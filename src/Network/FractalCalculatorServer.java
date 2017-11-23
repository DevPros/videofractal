/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import fractal.FractalImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalCalculatorServer extends Thread {
    FractalImage f;
    int port;

    public FractalCalculatorServer(FractalImage f, int port) {
        this.f = f;
        this.port = port;
    }
    public void run(){
        try {
            ServerSocket server =  new ServerSocket(port);
            while(true){
                Socket socket = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Service s = (Service)in.readObject();
                f.resizeImg((int)s.getCx(),(int)s.getCy());
                in.close();
                out.close();
                socket.close();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(FractalCalculatorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
