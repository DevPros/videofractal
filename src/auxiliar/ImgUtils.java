/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class ImgUtils {
    public BufferedImage byteToImage(InputStream ian) throws IOException{
        return ImageIO.read(ian);
    }
}
