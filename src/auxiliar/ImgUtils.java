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
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class ImgUtils {

    public static BufferedImage byteToImage(byte[] data) throws Exception {
        InputStream ian = new ByteArrayInputStream(data);
        BufferedImage bImage = ImageIO.read(ian);
        return bImage;
    }

    //https://stackoverflow.com/questions/10247123/java-convert-bufferedimage-to-byte-without-writing-to-disk
    public static byte[] ImageToByte(BufferedImage img) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //ImageIO.setUseCache(false);
        ImageIO.write(img, "jpg", outputStream);

        byte[] imageBytes = outputStream.toByteArray();

        return imageBytes;
    }

    public static void saveImage(byte[] bytes, String name) throws Exception {
        ImageIO.write(byteToImage(bytes),"jpg",new File(name));
    }
}
