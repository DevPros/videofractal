/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Aplication.GUI.GUIDistributor;
import Network.Service;
import Utils.ImgUtils;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class LinkToServer extends Thread {

    String ip;
    int port;
    Service service;
    GUIDistributor gui;
    double fator;

    /**
     * Construtor que recebe a ligação do server
     *
     * @param ip
     * @param port
     * @param service
     * @param fator
     * @param gui
     */
    public LinkToServer(String ip, int port, Service service, double fator, GUIDistributor gui) {
        this.ip = ip;
        this.port = port;
        this.service = service;
        this.fator = fator;
        this.gui = gui;
    }
    /**
     * Metodo que é chamado para executar a impressão das imgens
     */
    @Override
    public void run() {
        while (service.getZoom() > 1E-18) {
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
                
                ImageIcon icon = new ImageIcon(ImgUtils.byteToImage(myService.getData()));
                gui.limage.setIcon(icon);
                // apresenta a resposta
                gui.lnumbImg.setText(myService.imageNumber + "");
                gui.pb_oneMin.setMaximum(60*24);
                gui.pb_oneMin.setValue(myService.imageNumber);
                ImgUtils.saveImage(myService.getData(), "img/fractal" + String.format("%05d", myService.imageNumber) + ".jpg");

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
