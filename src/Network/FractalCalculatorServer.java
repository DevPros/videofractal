/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import External.Fractal.FractalThr;
import Aplication.GUI.GUIServer;
import Network.Service;
import Utils.ImgUtils;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalCalculatorServer extends Thread {

    int port = 10000;
    GUIServer gui;
    long cont = 0;

    /**
     * Inicia o calculo da thread
     *
     * @param gui
     * @param port
     */
    public FractalCalculatorServer(GUIServer gui, int port) {
        this.port = port;
        this.gui = gui;
    }

    public static BufferedImage image = null;

    /**
     * Metodo que é iniciado quando se inicia a tread
     */
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            if (gui != null) {
                gui.jTextDebug.append("[Server] Server running on port " + port + "...\n");
            } else {
                System.out.print("[Server] Server running on port " + port + "...\n");
            }
            while (true) {
                //System.out.println(server.getInetAddress());
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service s = (Service) in.readObject();
                image = FractalThr.getFractal(s.getCx(), s.getCy(), s.getZoom(), s.getItera(), s.getWidth(), s.getHeight());
                if (gui != null) {
                    ImageIcon icon = new ImageIcon(image);

                    gui.l.setIcon(icon);
                }
                s.setData(ImgUtils.ImageToByte(image));

                out.writeObject(s);

                socket.close();
                in.close();
                out.close();

                if (isInterrupted() == true) {
                    System.out.println("Server stop....");
                    server.close();
                    break;
                }
            }
            if (gui != null) {
                gui.jTextDebug.append("Server stoped....\n");
            } else {
                System.out.print("Server stoped....\n");
            }
        } catch (Exception ex) {
            Logger.getLogger(FractalCalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
