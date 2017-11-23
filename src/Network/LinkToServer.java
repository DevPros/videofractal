/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
    
    public void run(){
        while( service.getZoom() < 1E-16){
            //ligação do socket ao servidor
            Socket socket = new Socket("localhost", 10001);
            // abertura da stream de saída
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            //abertura da stream de entrada
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            zoom = zoom * 0.9;
            s.setZoom(zoom);
            
            // enviar a mensagem
            out.writeObject(s);
            out.flush(); //obriga a enviar o pacote
            // receber a resposta
            s = (Service) in.readObject();
            // apresental a resposta
            ImageUtils.saveImage(s.getData(), "teste"+i+".jpg");
            //fechar o socket e as streams
            socket.close();
            in.close();
            out.close();
        }
    }
    
}
