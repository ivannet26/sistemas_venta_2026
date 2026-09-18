package proyecto_gm.Empleado;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyecto_gm.Area.Area;
import proyecto_gm.Area.DatosArea;
import proyecto_gm.TipoEmpleado.DatosTipoEmpleado;
import proyecto_gm.TipoEmpleado.TipoEmpleado;

public class frmListaEmpleado extends javax.swing.JInternalFrame {

    private final DatosEmpleado datosEmpleado = new DatosEmpleado();
    private final DefaultTableModel modelo = new DefaultTableModel(null,
            new String[]{"ID", "APELLIDOS", "NOMBRES", "FEC. N.", "SEXO", "CORREO", "DNI", "CELULAR", "DISTRITO", "DIRECCIÓN", "ÁREA", "CARGO", "TIPO EMPL."}) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    public frmListaEmpleado() {
        initComponents();
        cargarComboBox();
        cargarDatos();
        // Enter en el campo de búsqueda filtra. No agregar listeners a
        // btnBuscar/btnRefrescar aquí: ya están conectados en initComponents
        // y duplicarlos ejecuta cargarDatos() dos veces (filas duplicadas).
        txtBuscar.addActionListener(e -> cargarDatos());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblArea = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox<>();
        lblTipoEmpl = new javax.swing.JLabel();
        cmbTipoEmpleado = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("LISTAR");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Filtros"));

        lblBuscar.setText("Buscar:");

        btnBuscar.setText("Aceptar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblEstado.setText("Estado");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T", "A", "I" }));

        lblArea.setText("Área");

        lblTipoEmpl.setText("Tipo Empl.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblArea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipoEmpl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTipoEmpl)
                        .addComponent(cmbTipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblArea)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEstado)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "APELLIDOS", "NOMBRES", "FEC. NAC.", "CORREO", "DNI", "CELULAR", "DISTRITO", "DIRECCIÓN", "ÁREA", "CARGO", "TIPO EMPL.", "ESTADO"
            }
        ));
        tblEmpleados.setRowHeight(25);
        jScrollPane1.setViewportView(tblEmpleados);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnCancelar.setBackground(new java.awt.Color(242, 242, 242));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btnCancelar.setAlignmentX(0.5F);
        btnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCancelar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(242, 242, 242));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
        btnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAgregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(242, 242, 242));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnEditar.setAlignmentX(0.5F);
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEditar.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnRefrescar.setBackground(new java.awt.Color(242, 242, 242));
        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-refrescar-20.png"))); // NOI18N
        btnRefrescar.setAlignmentX(0.5F);
        btnRefrescar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnRefrescar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefrescar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefrescar)
                    .addComponent(btnEditar)
                    .addComponent(btnCancelar)
                    .addComponent(btnAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarDatos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        JDesktopPane desktopPane = getDesktopPane();

        if (desktopPane == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el contenedor principal (JDesktopPane).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        frmEmpleado formEmpleado = new frmEmpleado();
        desktopPane.add(formEmpleado);
        formEmpleado.setVisible(true);
        formEmpleado.toFront();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int fila = tblEmpleados.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int empleadoId = ((Number) tblEmpleados.getValueAt(fila, 0)).intValue();
        Object[] opciones = {"Sí", "No"};

        int confirm = JOptionPane.showOptionDialog(
                this,
                "¿Está seguro de eliminar este empleado?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (confirm == 0) {
            if (datosEmpleado.eliminar(empleadoId)) {
                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        limpiarFiltros();
        cargarDatos();
        JOptionPane.showMessageDialog(this, "Tabla actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void cargarDatos() {
        modelo.setRowCount(0);
        Object areaSel = cmbArea.getSelectedItem();
        Object tipoSel = cmbTipoEmpleado.getSelectedItem();

        List<EmpleadoDTO> empleados = datosEmpleado.listar(
                txtBuscar.getText(),
                (String) cmbEstado.getSelectedItem(),
                ((Area) areaSel).getIdArea(),
                ((TipoEmpleado) tipoSel).getIdTipoEmpleado());

        for (EmpleadoDTO e : empleados) {
            modelo.addRow(new Object[]{
                e.getIdEmpleado(),
                e.getApellidos(),
                e.getNombres(),
                e.getFechaNacimiento(),
                e.getSexo(),
                e.getCorreo(),
                e.getDni(),
                e.getCelular(),
                e.getDistrito(),
                e.getDireccion(),
                e.getArea(),
                e.getCargo(),
                e.getTipoEmpleado()
            });
        }
        tblEmpleados.setModel(modelo);
    }

    private void cargarComboBox() {
        try {
            DefaultComboBoxModel<Area> areas = new DefaultComboBoxModel<>();
            areas.addElement(new Area(0, "TODOS"));
            for (Area a : DatosArea.listar()) {
                areas.addElement(a);
            }
            cmbArea.setModel(areas);
            DefaultComboBoxModel<TipoEmpleado> tipos = new DefaultComboBoxModel<>();
            tipos.addElement(new TipoEmpleado(0, "TODOS"));
            for (TipoEmpleado t : DatosTipoEmpleado.listar()) {
                tipos.addElement(t);
            }
            cmbTipoEmpleado.setModel(tipos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar combobox: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFiltros() {
        txtBuscar.setText("");
        cmbArea.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        cmbTipoEmpleado.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox<Area> cmbArea;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<TipoEmpleado> cmbTipoEmpleado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTipoEmpl;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
