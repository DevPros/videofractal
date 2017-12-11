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
import javax.imageio.ImageIO;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class ImgUtils {
    
    /**
     * Converte um array de bytes para imagem
     * @param data conteudo da imagem
     * @return Imagem
     * @throws Exception 
     */
    public static BufferedImage byteToImage(byte[] data) throws Exception {
        InputStream ian = new ByteArrayInputStream(data);
        BufferedImage bImage = ImageIO.read(ian);
        return bImage;
    }
    
    /**
     * Converte uma imagem para um array de bytes
     * @param img imagem
     * @return array de bytes
     * @throws Exception 
     */
    //https://stackoverflow.com/questions/10247123/java-convert-bufferedimage-to-byte-without-writing-to-disk
    public static byte[] ImageToByte(BufferedImage img) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        return imageBytes;
    }
    
    /**
     * Função utilizada para guardar imagens
     * @param bytes conteudo da imagem
     * @param name nome do ficheiro
     * @throws Exception 
     */
    public static void saveImage(byte[] bytes, String name) throws Exception {
        ImageIO.write(byteToImage(bytes),"jpg",new File(name));
    }
}
