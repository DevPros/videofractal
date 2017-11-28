/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canoso
 */
public class DistributorFractalVideo {
    public static void main(String[] args) throws IOException {
        String[] ip = {"localhost", "localhost"};
        int[] port = {10001, 10002};
        // mensagem a ser transmitida
        Service s = new Service(
            -0.562255859375,
            -0.64355468875,
            1.0
        );
        double factor = 0.9;
        
        try{
            ServerSocket server = new ServerSocket(5000);
            while (true) {
                Socket serverFractal = server.accept();
                
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                int fserver_port = in.readInt();
                in.close();
                out.close();
                serverFractal.close();
                
                
                LinkToServer link = new LinkToServer(serverFractal.getInetAddress().getHostName(), fserver_port, s, factor);
                link.start();
                serverFractal.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(DistributorFractalVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
