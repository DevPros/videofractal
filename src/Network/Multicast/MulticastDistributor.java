/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class MulticastDistributor extends Thread {

    JTextArea debug;
    InetAddress groupAddress;
    int groupPort;
    String distPort;

    public MulticastDistributor(JTextArea debug, InetAddress groupAddress, int groupPort, int distPort) {
        this.debug = debug;
        this.groupAddress = groupAddress;
        this.groupPort = groupPort;
        this.distPort = distPort + "";
    }

    /**
     * É executado quando é chamado para encotrar os servidores via multicast
     */
    @Override
    public void run() {
        int count = 0;

        // Open a new DatagramSocket, which will be used to send the data.
        try (DatagramSocket serverSocket = new DatagramSocket()) {
            while (true) {
                // mensagem
                byte[] data = new byte[256];
                data = distPort.getBytes();
                // mensagem de debug
                debug.append("[Multicast] Advertisement " + count + " | Port: " + distPort + "\n");

                // incrementa contador
                count++;

                // Create a packet that will contain the data
                // (in the form of bytes) and send it.
                DatagramPacket msgPacket = new DatagramPacket(data,
                        data.length, groupAddress, groupPort);

                // envia pacote
                serverSocket.send(msgPacket);

                //pausa a thread 10 segundos
                Thread.sleep(10000);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(MulticastDistributor.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
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
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        
        /*
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
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }
}
