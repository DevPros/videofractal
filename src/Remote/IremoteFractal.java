/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remote;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Canoso
 */
public interface IremoteFractal extends Remote{
    public byte[] getFratal(double centerX, double centerY, BigDecimal zoom, int max, int width, int height) throws RemoteException;
}
