/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import fractal.FractalImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Server extends Thread {
    public int port = 10000; 
    FractalImage f ;
    
    public Server(FractalImage f,int port ) {
        this.f = f;
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            while(true) {
                System.out.println("waiting for clients.....");
                Socket socket = server.accept();
                System.out.println("Client connected.");
                                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                
                ImageIO.write(f.getImg(), "jpg", baos);
                baos.flush();
                
                byte[] bytes = baos.toByteArray();
                baos.close();
                
                OutputStream out = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);
                
                dos.writeInt(bytes.length);
                dos.write(bytes, 0, bytes.length);
                
                System.out.println("Image sent to server....");
                
                dos.close();
                out.close();
                
                socket.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
