
public class Server_ON_OFF extends javax.swing.JFrame {
    myServer obj;
    
    public Server_ON_OFF() {
        initComponents();
        setSize(500,500);
        
        btoff.setEnabled(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bton = new javax.swing.JButton();
        btoff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SERVER ON OFF");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 6, 490, 30);

        bton.setBackground(new java.awt.Color(0, 204, 51));
        bton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bton.setForeground(new java.awt.Color(255, 255, 255));
        bton.setText("SERVER ON");
        bton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonActionPerformed(evt);
            }
        });
        getContentPane().add(bton);
        bton.setBounds(170, 83, 120, 40);

        btoff.setBackground(new java.awt.Color(255, 0, 0));
        btoff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btoff.setForeground(new java.awt.Color(255, 255, 255));
        btoff.setText("SERVER OFF");
        btoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoffActionPerformed(evt);
            }
        });
        getContentPane().add(btoff);
        btoff.setBounds(170, 143, 120, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonActionPerformed
     
        try{
            obj= new myServer(9000);
            bton.setEnabled(false);
            btoff.setEnabled(true);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btonActionPerformed

    private void btoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoffActionPerformed
       obj.shutdown();
       bton.setEnabled(true);
       btoff.setEnabled(false);
    }//GEN-LAST:event_btoffActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server_ON_OFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server_ON_OFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server_ON_OFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server_ON_OFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Server_ON_OFF().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btoff;
    private javax.swing.JButton bton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
