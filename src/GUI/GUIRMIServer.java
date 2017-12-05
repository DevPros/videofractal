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
import javax.swing.ImageIcon;
public class GUIRMIServer extends Thread{
    GUIServer gui = null;

    public GUIRMIServer(GUIServer gui) {
        this.gui = gui;
    }
    
    public void run() {
        try {
            RemoteFractal fr = new RemoteFractal();
            auxiliar.RMI.startRemoteObject(fr,Integer.valueOf(gui.txt_port.getText()), "fractal");
            gui.lstatusServer.setText("Server Up in" + gui.txt_port.getText());
        } catch (RemoteException ex) {
            Logger.getLogger(GUIRMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
