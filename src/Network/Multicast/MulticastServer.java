/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canoso
 */
public class MulticastServer extends Thread{
    MulticastSocket socket;
    DatagramPacket packet;
    InetAddress groupAddress;
    
    int port;

    public MulticastServer(int port, InetAddress groupAddress) {
        this.port = port;
        this.groupAddress = groupAddress;
    }
    
    @Override
    public void run() {
        System.out.println("[Multicast] Multicast receiver started\n");
        
        byte[] buf = new byte[256];
        
        try (MulticastSocket clientSocket = new MulticastSocket(port)){
            //Joint the Multicast group.
            clientSocket.joinGroup(groupAddress);
     
            while (true) {
                // Receive the information and print it.
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);

                String msg = new String(buf, 0, buf.length);
                System.out.println("Socket 1 received msg: " + msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        /*
        try {

            socket = new MulticastSocket(port);

            address = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(address);
            while (true) {
                byte[] buff = new byte[256];
                packet = new DatagramPacket(buff, buff.length);
                System.out.println("Waiting for packets...");
                socket.receive(packet);
                System.out.println("Received Message: " + socket.getLocalSocketAddress().toString());
                String NewHost = packet.getAddress().getHostAddress();
                String received = new String(packet.getData());
                received = received.trim();
                port = Integer.parseInt(received);

                
                Thread.sleep(5000);
            }
        } catch (Exception ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }
}
