/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class ImgUtils {

    public BufferedImage byteToImage(InputStream ian) throws Exception {
        return ImageIO.read(ian);
    }
    //https://stackoverflow.com/questions/3211156/how-to-convert-image-to-byte-array-in-java
    public byte[] ImageToByte(BufferedImage img) throws Exception {
        WritableRaster raster = img.getRaster();
        DataBufferByte data = (DataBufferByte) 
        raster.getDataBuffer();
        return (data.getData());
    }
    public void saveImage(byte[] bytes, String name) throws Exception{
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
        ImageIO.write(img, "png", new File(name));
    }
}
