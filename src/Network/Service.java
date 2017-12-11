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
     * @param cx     Valor das coordenadas X
     * @param cy     Valor das coordenadas Y
     * @param zoom   Valor do zoom
     * @param itera  Número de iterações
     * @param width  Valor do comprimento
     * @param height Valor da altura
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
     * @param factor
     * @return
     * @throws Exception
     */
    public synchronized Service cloneAndZoom(double factor) throws Exception {
        MathContext precision = new MathContext((int)(zoom.precision()*1.3)); //Precisão matemática para a realização de contas em BigDecimal
        BigDecimal bFactor = new BigDecimal(factor, precision);
        zoom = zoom.multiply(bFactor, precision);
        imageNumber++;
        Service s = new Service(cx, cy, zoom, itera, width, height);
        s.imageNumber = this.imageNumber;
        return s;
    }
    
    /**
     * Retorna o valor das coordenadas X
     * @return 
     */
    public BigDecimal getCx() {
        return cx;
    }
    
    /**
     * Define o valor das coordenadas X
     * @param cx 
     */
    public void setCx(BigDecimal cx) {
        this.cx = cx;
    }
    
    /**
     * Retorna o valor das coordenadas Y
     * @return 
     */
    public BigDecimal getCy() {
        return cy;
    }
    
    /**
     * Define o valor das coordenadas Y
     * @param cy 
     */
    public void setCy(BigDecimal cy) {
        this.cy = cy;
    }

    /**
     * Retorna o valor do zoom
     * @return 
     */
    public BigDecimal getZoom() {
        return zoom;
    }

    /**
     * Define o valor do zoom
     * @param zoom 
     */
    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

    /**
     * 
     * @return 
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 
     * @param data 
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * Retorna o número de iterações
     * @return 
     */
    public int getItera() {
        return itera;
    }

    /**
     * Define o número de iterações
     * @param itera 
     */
    public void setItera(int itera) {
        this.itera = itera;
    }

    /**
     * Retorna o valor do comprimento
     * @return 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Define o valor do comprimento
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Retorna o valor da altura
     * @return 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Define o valor da altura
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    } 
}
