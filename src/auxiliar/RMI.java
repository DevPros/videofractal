//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     Biosystems & Integrative Sciences Institute                         ::
//::     Faculty of Sciences University of Lisboa                            ::
//::     http://www.fc.ul.pt/en/unidade/bioisi                               ::
//::                                                                         ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2015   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package auxiliar;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created on 15/dez/2015, 22:07:34
 *
 * @author zulu - computer
 */
public class RMI {

    /**
     * provides a remote object on the server
     *
     * @param remote objet
     * @param port port to receive calls
     * @param objectName name of the object
     */
    public static void startRemoteObject(Remote remote, int port, String objectName) throws RemoteException {
            //Export Remote Object
            Remote stub = UnicastRemoteObject.exportObject(remote, 0);
            //create registry
            Registry registry = LocateRegistry.createRegistry(port);
            //regist the object in the registry
            registry.rebind(objectName, stub);
    }

    public static Object getRemote(String ip, int port, String name) throws Exception {
            //locate registry
            Registry registry = LocateRegistry.getRegistry(ip, port);
            //gets the object
            return registry.lookup(name);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 201512152207L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2015  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
