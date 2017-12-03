/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remote;

import FractalNovo.FractalThr;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canoso
 */
public class RemoteFractal implements IremoteFractal{

    @Override
    public byte[] getFratal(double centerX, double centerY, double zoom, int max, int width, int height) throws RemoteException {
        try {
            BufferedImage img = FractalThr.getFractal(centerX, centerY, new BigDecimal(zoom), max, width, height);
            return auxiliar.ImgUtils.ImageToByte(img);
        } catch (Exception ex) {
            Logger.getLogger(RemoteFractal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
