/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Canoso
 */
public class MulticastServer extends Thread{
     
    JTextArea debug;
    InetAddress groupAddress;
    int groupPort;
    String distPort;

    
    public MulticastServer(JTextArea debug, InetAddress groupAddress, int groupPort, int distPort) {
        this.debug = debug;
        this.groupAddress = groupAddress;
        this.groupPort = groupPort;
        this.distPort = distPort+"";
    }
    
   
    @Override
    public void run() {
        int count = 0;
        try {
            //Multicast Socket
            MulticastSocket socket = new MulticastSocket();
            while (true) {
                //mensagem
                byte[] data = new byte[256];
                count = count + 1;
                debug.append("[Multicast] Advertisement " + count + " | Port: " + distPort + "\n");
                data = distPort.getBytes();
                //pacote de envio
                DatagramPacket dg = new DatagramPacket(data, data.length, groupAddress, groupPort);
                // envia o pacote
                socket.send(dg);
                //pausa a thread 10 segundos
                Thread.sleep(10000);
            }
        } catch (Exception ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
