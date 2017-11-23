/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class ClientFractal {
    public static void main(String[] args) throws Exception {
        
        // mensagem a ser transmitida
        Service s = new Service(0,0, 4);
        
        for (int i = 0; i < 100; i++) {
            
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
            //ImageUtils.saveImage(s.getData(), "teste"+i+".jpg");
            //fechar o socket e as streams
            socket.close();
            in.close();
            out.close();
        
        }
    }
}
