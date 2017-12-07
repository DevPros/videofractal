/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMD;

import Server.AuxServer;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Server {

    public static void help() {
        System.out.println("Metodos Existentes");
        System.out.println("unicast");
        System.out.println("\"nomeDoExecutável unicast portaDoServer ipDistribuidor portaDoDistribuidor \"");
        System.out.println("multicast");
        System.out.println("\"nomeDoExecutável multicast portaDoServer ipGroup portGroup \"");
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("numero de parametros errados");
            help();
            return;
        }
        try {
            int portServer = Integer.valueOf(args[1]);
            String ip = args[2];
            int portDist = Integer.valueOf(args[3]);
            System.out.println("Definiu a porta do servidor: " + portServer + "\nIP:" + ip + "\nPorta da Distribuição:" + portDist);
            switch (args[0]) {
                case "unicast":
                    AuxServer.unicast(portServer, ip, portDist,null);
                    break;
                case "multicast":
                    AuxServer.multicast(portServer, ip, portDist,null);
                    break;
                default:
                    System.out.println("comando desconhecido : " + args[0]);
                    help();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Ups! Ocorreu um erro!");
            help();
        }
    }
}
