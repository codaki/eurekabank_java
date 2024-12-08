/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.edu.monster.view;

import ec.edu.monster.controller.MovimientoController;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author JOSE
 */
public class MovimientoView extends javax.swing.JFrame {

    /**
     * Creates new form MovimientosView
     */
    public MovimientoView() {
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

        txtCuenta = new javax.swing.JTextField();
        btnDepositar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCuenta.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));
        txtCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaActionPerformed(evt);
            }
        });
        getContentPane().add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 260, 40));

        btnDepositar.setBackground(new java.awt.Color(51, 153, 255));
        btnDepositar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnDepositar.setForeground(new java.awt.Color(255, 255, 255));
        btnDepositar.setText("Depositar");
        btnDepositar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });
        getContentPane().add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 540, 160, 40));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setText("N° de cuenta:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 150, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setText("Ingrese");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 150, -1));
        jLabel2.getAccessibleContext().setAccessibleName("Ingrese \ncuenta");

        btnBuscar.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 160, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 600, 430));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo2.png"))); // NOI18N
        background.setText(".");
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        MovimientoController controller = new MovimientoController();
        String cuenta = txtCuenta.getText();

        if (cuenta == null || cuenta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de cuenta válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        controller.cargarMovimientos(cuenta, this);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
       CuentaView deposito = new CuentaView();
       deposito.setVisible(true);
    }//GEN-LAST:event_btnDepositarActionPerformed

    private void txtCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaActionPerformed

    public JPanel crearCelda(String cuenta, String fecha, String movimiento, String descripcion, String tipo, String importe) {
        JPanel panelCelda = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40); 
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(bg);
                repaint();
            }
        };

        panelCelda.setLayout(new BorderLayout());
        panelCelda.setBackground(new Color(214, 209, 246));
        panelCelda.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panelCelda.setMaximumSize(new Dimension(550, 150));

        // Texto principal
        JLabel labelCuenta = new JLabel(cuenta);
        JLabel labelFecha = new JLabel(fecha);
        JLabel labelMovimiento = new JLabel(movimiento);
        JLabel labelDescripcion = new JLabel(descripcion);
        JLabel labelTipo = new JLabel(tipo);
        JLabel labelImporte = new JLabel(importe);
        labelCuenta.setFont(new Font("Arial", Font.BOLD, 14));
        labelImporte.setFont(new Font("Arial", Font.BOLD, 16));
        labelImporte.setForeground(new Color(45, 150, 255));

        // Contenedor de datos
        JPanel datosPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // Espacio entre filas y columnas
        datosPanel.setBackground(new Color(214, 209, 246));
        datosPanel.add(labelCuenta);
        datosPanel.add(labelDescripcion);
        datosPanel.add(labelFecha);
        datosPanel.add(labelTipo);
        datosPanel.add(labelMovimiento);
        datosPanel.add(labelImporte);

        // Agregar al panel de celda
        panelCelda.add(datosPanel, BorderLayout.CENTER);

        // Agregar borde con espacio entre celdas
        panelCelda.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10), // Espaciado entre celdas
                BorderFactory.createLineBorder(new Color(180, 180, 220), 2) // Borde de color claro
        ));

        return panelCelda;
    }

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
            java.util.logging.Logger.getLogger(MovimientoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovimientoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovimientoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovimientoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MovimientoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCuenta;
    // End of variables declaration//GEN-END:variables

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }
}
