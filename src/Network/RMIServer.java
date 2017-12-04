/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import GUI.GUIDistributor;
import GUI.RMIClient;
import Remote.IremoteFractal;
import auxiliar.RMI;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

/**
 *
 * @author Canoso
 */
public class RMIServer extends Thread{
    
    GUIDistributor gui;
    double centerX;
    double centerY;
    double zoom;
    int iter;
    int width;
    int height;
    int count = 0;

    public RMIServer(GUIDistributor gui, double centerX, double centerY, double zoom, int iter, int width, int height) {
        this.gui = gui;
        this.centerX = centerX;
        this.centerY = centerY;
        this.zoom = zoom;
        this.iter = iter;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void run() {
        gui.jTextAreaDebug.append("[RMI] RMI Server initialized.");
        try{
            gui.jProgressBar1.setMinimum(0);
            gui.jProgressBar1.setMaximum(2147483647);
            IremoteFractal remoteF = (IremoteFractal) RMI.getRemote("localhost", 10021, "fractal");
            while(true) 
            {
                zoom *= 0.9;
                gui.jProgressBar1.setValue((int)(Math.pow(zoom,-1)/1000));
                byte[] data = remoteF.getFratal(centerX, centerY, zoom, iter, width, height);
                BufferedImage image = auxiliar.ImgUtils.byteToImage(data);
                ImageIO.write(image, "png", new File("fractal" + String.format("%06d", count) + ".png"));
                count++;
            }
        } catch (Exception ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
