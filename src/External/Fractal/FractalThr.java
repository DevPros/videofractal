//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     Biosystems & Integrative Sciences Institute                         ::
//::     Faculty of Sciences University of Lisboa                            ::
//::     http://www.fc.ul.pt/en/unidade/bioisi                               ::
//::                                                                         ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2016   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package External.Fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antoniomanso
 */
public class FractalThr extends Thread {

    AtomicInteger rowNumber;
    double centerX;
    double centerY;
    double zoom;
    int max;
    int[][] image;

    /**
     * make a thread to calculate video
     * @param rowNumber shared objet to balance the row calculation
     * @param centerX center X of fracatal
     * @param centerY center X of fracatal
     * @param zoom zoom of fractal
     * @param maxIteration max Iteration
     * @param image shared Array of escape iterations
     */
    public FractalThr(AtomicInteger rowNumber, double centerX, double centerY, double zoom, int maxIteration, int[][] image) {
        this.rowNumber = rowNumber;
        this.centerX = centerX;
        this.centerY = centerY;
        this.zoom = zoom;
        this.max = maxIteration;
        this.image = image;
    }

    @Override
    public void run() {
        int y;
        double cx, cy;
        //calculate pixels
        while ((y = rowNumber.getAndDecrement()) >= 0) {
            for (int x = 0; x < image[y].length; x++) {
                //coordinates of fractal world
                cx = centerX + (x - image[y].length / 2.0) * zoom;
                cy = centerY - (y - image.length / 2.0) * zoom;
                //escape iteration
                image[y][x] = mandelbroth(cx, cy, max);
            }
        }
    }

    /**
     * escape iteration of mandelbroth set
     *
     * @param c_re coordinate X o point (real value)
     * @param c_im coordinate Y o point (imaginary value)
     * @param max max iteration
     * @return escape iteration
     */
    private  int mandelbroth(double c_re, double c_im, int max) {
        int iteration = 0;
        double x = 0, y = 0, x_new;
        while (x * x + y * y < 4 && iteration < max) {
            x_new = x * x - y * y + c_re;
            y = 2 * x * y + c_im;
            x = x_new;
            iteration++;
        }
        return iteration;
    }
    
    
    /**
     *
     * @param centerX center X of fractal
     * @param centerY center Y of fractal
     * @param zoom zoom of fractal
     * @param max max iteration
     * @param width width of image
     * @param height height of image
     * @return image of the fractal
     */
    public static BufferedImage getFractal(double centerX, double centerY, double zoom, int max, int width, int height) {
        //escape iteration     
        int[][]image = new int[height][width];
        //row distributor
        AtomicInteger tickets = new AtomicInteger(height-1);
        //fractal threads 
        FractalThr[] thr = new FractalThr[Runtime.getRuntime().availableProcessors()];
        //create and start threads
        for (int i = 0; i < thr.length; i++) {
            thr[i]= new FractalThr(tickets, centerX, centerY, zoom, max, image);
            thr[i].start();
            
        }
       
        //wait to job done
        for (FractalThr fractalThr : thr) {
            try {
                fractalThr.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(FractalThr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         //image to fractal
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //color pallete
        int[] pallet = new int[max + 1];
        for (int i = 0; i < max; i++) {
            pallet[i] = Color.HSBtoRGB(i / 256f, 1, i / (i + 8f));
        }
        pallet[max] = 0; //black
        //calculate pixels
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //paint pixel
                img.setRGB(x, y, pallet[image[y][x]]);
            }
        }
        return img;
    }
}
