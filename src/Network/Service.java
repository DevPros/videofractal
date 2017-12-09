/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Service implements Serializable {

    MathContext precision = new MathContext(10); //Precisão matemática para a realização de contas em BigDecimal
    private BigDecimal cx;
    private BigDecimal cy;
    private BigDecimal zoom;
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
    public Service(BigDecimal cx, BigDecimal cy, BigDecimal zoom, int itera, int width, int height) {
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
        BigDecimal bFactor = new BigDecimal(factor);
        zoom = zoom.multiply(bFactor, precision);
        //zoom *= factor;
        imageNumber++;
        Service s = new Service(cx, cy, zoom, itera, width, height);
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
