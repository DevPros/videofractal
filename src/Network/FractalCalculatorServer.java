/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import FractalNovo.FractalThr;
import auxiliar.ImgUtils;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalCalculatorServer extends Thread {

    int port = 10000;
    JLabel l; //imagem
    JTextArea jDebug; //Console
    
    /**
     * Inicia o calculo da thread
     * @param l
     * @param jDebug
     * @param port 
     */
    public FractalCalculatorServer(JLabel l, JTextArea jDebug, int port) {
        this.l = l;
        this.jDebug = jDebug;
        this.port = port;
    }

    public static BufferedImage image = null;
    /**
     * Metodo que é iniciado quando se inicia a tread
     */
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            // try catch CLI
            try{
                jDebug.append("[Server] Server running on port " + port + "... \n");
            } catch (Exception e) {
                System.out.println("[Server] Server running on port " + port + "... \n");
            }
            
            while (true) {
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service s = (Service) in.readObject();
                image = FractalThr.getFractal(s.getCx(), s.getCy(), s.getZoom(), 1000, 3860, 2160);
                
                // try catch CLI
                try{
                    
                    ImageIcon icon = new ImageIcon(image);
                    l.setIcon(icon);
                    
                } catch (Exception e) {
                }
                
                s.setData(ImgUtils.ImageToByte(image));
                out.writeObject(s);

                socket.close();
                in.close();
                out.close();
                
                // stop server
                if (isInterrupted() == true) {
                    server.close();
                    break;
                }
            }
            // try catch CLI
            try{
                jDebug.append("Server stopped... \n");
            } catch (Exception e) {
                System.out.println("[Server] Server stopped... \n");
            }
        } catch (Exception ex) {
            Logger.getLogger(FractalCalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
