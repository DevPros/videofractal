/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Service implements Serializable {
    private Double cx;
    private Double cy;
    private BigDecimal zoom;
    private byte[] data;
            int imageNumber;
    
    public Service(Double cx, Double cy, BigDecimal zoom){
        this.cx = cx;
        this.cy = cy;
        this.zoom = zoom;
        this.data = null;
        imageNumber = 0;
    }

    public synchronized Service cloneAndZoom(BigDecimal factor) throws Exception{
        zoom = zoom.multiply(factor);
        imageNumber++;
        Service s = new Service(cx, cy, zoom);
        s.imageNumber = this.imageNumber;
        return s;
    }
    
    public Double getCx() {
        return cx;
    }

    public void setCx(Double cx) {
        this.cx = cx;
    }

    public Double getCy() {
        return cy;
    }

    public void setCy(Double cy) {
        this.cy = cy;
    }

    public BigDecimal getZoom() {
        return zoom;
    }

    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
}
