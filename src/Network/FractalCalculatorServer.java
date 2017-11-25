/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import auxiliar.ImgUtils;
import fractal.FractalImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalCalculatorServer extends Thread {

    FractalImage f;
    int port = 10000;

    public FractalCalculatorServer(FractalImage f, int port) {
        this.f = f;
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server run....");
            while (true) {
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service s = (Service) in.readObject();
                f.changePosition(s.getCx(), s.getCy());
                f.setNewZoom(s.getZoom());
                f.balCalculateFractalGUI(null, null);
                f.initCalculateFractalGUI();
                s.setData(ImgUtils.ImageToByte(f.getImg()));
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
            System.out.println("Server stop....");
        } catch (Exception ex) {
            Logger.getLogger(FractalCalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
