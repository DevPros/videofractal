/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class LinkToServer extends Thread {
    String ip;
    int port;
    Service service;
    double fator;

    public LinkToServer(String ip, int port, Service service, double fator) {
        this.ip = ip;
        this.port = port;
        this.service = service;
        this.fator = fator;
    }
    
    
    
}
