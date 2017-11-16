/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Client {
    public static String iter;

    public static void setIter(String iter) {
        Client.iter = iter;
    }

    
    public static BufferedImage Client(String local, int port) throws Exception {
        //abrir um socket na máquina actual 
        Socket socket = new Socket(local, port);
        //
        System.out.println("Client is running. ");
        /**/
        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);
        PrintStream out = new PrintStream(socket.getOutputStream());
        out.println(iter);
        
        int len = dis.readInt();
        System.out.println("Image Size: " + len / 1024 + "KB");

        byte[] data = new byte[len];
        dis.readFully(data);
        dis.close();
        in.close();
        out.close();
        socket.close();
        
        InputStream ian = new ByteArrayInputStream(data);
        BufferedImage bImage = ImageIO.read(ian);
        return bImage;

        /**/
    }
}
