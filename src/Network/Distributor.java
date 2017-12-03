/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

/**
 *
 * @author Canoso
 */
public class Distributor extends Thread {

    int port;
    BigDecimal factor;

    InetAddress myIP;
    JTextArea debug;

    JTextField jTextFieldIP;
    // mensagem a ser transmitida
    Service s = new Service(BigDecimal.valueOf(
            -1.78916901860482310667446834118883876381736183681),
            BigDecimal.valueOf(
                    -0.000000339368515767182566028230266146812728348218),
            BigDecimal.valueOf(1E-2)
    );

    public Distributor(int port, BigDecimal factor, JTextArea jTextAreaDebug, JTextField jTextFieldIP) {
        this.port = port;
        this.factor = factor;
        this.debug = jTextAreaDebug;
        this.jTextFieldIP = jTextFieldIP;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);

            // get the real local address
            // https://stackoverflow.com/a/38342964
            try (final DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                myIP = InetAddress.getByName(socket.getLocalAddress().getHostAddress());
            }

            debug.append("Distributor running on address " + myIP + ":" + port + "... \n");
            jTextFieldIP.setText(myIP + ":" + port);

            while (true) {
                Socket serverFractal = server.accept();

                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(serverFractal.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(serverFractal.getInputStream());
                //valor da porta
                int fserver_port = in.readInt();
                // adiciona o ip:porta ao debug
                debug.append("New Server " + serverFractal.getInetAddress().getHostAddress() + ":" + fserver_port + "\n");

                in.close();
                out.close();
                serverFractal.close();

                LinkToServer link = new LinkToServer(serverFractal.getInetAddress().getHostName(), fserver_port, s, factor);
                link.start();
                serverFractal.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generateVideo(File video, File imagens[], int fps, String filetype) throws IOException {
        IMediaWriter writer = ToolFactory.makeWriter(video + "");
        int frameNumber = 0;

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
            frameNumber++;
        }
        writer.close();
        System.out.println("Video gerado!");
    }

}
