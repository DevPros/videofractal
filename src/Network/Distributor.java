/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import GUI.GUIDistributor;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.DatagramSocket; 
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Canoso
 */
public class Distributor extends Thread{
    
    int port;
    double factor;
    
    private static GUIDistributor gui;
    InetAddress myIP;

    // mensagem a ser transmitida
    Service s = null;

    public Distributor(double centerX ,double centerY ,int port, double factor,GUIDistributor gui) {
        this.port = port;
        this.factor = factor;
        this.gui = gui;
        this.s = new Service(centerX,centerY,1E-1);
    }
    
    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(port);
            
            // get the real local address
            // https://stackoverflow.com/a/38342964
            try(final DatagramSocket socket = new DatagramSocket()){
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                myIP = InetAddress.getByName(socket.getLocalAddress().getHostAddress());
            }
            
            
            gui.jTextAreaDebug.append("Distributor running on address "+myIP+":"+port+"... \n");
            gui.jTextFieldIP.setText(myIP+":"+port);
            
            while (true) {
                Socket serverFractal = server.accept();
                System.out.println("Aqui");
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                //valor da porta
                int fserver_port = in.readInt();
                // adiciona o ip:porta ao debug
                gui.jTextAreaDebug.append("New Server "+serverFractal.getInetAddress().getHostAddress()+":"+fserver_port+"\n");
                
                in.close();
                out.close();
                serverFractal.close();
                
                
                LinkToServer link = new LinkToServer(serverFractal.getInetAddress().getHostName(), fserver_port, s, factor, gui);
                link.start();
                serverFractal.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void generateVideo(File video, File imagens[], int fps, String filetype, GUIDistributor gui) throws IOException
    {
        IMediaWriter writer = ToolFactory.makeWriter(video + "");
        int frameNumber = 0;
        int imgleng = imagens.length - 1;
        for (File imagem : imagens) {
            final BufferedImage image = ImageIO.read(imagem);
            // o ficheiro nao e uma imagem
            if (image == null) {
                continue;
            }
            if (frameNumber == 0) {
                writer.addVideoStream(0, 0, image.getWidth(), image.getHeight());
            }
            writer.encodeVideo(0, image, (int) ((1000.0 / fps) * frameNumber), TimeUnit.MILLISECONDS);
            gui.jProgressBarVideo.setMaximum(imagens.length - 1);
            gui.jProgressBarVideo.setValue(frameNumber);

            double perc = (((double) frameNumber / imgleng) * 100);
            NumberFormat formatter = new DecimalFormat("#0.00");
            gui.lperc.setText(formatter.format(perc) + "%");
            frameNumber++;
        }
        writer.close();
        JOptionPane.showMessageDialog(gui, "Video Gerado", "Fractal Movie", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/auxiliar/icon.png"));
        gui.jProgressBarVideo.setValue(0);
        gui.bt_genVideo.setEnabled(true);
        gui.lperc.setText("0.00%");
        System.out.println("Video gerado!");
    }
    
}
