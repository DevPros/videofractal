/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static Application.UIServer.startServer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Adaptado de http://www.thinkplexx.com/blog/simple-apache-commons-cli-example-java-command-line-arguments-parsing
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Cli {
    private String[] args = null;
    private Options options = new Options();
    
    
            
    /**
     * Construtor da CLI que recebe argumenos e define as opções disponíveis
     * @param args argumentos passados da interface
     */
    public Cli(String[] args) {
        this.args = args;
        Option help = new Option("h", "help", false, "print this message" );
        options.addOption(help);
        
        Option serverPort = new Option("p", "port", true, "server working port");
        serverPort.setRequired(true);
        options.addOption(serverPort);

        Option networkType = new Option("n", "network", true, "choose between unicast or multicast");
        networkType.setRequired(true);
        options.addOption(networkType);
        
        Option multiAddress = new Option("a", "address", true, "the group or distributor address");
        multiAddress.setRequired(true);
        options.addOption(multiAddress);
        
        Option multiPort = new Option("d", "dport", true, "the group or distributor port");
        multiPort.setRequired(true);
        options.addOption(multiPort);
    }
    
    /**
     * Efetua o parse dos argumentos passados à interface
     */
    public void parse(){
        int sPort; // porta server
        String dAddress; // endereço distribuidor
        int dPort; // porta distribuidor
    
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        
        try {
            cmd = parser.parse(options, args);
            
            // ajuda
            if (cmd.hasOption("h"))
                help();
            
            sPort = Integer.valueOf(cmd.getOptionValue("port"));
            dAddress = cmd.getOptionValue("address");
            dPort = Integer.valueOf(cmd.getOptionValue("dport"));
            
            switch (cmd.getOptionValue("network")){
                case "multicast":
                    startServer(true, dAddress, dPort, sPort);
                    break;
                case "unicast":
                    startServer(false, dAddress, dPort, sPort);
                    break;
                default:
                    help();
                    break;
            }
            
        } catch (ParseException e) {
            help();
        }
    }
    
    /**
     * Mostra a mensagem de ajuda
     */
    private void help()
    {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("GUIServer", options);
        System.exit(0);
    }
}
