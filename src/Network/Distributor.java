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
import javax.swing.DefaultListModel;
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
    JList<String> serverList = new JList<String>(new DefaultListModel<String>());
    
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
            debug.append("Distributor running on port 5000... \n");
            while (true) {
                Socket serverFractal = server.accept();
                
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                
                int fserver_port = in.readInt();
                String newServer =  serverFractal.getInetAddress()+":"+fserver_port;
                debug.append("New Server "+serverFractal.getInetAddress()+":"+fserver_port+"\n");
                
                //adicionar servidor à lista
                ((DefaultListModel)serverList.getModel()).addElement(serverFractal.getInetAddress()+":"+fserver_port);
                
                in.close();
                out.close();
                serverFractal.close();
                
                
                LinkToServer link = new LinkToServer(serverFractal.getInetAddress().getHostName(), fserver_port, s, factor);
                link.start();
                serverFractal.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
