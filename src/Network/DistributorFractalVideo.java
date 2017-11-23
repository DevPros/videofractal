/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                Socket client = server.accept();
                LinkToServer link = new LinkToServer(client.getInetAddress().getHostName(),
                client.getPort(), s, factor);
                link.start();
                client.close();
            }
        } catch (Exception e) {
            
        }
    }
}