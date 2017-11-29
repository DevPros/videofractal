/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.DatagramSocket; 

/**
 *
 * @author Canoso
 */
public class Distributor extends Thread{
    
    int port;
    double factor;
    
    InetAddress myIP;
    JTextArea debug;
    
    DefaultListModel model = new DefaultListModel();
    
    JList serverList = new JList(model);
    JTextField jTextFieldIP;
    // mensagem a ser transmitida
    Service s = new Service(
        -0.562255859375,
        -0.64355468875,
        2.0
    );

    public Distributor(int port, double factor, JTextArea jTextAreaDebug, JList ServerList, JTextField jTextFieldIP) {
        this.port = port;
        this.factor = factor;
        this.debug = jTextAreaDebug;
        this.serverList = ServerList;
        this.jTextFieldIP = jTextFieldIP;
    }
    
    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(port);
            
            // get the real local address
            // https://stackoverflow.com/a/38342964
            try(final DatagramSocket socket = new DatagramSocket()){
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                myIP = InetAddress.getByName(socket.getLocalAddress().getHostAddress());
            }
            
            
            debug.append("Distributor running on address "+myIP+":"+port+"... \n");
            jTextFieldIP.setText(myIP+":"+port);
            
            while (true) {
                Socket serverFractal = server.accept();
                
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                //valor da porta
                int fserver_port = in.readInt();
                // adiciona o ip:porta ao debug
                debug.append("New Server "+serverFractal.getInetAddress().getHostAddress()+":"+fserver_port+"\n");
                
                //adicionar servidor à lista
                //model.addElement(serverFractal.getInetAddress().getHostAddress()+":"+fserver_port);
                model.addElement(serverFractal.getInetAddress().getHostAddress()+":"+fserver_port);
                
                //update JList
                //JList serverList = new JList(model);
                //jLabelIPAddress.setText(Inet4Address.getLocalHost().getHostAddress());
                //jLabelIPAddress.setText("ola");
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
