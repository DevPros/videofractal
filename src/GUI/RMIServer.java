/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Remote.RemoteFractal;

/**
 *
 * @author Canoso
 */
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RMIServer {
    public static void main(String[] args) {
        try {
            RemoteFractal fr = new RemoteFractal();
            auxiliar.RMI.startRemoteObject(fr, 10021, "fractal");
            System.out.println("Servidor disponivel");
        } catch (RemoteException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
