/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canoso
 */
public class Server extends Thread{
    MulticastSocket socket;
    DatagramPacket packet;
    InetAddress address;
    
    int port;

    public Server(int port) {
        this.port = port;
    }
    
    @Override
    public void run() {
        System.out.println("[Multicast] Multicast receiver started\n");
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
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
