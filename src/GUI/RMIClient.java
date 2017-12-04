/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Remote.IremoteFractal;
import auxiliar.RMI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Canoso
 */
public class RMIClient {
    public static void main(String[] args) {
        try{
            double cx = -1.7;
            double cy = -0.00000000339;
            int max = 100;
            int width = 100;
            int height = 100;
            BigDecimal zoom = new BigDecimal(1E-2);
            
            IremoteFractal remoteF = (IremoteFractal) RMI.getRemote("localhost", 10021, "fractal");
            for (int i = 0; i < 100; i++) {
                zoom = zoom.multiply(BigDecimal.valueOf(0.9));
                byte[] data = remoteF.getFratal(cx, cy, zoom, max, width, height);
                BufferedImage image = auxiliar.ImgUtils.byteToImage(data);
                ImageIO.write(image, "png", new File("remote" + i + ".png"));
            }
        } catch (Exception ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
