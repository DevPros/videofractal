/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class ImgUtils {

    /*
    public static byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        return  baos.toByteArray();
    }

  */
    
    public static BufferedImage byteToImage(byte[] data) throws Exception {
        return ImageIO.read(new ByteArrayInputStream(data));
    }

    //https://stackoverflow.com/questions/10247123/java-convert-bufferedimage-to-byte-without-writing-to-disk
    public static byte[] ImageToByte(BufferedImage img) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", outputStream);
        //byte[] imageBytes = outputStream.toByteArray();
        outputStream.flush();
        return outputStream.toByteArray();
    }

    public static void saveImage(byte[] bytes, String name) throws Exception {
        //ImageIO.write(byteToImage(bytes),"jpg",new File(name));
        Files.write(Paths.get(name), bytes);
    }
}
