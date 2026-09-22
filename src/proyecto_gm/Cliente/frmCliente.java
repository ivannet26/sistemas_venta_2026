package proyecto_gm.Cliente;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import principal.Exportar;

public class frmCliente extends javax.swing.JInternalFrame {

    private static frmCliente instancia;
    DefaultTableModel modelo;
    boolean esNuevo = false;
    List<Cliente> listaCliente;

    public frmCliente() {
        initComponents();
        setTitle("CLIENTE");
        modelo = new DefaultTableModel(null, new String[]{"ID", "Nombre Empresa", "Proyecto", "Tipo Cliente"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCliente.setModel(modelo);
        cargarDatos();
        configurarEstadoInicial();
    }

    public frmCliente getInstancia() {
        if (instancia == null) {
            instancia = new frmCliente();
        }
        return instancia;
    }

    @Override
    public void dispose() {
        super.dispose();
        instancia = null;
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        listaCliente = DatosClientes.listar(null);
        for (Cliente c : listaCliente) {
            modelo.addRow(new Object[]{c.getIdCliente(), c.getNombreEmpresa(), c.getProyecto(), c.getTipoCliente()});
        }
    }

    private void configurarEstadoInicial() {
        txtId.setEnabled(false);
        txtNomEmp.setEnabled(false);
        txtProyecto.setEnabled(false);
        txtTipoCliente.setEnabled(false);

        btnAgregar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnDeshacer.setEnabled(false);

        limpiarCampos();
    }

    private void configurarEstadoFormulario() {
        txtId.setEnabled(false);
        txtNomEmp.setEnabled(true);
        txtProyecto.setEnabled(true);
        txtTipoCliente.setEnabled(true);

        btnAgregar.setEnabled(false);

        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnDeshacer.setEnabled(true);
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNomEmp.setText("");
        txtProyecto.setText("");
        txtTipoCliente.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNomEmp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTipoCliente = new javax.swing.JTextField();
        txtProyecto = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Empresa", "Proyecto", "Tipo Cliente"
            }
        ));
        jScrollPane2.setViewportView(tblCliente);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setFocusCycleRoot(true);

        jLabel1.setText("Proyecto:");

        jLabel2.setText("ID:");

        txtId.setColumns(10);

        jLabel3.setText("Empresa:");

        jLabel4.setText("Tipo Cliente:");

        txtProyecto.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtProyecto))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator1);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
        btnAgregar.setToolTipText("Nuevo");
        btnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAgregar.setFocusable(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAgregar);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-doc-25.png"))); // NOI18N
        btnExportar.setToolTipText("exportar datos");
        btnExportar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExportar.setFocusable(false);
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExportar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        esNuevo = true;
        limpiarCampos();
        configurarEstadoFormulario();
        setTitle("NUEVO CLIENTE");
        txtNomEmp.requestFocus();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tblCliente.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        esNuevo = false;
        txtId.setText(Objects.toString(tblCliente.getValueAt(fila, 0), ""));
        txtNomEmp.setText(Objects.toString(tblCliente.getValueAt(fila, 1), ""));
        txtProyecto.setText(Objects.toString(tblCliente.getValueAt(fila, 2), ""));
        txtTipoCliente.setText(Objects.toString(tblCliente.getValueAt(fila, 3), ""));
        configurarEstadoFormulario();
        setTitle("EDITAR CLIENTE");
        txtNomEmp.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblCliente.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = Integer.parseInt(tblCliente.getValueAt(fila, 0).toString());
        Object[] opciones = {"Sí", "No"};
        int confirm = JOptionPane.showOptionDialog(this, "¿Está seguro de eliminar este cliente?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (confirm == 0) {
            DatosClientes.eliminar(id);
            cargarDatos();
            configurarEstadoInicial();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtNomEmp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo nombre empresa es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtNomEmp.requestFocus();
            return;
        }
        if (txtProyecto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo proyecto es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtProyecto.requestFocus();
            return;
        }
        if (txtTipoCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo tipo cliente es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtTipoCliente.requestFocus();
            return;
        }
        Cliente cliente = new Cliente();
        cliente.setNombreEmpresa(txtNomEmp.getText().trim());
        cliente.setProyecto(txtProyecto.getText().trim());
        cliente.setTipoCliente(txtTipoCliente.getText().trim());
        if (esNuevo) {
            DatosClientes.insertar(cliente);
        } else {
            cliente.setIdCliente(Integer.parseInt(txtId.getText()));
            DatosClientes.actualizar(cliente);
        }
        cargarDatos();
        limpiarCampos();
        configurarEstadoInicial();
        setTitle("CLIENTE");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
        limpiarCampos();
        configurarEstadoInicial();
        setTitle("CLIENTE");
    }//GEN-LAST:event_btnDeshacerActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        try {
            Exportar obj = new Exportar();
            obj.exportarExcel(tblCliente);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al exportar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNomEmp;
    private javax.swing.JTextField txtProyecto;
    private javax.swing.JTextField txtTipoCliente;
    // End of variables declaration//GEN-END:variables
}
