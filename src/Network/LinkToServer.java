/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class LinkToServer extends Thread {
    String ip;
    int port;
    Service service;
    double fator;

    public LinkToServer(String ip, int port, Service service, double fator) {
        this.ip = ip;
        this.port = port;
        this.service = service;
        this.fator = fator;
    }
    
    @Override
    public void run(){
        while( service.getZoom() < 1E-16){
            try {
                //ligação do socket ao servidor
                Socket socket = new Socket(ip, port);
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service myService = service.cloneAndZoom(fator);
                
                // enviar a mensagem
                out.writeObject(myService);
                out.flush(); //obriga a enviar o pacote
                // receber a resposta
                myService = (Service) in.readObject();
                // apresental a resposta
                
                //fechar o socket e as streams
                socket.close();
                in.close();
                out.close();
            } catch (Exception ex) {
            Logger.getLogger(LinkToServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
