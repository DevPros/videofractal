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
    private BigDecimal cx;
    private BigDecimal cy;
    private BigDecimal zoom;
    private byte[] data;
            int imageNumber;
    
    public Service(BigDecimal cx, BigDecimal cy, BigDecimal zoom){
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
    
    public BigDecimal getCx() {
        return cx;
    }

    public void setCx(BigDecimal cx) {
        this.cx = cx;
    }

    public BigDecimal getCy() {
        return cy;
    }

    public void setCy(BigDecimal cy) {
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
