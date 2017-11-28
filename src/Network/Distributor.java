/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author Canoso
 */
public class Distributor extends Thread{
    
    int port;
    double factor;
    
    JTextArea debug;
    JList<String> serverList;
    
    // mensagem a ser transmitida
        Service s = new Service(
            -0.562255859375,
            -0.64355468875,
            1.0
        );

    public Distributor(int port, double factor, JTextArea jTextAreaDebug, JList<String> ServerList) {
        this.port = port;
        this.factor = factor;
        this.debug = jTextAreaDebug;
        this.serverList = ServerList;
    }
    
    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(port);
            debug.append("[DIST] Distributor running on port 5000...");
            while (true) {
                Socket serverFractal = server.accept();
                
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                
                int fserver_port = in.readInt();
                debug.append("[DIST] New Server on port "+fserver_port);
                
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
