/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.Serializable;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Service implements Serializable {

    private double cx;
    private double cy;
    private double zoom;
    private int itera;
    private int width;
    private int height;
    private byte[] data;
    int imageNumber;
    /**
     * Objecto que é chamado para passar as informações de um lado para o outro
     * @param cx
     * @param cy
     * @param zoom
     * @param itera
     * @param width
     * @param height 
     */
    public Service(double cx, double cy, double zoom, int itera, int width, int height) {
        this.cx = cx;
        this.cy = cy;
        this.zoom = zoom;
        this.data = null;
        this.itera = itera;
        this.width = width;
        this.height = height;
        imageNumber = 0;
    }

    /**
     * Recebe o novo valor do zoom e multiplica pelo anterior
     *
     * @param factor
     * @return
     * @throws Exception
     */
    public synchronized Service cloneAndZoom(double factor) throws Exception {
        zoom *= factor;
        imageNumber++;
        Service s = new Service(cx, cy, zoom, itera, width, height);
        s.imageNumber = this.imageNumber;
        return s;
    }

    public double getCx() {
        return cx;
    }
    

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getItera() {
        return itera;
    }

    public void setItera(int itera) {
        this.itera = itera;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    } 
}
