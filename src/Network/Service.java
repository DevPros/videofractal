/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import auxiliar.ImgUtils;
import java.io.Serializable;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Service implements Serializable {
    private double cx;
    private double cy;
    private double zoom;
    private byte[] data;
    double i = 0;
    
    public Service(double cx, double cy, double zoom){
        this.cx = cx;
        this.cy = cy;
        this.zoom = zoom;
        this.data = null;
    }

    public synchronized Service cloneAndZoom(double factor) throws Exception{
        zoom *= factor;
        new ImgUtils().saveImage(getData(), "teste"+i+".jpg");
        i++;
        return new Service(cx, cy, zoom);
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
    
}
