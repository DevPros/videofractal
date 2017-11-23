/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import fractal.FractalImage;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalCalculatorServer {
    FractalImage f;
    int port;

    public FractalCalculatorServer(FractalImage f, int port) {
        this.f = f;
        this.port = port;
    }
    
}
