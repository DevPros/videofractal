/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import auxiliar.ImgUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ClientFractal extends Thread{
   private int port = 10000;
   private String ip = "locahost";
   
    
   @Override
    public void run(){
        
        // mensagem a ser transmitida
        Service s = new Service(0, 0, 4);

        for (int i = 0; i < 100; i++) {

            try {
                //ligação do socket ao servidor
                Socket socket = new Socket(ip, port);
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service myService = s.cloneAndZoom(0.9);
                
                // enviar a mensagem
                out.writeObject(myService);
                out.flush(); //obriga a enviar o pacote
                
                // receber a resposta
                myService = (Service) in.readObject();
                // apresental a resposta
                ImgUtils.saveImage(myService.getData(), "teste" + myService.imageNumber + ".jpg");
                //fechar o socket e as streams
                socket.close();
                in.close();
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(ClientFractal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void setPortIp(String ip,int port) {
        this.ip = ip;
        this.port = port;
    }
}
