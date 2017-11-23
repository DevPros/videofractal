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

/**
 *
 */
public class ClientFractal {

    public static void main(String[] args) throws Exception {

        // mensagem a ser transmitida
        Service s = new Service(0, 0, 4);

        for (int i = 0; i < 100; i++) {

            //ligação do socket ao servidor
            Socket socket = new Socket("localhost", 10000);
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
            ImgUtils.saveImage(s.getData(), "teste"+myService.imageNumber+".jpg");
            //fechar o socket e as streams
            socket.close();
            in.close();
            out.close();

        }
    }
}
