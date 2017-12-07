/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMD;

import GUI.GUIDistributor;
import GUI.GUIServer;
import Network.FractalCalculatorServer;
import Network.Multicast.MulticastServer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Server {

    private static FractalCalculatorServer s = null;
    public static InetAddress groupAddress = null;

    /**
     * Communica de IP para IP
     *
     * @param serverPort Porta do servidor
     * @param distIp IP da Distribuição
     * @param distPort Porta da Distribuição
     */
    public static void unicast(int serverPort, String distIp, int distPort) {
        if (s == null) {
            try {
                Socket dist = new Socket(distIp, distPort);
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(dist.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(dist.getInputStream());
                // envia porta em que a instancia está a correr
                out.writeInt(serverPort);
                System.out.println("[Server] Sending port " + serverPort + " to dist");
                out.close();
                in.close();
                dist.close();
            } catch (Exception ex) {
                System.out.println("Enganou-se a introduzir os dados");
            }
            s = new FractalCalculatorServer(null, serverPort);

            s.start();

        }
    }

    /**
     * Faz o multicast
     *
     * @param serverPort Porta do Servidor
     * @param groupAddr Endereço de Grupo
     * @param groupPort Porta de Grupo
     */
    public static void multicast(int serverPort, String groupAddr, int groupPort) {
        InetAddress groupAddress = null;
        String distPort;
        try {
            groupAddress = InetAddress.getByName(groupAddr); //Endereço do grupo
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUIDistributor.class.getName()).log(Level.SEVERE, null, ex);
        }

        //obtem a porta do server com multicast
        distPort = MulticastServer.listenMulticast(groupPort, groupAddress, null);

        String[] dados = distPort.split(",");
        // limpa e faz o parse
        int porta = Integer.parseInt(dados[1].trim());
        // retira a / do ip
        dados[0] = dados[0].replace("/", "");
        //System.out.println("porta: "+porta);
        //System.out.println("ip: "+dados[0]);

        // inicia server
        if (s == null) {
            try {
                //Socket dist = new Socket(txt_distIP.getText(),Integer.valueOf(txt_distPort.getText())) ;
                Socket dist = new Socket(dados[0], porta);
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(dist.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(dist.getInputStream());
                // envia porta em que a instancia está a correr
                out.writeInt(serverPort);
                System.out.println("[Server] Sending port " + serverPort + " to dist");
                out.close();
                in.close();
                dist.close();
            } catch (Exception ex) {
                Logger.getLogger(GUIServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Inicia enviando a gui e a porta
            s = new FractalCalculatorServer(null, serverPort);
            //Inicia o server
            s.start();
        }
    }

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
                    unicast(portServer, ip, portDist);
                    break;
                case "multicast":
                    multicast(portServer, ip, portDist);
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
