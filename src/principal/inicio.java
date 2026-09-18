package principal;

//import Actualizador.Actualizador;
//import Actualizador.Configurador;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;
import java.sql.CallableStatement;
import java.util.Locale;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class inicio extends javax.swing.JFrame {

    public static boolean esModoDev = false;
    int intentos;
    String nombreEquipo = "";
    String macAddress = "";
    static Connection conn = ConexionBD.getConnection();
    public static String macAddressCalculada = "";
    public static String nombreEquipoCalculado = "";
    
    public inicio() {
        this(false);
        
    }
    
    public inicio(boolean modoDev) {
        cargarModoDev();
        inicio.esModoDev = modoDev || inicio.esModoDev;
        initComponents();
        if (!inicio.esModoDev) {
            obtenerDatosEquipo();
        }
    }
    
    public static void cargarModoDev() {
      try {
          Path path = Paths.get("proyect_gm.config.json");
          String contenido = Files.readString(path).trim();
          int idx = contenido.indexOf("\"modo_desarrollador\"");
          if (idx >= 0) {
              int idxTrue = contenido.indexOf("true", idx);
              int idxFalse = contenido.indexOf("false", idx);
              if (idxTrue >= 0 && (idxFalse < 0 || idxTrue < idxFalse)) {
                  inicio.esModoDev = true;
              } else {
                  inicio.esModoDev = false;
              }
          } else {
              inicio.esModoDev = false;
          }
          System.out.println("ModoDev cargado: " + inicio.esModoDev);
      } catch (Exception e) {
          inicio.esModoDev = false;
      }
  }



   private void obtenerDatosEquipo() {
        if (inicio.esModoDev) return; 
        try {
            InetAddress ip = InetAddress.getLocalHost();
            nombreEquipoCalculado = ip.getHostName(); 
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                macAddressCalculada = sb.toString();
            }
            
            System.out.println("Datos obtenidos -> PC: " + nombreEquipoCalculado + " | MAC: " + macAddressCalculada);
        } catch (Exception e) {
        }
    }
    
    public void ingresar() {
        menu contenedor = new menu();
        this.dispose();
        contenedor.setExtendedState(Frame.MAXIMIZED_BOTH);
        contenedor.setVisible(true);
    }
    
   private void ejecutarValidacion() {
        String user = txtusuario.getText();
        String pass = new String(txtcontrasena.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Llenar completamente los campos");
            return;
        }

        int idUsuarioValidado = 0;
        try {
            Connection con = ConexionBD.getConnection();
            String sql = "SELECT IdUsuario FROM usuarios WHERE username = ? AND pass = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass); 
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idUsuarioValidado = rs.getInt("IdUsuario");
            }

        } catch (SQLException e) {
        }

        if (idUsuarioValidado > 0) {
            TraerPerfil(user);
            ConexionBD.nomUsuario = user;

//            if (!inicio.esModoDev) {
//                //proyecto_gm.Mac.DatosMac control = new proyecto_gm.Mac.DatosMac();
//                String resultado = control.validarAcceso(idUsuarioValidado);
//                if (!resultado.equals("PERMITIDO") && !resultado.equals("PERMITIDO_NUEVO")) {
//                    JOptionPane.showMessageDialog(this, "Acceso denegado: " + resultado);
//                    return;
//                }
//            }

            ingresar();

        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }

    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuario = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtcontrasena = new javax.swing.JPasswordField();
        contraseña = new javax.swing.JLabel();
        btningresar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GM SISTEMAS");
        setBackground(new java.awt.Color(0, 0, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(290, 140));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        usuario.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        usuario.setText("usuario:");
        getContentPane().add(usuario);
        usuario.setBounds(30, 20, 50, 20);

        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtusuario);
        txtusuario.setBounds(90, 10, 170, 30);

        txtcontrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontrasenaActionPerformed(evt);
            }
        });
        txtcontrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontrasenaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontrasenaKeyTyped(evt);
            }
        });
        getContentPane().add(txtcontrasena);
        txtcontrasena.setBounds(90, 40, 170, 30);

        contraseña.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        contraseña.setText("contraseña:");
        getContentPane().add(contraseña);
        contraseña.setBounds(20, 50, 61, 17);

        btningresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btningresar.setText("INGRESAR");
        btningresar.setMaximumSize(new java.awt.Dimension(200, 28));
        btningresar.setMinimumSize(new java.awt.Dimension(90, 28));
        btningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresarActionPerformed(evt);
            }
        });
        getContentPane().add(btningresar);
        btningresar.setBounds(90, 70, 100, 26);

        btnsalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir);
        btnsalir.setBounds(190, 70, 71, 26);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcontrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontrasenaActionPerformed
      
    }//GEN-LAST:event_txtcontrasenaActionPerformed
   void TraerPerfil(String nombreUsuario){
        try{
            CallableStatement cstmt = conn.prepareCall("{call listar_perfilxusuario(?,?)}");
            cstmt.setString(1, "01"); 
            cstmt.setString(2, nombreUsuario);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()){
                ConexionBD.codPerfil = rs.getString("codperfil");
                ConexionBD.nomPerfil = rs.getString("nomperfil");
            }
        }catch(SQLException exSQL){
            JOptionPane.showMessageDialog(null,exSQL.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
 

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
    
        ejecutarValidacion();
        
    }//GEN-LAST:event_btningresarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtusuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyTyped
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtcontrasena.requestFocus(); 
        }
    }//GEN-LAST:event_txtusuarioKeyTyped

    private void txtcontrasenaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontrasenaKeyTyped
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           
            ejecutarValidacion();
        }
    }//GEN-LAST:event_txtcontrasenaKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.txtusuario.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void txtcontrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontrasenaKeyPressed
      if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            ejecutarValidacion();
        }
    }//GEN-LAST:event_txtcontrasenaKeyPressed

    private void txtusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            txtcontrasena.requestFocus();
        }
    }//GEN-LAST:event_txtusuarioKeyPressed

    /**
     * @param args the command line arguments
     */
   
   public static void main(String args[]) {
      
       
        // 1. Configurar el Look and Feel antes de iniciar la UI
            try {
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("No se pudo establecer el estilo visual: " + e.getMessage());
            }

//            String modoJSON = Configurador.getDato("modo_desarrollador");
//            inicio.esModoDev = "true".equalsIgnoreCase(modoJSON);

            java.awt.EventQueue.invokeLater(() -> {
                inicio ventana;
                ventana = new inicio(true);
                
//                if (inicio.esModoDev) {
//                    
//                } else {
//                    Actualizador actualizar = new Actualizador();
//                    actualizar.comprobarNuevaVersion();
//                    ventana = new inicio(false);
//                }

                // No es estrictamente necesario updateComponentTreeUI si lo pones al inicio,
                // pero ayuda si la ventana ya estaba instanciada.
                javax.swing.SwingUtilities.updateComponentTreeUI(ventana);
                ventana.setVisible(true);
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btningresar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel contraseña;
    private javax.swing.JPasswordField txtcontrasena;
    private javax.swing.JTextField txtusuario;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
