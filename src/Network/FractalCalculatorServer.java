/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import FractalNovo.FractalThr;
import GUI.GUIServer;
import auxiliar.ImgUtils;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalCalculatorServer extends Thread {

    
    int port = 10000;
    GUIServer gui;
    long cont = 0;
    public FractalCalculatorServer(GUIServer gui,int port) {
        this.port = port;
        this.gui = gui;
    }
    
    public static BufferedImage image = null;

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("[Server] Server running on port "+port+"...");
            while (true) {
                //System.out.println(server.getInetAddress());
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service s = (Service) in.readObject();
                System.out.println("ZOOM: "+s.getZoom()+" X:"+s.getCx()+" Y: "+s.getCy());
                image = FractalThr.getFractal(s.getCx(), s.getCy(), s.getZoom(),1000, 3860, 2160);
                ImageIcon icon = new ImageIcon(image);
                

                gui.l.setIcon(icon);
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
            System.out.println("Server stop....");
        } catch (Exception ex) {
            Logger.getLogger(FractalCalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
