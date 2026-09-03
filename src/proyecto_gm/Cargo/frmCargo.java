package proyecto_gm.Cargo;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import principal.Utilitario;


public class frmCargo extends javax.swing.JInternalFrame {
    
    private static frmCargo instancia;
    
    private final DatosCargo datosCargo = new DatosCargo();
    private boolean esNuevo = false;
    private javax.swing.table.TableRowSorter<DefaultTableModel> sorter;


    public frmCargo() {
        initComponents();
        cargarTabla();
        habilitarControles(false);
        habilitarBotonesMantenimiento(true);
        
        // Inicializar filtro
        DefaultTableModel modelo = (DefaultTableModel) tblCargo.getModel();
        sorter = new javax.swing.table.TableRowSorter<>(modelo);
        tblCargo.setRowSorter(sorter);

        // Buscar al presionar Enter
        txtBusqueda.addActionListener(e -> filtrarCargo(txtBusqueda.getText().trim()));
    }
    
    
    public static frmCargo getInstancia() {
        if (instancia == null) {
            instancia = new frmCargo();
        }
        return instancia;
    }

    @Override
    public void dispose() {
        super.dispose();
        instancia = null;
    }
    
    // Carga los datos de la base de datos en la tabla
     private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblCargo.getModel();
        modelo.setRowCount(0);

        try {
            List<Cargo> listaCargos = datosCargo.listarCargo();
            for (Cargo cargo : listaCargos) {
                Object[] fila = {
                    cargo.getIdCargo(),
                    cargo.getDescripcion()
                };
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            Utilitario.MostrarMensaje("Error al cargar los datos: " + ex.getMessage(), Utilitario.TipoMensaje.error);
        }
    }

    // Limpia los campos del formulario
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtDescripcion.setText("");
    }
    
    // Habilita o deshabilita los controles del formulario
    private void habilitarControles(boolean estado) {
        txtCodigo.setEnabled(false);
        txtDescripcion.setEnabled(estado);
    }
    
    // Habilita o deshabilita los botones de mantenimiento y de acción
    private void habilitarBotonesMantenimiento(boolean estado) {
        btnAgregar.setEnabled(estado);
        btnEditar.setEnabled(estado);
        btnEliminar.setEnabled(estado);
        btnGuardar.setEnabled(!estado);
        btnDeshacer.setEnabled(!estado);
        tblCargo.setEnabled(estado); 
    }
    
    // Valida que los campos obligatorios no estén vacíos
    private boolean validarCampos() {
        if (txtDescripcion.getText().trim().isEmpty()) {
            Utilitario.MostrarMensaje("El campo descripción es obligatorio.", Utilitario.TipoMensaje.alerta);
            txtDescripcion.requestFocus();
            return false;
        }
        return true;
    }
    
    // Restaura la vista a su estado inicial después de una operación
    private void finalizarOperacion() {
        limpiarCampos();
        habilitarControles(false);
        habilitarBotonesMantenimiento(true);
        cargarTabla();
        esNuevo = false;
    }
    
    private void filtrarCargo(String texto) {
        try {
            if (texto.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                // Busca en la columna 1 (Descripción)
                sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i).*" + java.util.regex.Pattern.quote(texto) + ".*", 1));
            }
        } catch (java.util.regex.PatternSyntaxException ex) {
            System.err.println("Error en el filtro: " + ex.getMessage());
        }
    }
    
    private void configurarEstadoInicial() {
        // Habilitar/deshabilitar campos
        txtCodigo.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtBusqueda.setEnabled(true);

        // Habilitar/deshabilitar botones
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnDeshacer.setEnabled(false);
        
        // Limpiar campos
        limpiarCampos();
        
        // Permitir selección en la tabla
        tblCargo.setEnabled(true);
        tblCargo.clearSelection();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCargo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setClosable(true);
        setIconifiable(true);
        setTitle("CARGO");
        setMaximumSize(new java.awt.Dimension(500, 312));
        setMinimumSize(new java.awt.Dimension(500, 312));
        setPreferredSize(new java.awt.Dimension(500, 312));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Id:");

        jLabel2.setText("Descripción:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jToolBar1.setRollover(true);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
        btnAgregar.setToolTipText("Nuevo");
        btnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAgregar.setMaximumSize(new java.awt.Dimension(30, 29));
        btnAgregar.setMinimumSize(new java.awt.Dimension(30, 29));
        btnAgregar.setName("agregar"); // NOI18N
        btnAgregar.setPreferredSize(new java.awt.Dimension(30, 29));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAgregar);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setMaximumSize(new java.awt.Dimension(30, 29));
        btnEditar.setMinimumSize(new java.awt.Dimension(30, 29));
        btnEditar.setName("editar"); // NOI18N
        btnEditar.setPreferredSize(new java.awt.Dimension(30, 29));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setMaximumSize(new java.awt.Dimension(30, 29));
        btnEliminar.setMinimumSize(new java.awt.Dimension(30, 29));
        btnEliminar.setName("eliminar"); // NOI18N
        btnEliminar.setPreferredSize(new java.awt.Dimension(30, 29));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setMaximumSize(new java.awt.Dimension(30, 29));
        btnGuardar.setMinimumSize(new java.awt.Dimension(30, 29));
        btnGuardar.setName("guardar"); // NOI18N
        btnGuardar.setPreferredSize(new java.awt.Dimension(30, 29));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        btnDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/regresar.png"))); // NOI18N
        btnDeshacer.setToolTipText("Cancelar");
        btnDeshacer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDeshacer.setFocusable(false);
        btnDeshacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeshacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDeshacer);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tblCargo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblCargo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCargo.setEnabled(false);
        jScrollPane1.setViewportView(tblCargo);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Buscar");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });

        btnBuscar.setText("Aceptar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(btnBuscar))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblCargo.getSelectedRow();
        if (fila >= 0) {
            
            Object[] opciones = {"Sí", "No"};

          
            int confirmacion = javax.swing.JOptionPane.showOptionDialog(
                this,
                "¿Está seguro de eliminar este cargo?",
                "Confirmar Eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE, 
                null,
                opciones,
                opciones[0] 
            );

            
            if (confirmacion == 0) { 
                try {
                    int idCargo = Integer.parseInt(tblCargo.getValueAt(fila, 0).toString());
                    boolean eliminado = datosCargo.eliminar(idCargo);
                    if (eliminado) {
                        javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Cargo eliminado correctamente.",
                            "Información",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                        );
                        cargarTabla(); 
                    }
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "El ID del cargo no es válido.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                } catch (SQLException ex) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Error al eliminar el cargo: " + ex.getMessage(),
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Debe seleccionar una fila para eliminar.",
                "Advertencia",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = tblCargo.getSelectedRow();
        if (filaSeleccionada == -1) {
            Utilitario.MostrarMensaje("Seleccione un registro para editar.", Utilitario.TipoMensaje.alerta);
            return;
        }

        esNuevo = false;
        
        // Obtener datos de la tabla y cargarlos en los campos
        String id = tblCargo.getValueAt(filaSeleccionada, 0).toString();
        String descripcion = tblCargo.getValueAt(filaSeleccionada, 1).toString();
        
        txtCodigo.setText(id);
        txtDescripcion.setText(descripcion);
        
        habilitarControles(true);
        habilitarBotonesMantenimiento(false);
        txtDescripcion.requestFocus();
        txtBusqueda.setEnabled(false);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // Genera codigo automaticos IdCargo es auto_increment
        txtCodigo.setText("");
        txtCodigo.setEnabled(false);
        habilitarControles(true);
        txtDescripcion.requestFocus();
        habilitarBotonesMantenimiento(false);
        esNuevo = true;
        tblCargo.setRowSelectionAllowed(false);
        txtBusqueda.setEnabled(false);
    }//GEN-LAST:event_btnAgregarActionPerformed
    void cancelar(){
     finalizarOperacion();
        txtBusqueda.setEnabled(true);
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!validarCampos()) {
            return;
        }

        try {
            Cargo cargo = new Cargo();
            cargo.setDescripcion(txtDescripcion.getText().trim());

            boolean resultado;
            String mensaje;

            if (esNuevo) {
                resultado = datosCargo.insertar(cargo);
                mensaje = "Cargo registrado correctamente.";
            } else {
                cargo.setIdCargo(Integer.parseInt(txtCodigo.getText()));
                resultado = datosCargo.actualizar(cargo);
                mensaje = "Cargo actualizado correctamente.";
            }

            if (resultado) {
                Utilitario.MostrarMensaje(mensaje, Utilitario.TipoMensaje.informativo);
                finalizarOperacion();
            }

        } catch (NumberFormatException e) {
            Utilitario.MostrarMensaje("Error: el código del cargo no es un número válido.", Utilitario.TipoMensaje.error);
        } catch (SQLException ex) {
            Utilitario.MostrarMensaje("Error al guardar los datos: " + ex.getMessage(), Utilitario.TipoMensaje.error);
        }
        txtBusqueda.setEnabled(true);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        filtrarCargo(txtBusqueda.getText().trim());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        if(evt.getKeyChar()== KeyEvent.VK_ENTER){
            if(!txtBusqueda.getText().trim().equals("")){
                filtrarCargo(txtBusqueda.getText());
            }else{
                filtrarCargo("");
            }
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
       configurarEstadoInicial();
    }//GEN-LAST:event_btnDeshacerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JTable tblCargo;
    private javax.swing.JTextField txtBusqueda;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
