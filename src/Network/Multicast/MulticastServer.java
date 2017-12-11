/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Multicast;

import Application.UIServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class MulticastServer extends Thread {

    MulticastSocket socket;
    DatagramPacket packet;
    InetAddress groupAddress;
    int port;

    /**
     * Lança instancia para receber pacote de multicast
     *
     * @param port         Porto
     * @param groupAddress Endereço
     * @param gui          GUI
     * @return mensagem recebida
     */
    public static String listenMulticast(int port, InetAddress groupAddress, UIServer gui) {
        String msg = "";
        
        try {
            gui.jTextDebug.append("[Multicast] Multicast receiver started \n");
        } catch (Exception e) {
            System.out.println("[Multicast] Multicast receiver started \n");
        }
        
        byte[] buf = new byte[256];

        try (MulticastSocket clientSocket = new MulticastSocket(port)) {
            //Joint the Multicast group.
            clientSocket.joinGroup(groupAddress);

            // Receive the information
            DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
            clientSocket.receive(msgPacket);

            // obtem o ip de quem enviou o pacote + , + porta
            msg = msgPacket.getAddress().toString() + "," + new String(buf, 0, buf.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return msg;
    }
}
