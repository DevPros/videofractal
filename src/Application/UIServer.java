/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Network.FractalCalculatorServer;
import Network.Multicast.MulticastServer;
import Utils.Cli;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class UIServer extends javax.swing.JFrame {

    public static FractalCalculatorServer s = null;
    public static InetAddress groupAddress = null;

    public UIServer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        txt_distIP = new javax.swing.JTextField();
        btn_start = new javax.swing.JButton();
        bt_stopManual = new javax.swing.JButton();
        txt_distPort = new javax.swing.JTextField();
        txt_port = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextGroupAddress = new javax.swing.JTextField();
        jTextGroupPort = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        bt_autoDiscovery = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        bt_stopAuto = new javax.swing.JButton();
        panelSuport = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelIMG = new javax.swing.JPanel();
        l = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextDebug = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Network"));

        txt_distIP.setText("localhost");

        btn_start.setText("Manual Server");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        bt_stopManual.setText("Stop");
        bt_stopManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_stopManualActionPerformed(evt);
            }
        });

        txt_distPort.setText("5000");

        txt_port.setText("10001");

        jLabel14.setText("Dist. IP");

        jLabel15.setText("Server Port");

        jLabel16.setText("Dist. Port");

        jLabel1.setText("Group Address");

        jLabel2.setText("Group Port");

        jTextGroupAddress.setText("230.0.0.1");

        jTextGroupPort.setText("10000");

        bt_autoDiscovery.setText("Auto Discovery Server");
        bt_autoDiscovery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_autoDiscoveryActionPerformed(evt);
            }
        });

        bt_stopAuto.setText("Stop");
        bt_stopAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_stopAutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btn_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_stopManual, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txt_distIP, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_distPort, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_port)
                            .addComponent(jTextGroupPort)
                            .addComponent(jTextGroupAddress)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(bt_autoDiscovery)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_stopAuto)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextGroupAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextGroupPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_autoDiscovery)
                    .addComponent(bt_stopAuto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_distPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_distIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_stopManual)
                    .addComponent(btn_start))
                .addContainerGap())
        );

        l.setText("j");

        javax.swing.GroupLayout panelIMGLayout = new javax.swing.GroupLayout(panelIMG);
        panelIMG.setLayout(panelIMGLayout);
        panelIMGLayout.setHorizontalGroup(
            panelIMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
        panelIMGLayout.setVerticalGroup(
            panelIMGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(l, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelIMG);

        javax.swing.GroupLayout panelSuportLayout = new javax.swing.GroupLayout(panelSuport);
        panelSuport.setLayout(panelSuportLayout);
        panelSuportLayout.setHorizontalGroup(
            panelSuportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );
        panelSuportLayout.setVerticalGroup(
            panelSuportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Debug"));

        jTextDebug.setBackground(new java.awt.Color(0, 0, 0));
        jTextDebug.setColumns(20);
        jTextDebug.setForeground(new java.awt.Color(255, 255, 255));
        jTextDebug.setRows(5);
        jScrollPane2.setViewportView(jTextDebug);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(panelSuport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSuport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão para parar serviços em execução
     * @param evt 
     */
    private void bt_stopManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_stopManualActionPerformed
        stop();
    }//GEN-LAST:event_bt_stopManualActionPerformed
    
    /**
     * Botão que inicia o servidor sem multicast
     * @param evt 
     */
    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        boolean serverStatus;
        String distIP = txt_distIP.getText();
        int distPort = Integer.valueOf(txt_distPort.getText());
        int serverPort = Integer.parseInt(txt_port.getText());
        // inicia Servidor
        serverStatus = startServer(false, distIP, distPort, serverPort);
        
        if (!serverStatus){
            // Muda estado dos botões
            bt_stopManual.setEnabled(true);
            btn_start.setEnabled(false);
            bt_autoDiscovery.setEnabled(false);
        }
    }//GEN-LAST:event_btn_startActionPerformed
    
    /**
     * Botão que inicia servidor com multicast
     * @param evt 
     */
    private void bt_autoDiscoveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_autoDiscoveryActionPerformed
        int groupPort = Integer.valueOf(jTextGroupPort.getText()); // porta do grupo
        int serverPort = Integer.parseInt(txt_port.getText()); // porta de trabalho do server
        String groupAddress = jTextGroupAddress.getText(); // Endereço do grupo
        boolean serverStatus;
 
        // inicia server
        serverStatus = startServer(true, groupAddress, groupPort, serverPort);
        
        if (!serverStatus) {
            // Muda estado dos botões
            bt_stopManual.setEnabled(true);
            btn_start.setEnabled(false);
            bt_autoDiscovery.setEnabled(false);
        }
    }//GEN-LAST:event_bt_autoDiscoveryActionPerformed
    
    /**
     * Botão para parar serviços em execução
     * @param evt 
     */
    private void bt_stopAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_stopAutoActionPerformed
        stop();
    }//GEN-LAST:event_bt_stopAutoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) { 
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Se existirem argumentos, não lança GUI
                if (args.length <= 0){
                    new UIServer().setVisible(true);
                } else {
                    new Cli(args).parse(); //lança o parse
                }
            }
        });
    }
    
    /**
     * Função que pára todos os serviços
     */
    private void stop()
    {
        if (s != null) {
            if (s.isAlive()) {
                if (s.isInterrupted() == false) {
                    bt_autoDiscovery.setEnabled(true);
                    bt_stopManual.setEnabled(false);
                    s.interrupt();
                    s = null;
                }
            }
        }
    }
    
    /**
     * Função para iniciar os serviços do servidor
     * @param multi
     * @param distIP     IP Distribuição / multicast
     * @param distPort   Porto Distribuição / multicast
     * @param serverPort Porto do servidor
     * @return 
     */
    public static boolean startServer(boolean multi, String distIP, int distPort, int serverPort)
    {
        boolean err = false;
        // se o serviço não estiver a correr 
        if (s == null) {
            
            // lançar server multicast
            if (multi){
                InetAddress groupAddress = null;
                String distData;
                try {
                    groupAddress = InetAddress.getByName(distIP); //Endereço do grupo
                } catch (UnknownHostException ex) {
                    Logger.getLogger(UIDistributor.class.getName()).log(Level.SEVERE, null, ex);
                }
                //obtem a porta do server com multicast
                distData = MulticastServer.listenMulticast(distPort, groupAddress, jTextDebug);
                // separa o ip e a porta
                String[] dados = distData.split(",");
                // limpa e faz o parse
                distPort = Integer.parseInt(dados[1].trim());
                // retira a / do ip
                distIP = dados[0].replace("/", "");
            }
            
            // lança server
            try {
                Socket dist = new Socket(distIP, distPort);
                // abertura da stream de saída
                ObjectOutputStream out = new ObjectOutputStream(dist.getOutputStream());
                //abertura da stream de entrada
                ObjectInputStream in = new ObjectInputStream(dist.getInputStream());
                // envia porta em que a instancia está a correr
                out.writeInt(serverPort);
                // try catch CLI
                try {
                    jTextDebug.append("[Server] Sending port " + serverPort + " to distributor \n");
                } catch (Exception e) {
                    System.out.println("[Server] Sending port " + serverPort + " to distributor \n");
                }
                out.close();
                in.close();
                dist.close();
                
            } catch (ConnectException ex) {
                // try catch CLI
                try{
                    jTextDebug.append("[Server] Cannot establish connection with distributor \n");
                } catch (Exception e) {
                    System.out.println("[Server] Cannot establish connection with distributor \n");
                }
                
                err = true;
            } catch (Exception ex) {
                err = true;
                // try catch CLI
                try{
                    jTextDebug.append("[Server] An error has occurred \n");
                } catch (Exception e) {
                    System.out.println("[Server] An error has occurred \n");
                }
                Logger.getLogger(UIServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!err){
                //Inicia enviando a gui e a porta
                s = new FractalCalculatorServer(l,jTextDebug ,serverPort);
                //Inicia o server
                s.start();
            }
	}
        return err;
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_autoDiscovery;
    private javax.swing.JButton bt_stopAuto;
    private javax.swing.JButton bt_stopManual;
    private javax.swing.JButton btn_start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JTextArea jTextDebug;
    private static javax.swing.JTextField jTextGroupAddress;
    private static javax.swing.JTextField jTextGroupPort;
    public static javax.swing.JLabel l;
    public javax.swing.JPanel panelIMG;
    private javax.swing.JPanel panelSuport;
    private javax.swing.JTextField txt_distIP;
    private javax.swing.JTextField txt_distPort;
    private static javax.swing.JTextField txt_port;
    // End of variables declaration//GEN-END:variables
}
