/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import fractal.FractalImage;
import fractal.functions.Madelbroth;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Server extends Thread {

    public int port = 10000;
    ServerSocket server = null;
    Socket socket = null;
    FractalImage f;

    public Server(FractalImage f, int port) {
        this.f = f;
        this.port = port;
    }

    public void run() {
        try {
            server = new ServerSocket(port);
            while (true) {
                System.out.println("waiting for clients.....");
                socket = server.accept();
                System.out.println("Client connected.");
                Scanner in = new Scanner(socket.getInputStream());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                String iter = in.nextLine();
                String zoom = in.nextLine();
                String width = in.nextLine();
                String height = in.nextLine();
                System.out.println("N.º de Iterações = " + iter);
                System.out.println("N.º de ZOOM = " + zoom);
                f.setNewZoom(Double.parseDouble(zoom));
                f.resizeImg(Integer.parseInt(width), Integer.parseInt(height));
                f.setFractalFunction(new Madelbroth(Integer.parseInt(iter)));
                f.seqCalculateFractalGUI(null, null);
                f.initCalculateFractalGUI();
                ImageIO.write(f.getImg(), "jpg", baos);
                baos.flush();

                byte[] bytes = baos.toByteArray();
                baos.close();

                OutputStream out = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);

                dos.writeInt(bytes.length);
                dos.write(bytes, 0, bytes.length);

                System.out.println("Image sent to server....");
                in.close();
                dos.close();
                out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeSocket() {
        try {
            server.close();
            interrupt();
            stop();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
