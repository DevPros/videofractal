/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import Application.UIDistributor;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.DatagramSocket;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Distributor extends Thread {
    
    MathContext precision = new MathContext(5);
    int port;
    double factor;

    private static UIDistributor gui;
    InetAddress myIP;

    // mensagem a ser transmitida
    Service s = null;

    /**
     * Recebe os Parametro da GUI Para começar a distribuir
     *
     * @param centerX Valor X para exploração
     * @param centerY Valor Y para exploração
     * @param port    porto
     * @param factor  zoom
     * @param gui     GUI
     */
    public Distributor(double centerX, double centerY, int port, double factor, UIDistributor gui) {
        this.port = port;
        this.factor = factor;
        this.gui = gui;
        this.s = new Service(new BigDecimal(centerX, precision), new BigDecimal(centerY, precision), new BigDecimal(1E-2), Integer.valueOf(gui.jTextIterations.getText()), Integer.valueOf(gui.jTextWidth.getText()), Integer.valueOf(gui.jTextHeight.getText()));
    }

    /**
     * Metodo Executado com a Thread é iniciada
     * Efetua a distribuição da carga de trabalho
     */
    @Override
    public void run() {
        try {
            //Server Port
            ServerSocket server = new ServerSocket(port);

            // get the real local address
            // https://stackoverflow.com/a/38342964
            try (final DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                myIP = InetAddress.getByName(socket.getLocalAddress().getHostAddress());
            }

            gui.jTextAreaDebug.append("[Dist] Running on address " + myIP + ":" + port + "... \n");
            gui.jTextFieldIP.setText(myIP + ":" + port);
            //Escuta o server
            while (true) {
                Socket serverFractal = server.accept();
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                //valor da porta
                int fserver_port = in.readInt();
                // adiciona o ip:porta ao debug
                gui.jTextAreaDebug.append("[Dist] New Server " + serverFractal.getInetAddress().getHostAddress() + ":" + fserver_port + " \n");

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

    /**
     * Função que serve para gerar o vídeo
     *
     * @param video
     * @param imagens
     * @param fps
     * @param filetype
     * @param gui
     * @throws IOException
     */
    public static void generateVideo(File video, File imagens[], int fps, String filetype, UIDistributor gui) throws IOException {
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
            gui.jProgressBarVideo.setMaximum(imagens.length);
            gui.jProgressBarVideo.setValue(frameNumber);

            double perc = (((double) frameNumber / imgleng) * 100);
            NumberFormat formatter = new DecimalFormat("#0.00");
            gui.lperc.setText(formatter.format(perc) + "%");
            frameNumber++;
        }
        writer.close();
        gui.jTextAreaDebug.append("[Video] Generated video \n");
        JOptionPane.showMessageDialog(gui, "Video Gerado", "Fractal Movie", JOptionPane.PLAIN_MESSAGE, new ImageIcon("src/auxiliar/icon.png"));
        gui.jProgressBarVideo.setValue(0);
        gui.bt_genVideo.setEnabled(true);
        gui.lperc.setText("0.00%");
    }

}
