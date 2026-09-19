package proyecto_gm.Empleado;

import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import proyecto_gm.Area.Area;
import proyecto_gm.Area.DatosArea;
import proyecto_gm.Cargo.Cargo;
import proyecto_gm.Cargo.DatosCargo;

public class frmEmpleado extends javax.swing.JInternalFrame {

    DefaultComboBoxModel<Area> areaModelo = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Cargo> cargoModelo = new DefaultComboBoxModel<>();
    int empleadoId = 0;

    DatosCargo datosCargo = new DatosCargo();

    private static final String[] DISTRITOS_LIMA = {
        "Cercado de Lima", "Ancón", "Ate", "Barranco", "Breña",
        "Carabayllo", "Chaclacayo", "Chorrillos", "Cieneguilla", "Comas",
        "El Agustino", "Independencia", "Jesús María", "La Molina",
        "La Victoria", "Lince", "Los Olivos", "Lurigancho", "Lurín",
        "Magdalena del Mar", "Miraflores", "Pachacámac", "Pucusana",
        "Pueblo Libre", "Puente Piedra", "Punta Hermosa", "Punta Negra",
        "Rímac", "San Bartolo", "San Borja", "San Isidro",
        "San Juan de Lurigancho", "San Juan de Miraflores", "San Luis",
        "San Martín de Porres", "San Miguel", "Santa Anita",
        "Santa María del Mar", "Santa Rosa", "Santiago de Surco",
        "Surquillo", "Villa El Salvador", "Villa María del Triunfo"
    };

    public frmEmpleado() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cargarCombos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbDistrito = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox<>();
        cmbCargo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        cmbMes = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        rbCompleto = new javax.swing.JRadioButton();
        rbPartTime = new javax.swing.JRadioButton();
        rbPracticante = new javax.swing.JRadioButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("EMPLEADOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnLimpiar.setBackground(new java.awt.Color(242, 242, 242));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/limpiar.png"))); // NOI18N
        btnLimpiar.setAlignmentX(0.5F);
        btnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLimpiar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLimpiar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(242, 242, 242));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        btnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(242, 242, 242));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/regresar.png"))); // NOI18N
        btnRegresar.setAlignmentX(0.5F);
        btnRegresar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRegresar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnRegresar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales"));

        txtApellidos.setToolTipText("");

        jLabel1.setText("Apellidos:");

        jLabel2.setText("Nombres:");

        jLabel3.setText("Fec. Nac:");

        jLabel9.setText("Dirección:");

        jLabel6.setText("DNI:");

        jLabel8.setText("Celular:");

        jLabel5.setText("Correo:");

        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("yyyy/mm/dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtFechaNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Laborales"));

        cmbDistrito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Distrito:");

        jLabel13.setText("Area:");

        jLabel14.setText("Cargo:");

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));

        jLabel4.setText("Sexo:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "T", "A", "I" }));

        jLabel10.setText("Estado:");

        jLabel12.setText("Mes:");

        jLabel11.setText("Año:");

        cmbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAnio)
                                .addComponent(cmbMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Empleado"));

        buttonGroup1.add(rbCompleto);
        rbCompleto.setText("Completo");

        buttonGroup1.add(rbPartTime);
        rbPartTime.setText("Part-time");
        rbPartTime.setToolTipText("");

        rbPracticante.setText("Practicante");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCompleto)
                    .addComponent(rbPartTime)
                    .addComponent(rbPracticante))
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(rbCompleto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbPartTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbPracticante)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtApellidos.getText().trim().isEmpty()
                || txtNombres.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Apellidos y Nombres son requeridos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Empleado empleado = new Empleado();

            empleado.setApellidos(txtApellidos.getText().trim());
            empleado.setNombres(txtNombres.getText().trim());
            String fecNac = txtFechaNacimiento.getText().trim();
            empleado.setFechaNacimiento(fecNac.isEmpty() ? null : java.sql.Date.valueOf(fecNac));
            String dni = txtDni.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String celular = txtCelular.getText().trim();
            String correo = txtCorreo.getText().trim();
            String anio = txtAnio.getText().trim();
            empleado.setDni(dni.isEmpty() ? null : dni);
            empleado.setDireccion(direccion.isEmpty() ? null : direccion);
            empleado.setCelular(celular.isEmpty() ? null : celular);
            empleado.setCorreo(correo.isEmpty() ? null : correo);
            empleado.setAnio(anio.isEmpty() ? null : anio);

            empleado.setSexo(cmbSexo.getSelectedIndex() <= 0 ? null : (String) cmbSexo.getSelectedItem());
            empleado.setEstado((String) cmbEstado.getSelectedItem());
            empleado.setDistrito(cmbDistrito.getSelectedIndex() <= 0 ? null : (String) cmbDistrito.getSelectedItem());
            empleado.setMes(cmbMes.getSelectedIndex() <= 0 ? null : (String) cmbMes.getSelectedItem());

            //ids de combobox (índice 0 = SELECCIONAR... = null)
            Area areaElegida = (Area) cmbArea.getSelectedItem();
            Cargo cargoElegido = (Cargo) cmbCargo.getSelectedItem();

            empleado.setIdArea(areaElegida == null ? 0 : areaElegida.getIdArea());
            empleado.setIdCargo(cargoElegido == null ? 0 : cargoElegido.getIdCargo());

            if (rbCompleto.isSelected()) {
                empleado.setIdTipoEmpleado(1);
            } else if (rbPartTime.isSelected()) {
                empleado.setIdTipoEmpleado(2);
            } else if (rbPracticante.isSelected()) {
                empleado.setIdTipoEmpleado(3);
            } else {
                empleado.setIdTipoEmpleado(0);
            }

            DatosEmpleado datosEmpleado = new DatosEmpleado();

            if (empleadoId == 0 && datosEmpleado.insertar(empleado)) {
                JOptionPane.showMessageDialog(this, "Empleado guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                dispose();
            } else if (empleadoId != 0 && datosEmpleado.actualizar(empleadoId, empleado)) {
                JOptionPane.showMessageDialog(this, "Empleado " + empleadoId + "actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                empleadoId = 0;
                dispose();
            }

        } catch (IllegalArgumentException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private static final String SELECCIONAR = "SELECCIONAR...";

    private void cargarCombos() {
        try {
            areaModelo.addElement(new Area(0, SELECCIONAR));
            for (Area a : DatosArea.listar()) {
                areaModelo.addElement(a);
            }
            cargoModelo.addElement(new Cargo(0, SELECCIONAR));
            for (Cargo c : datosCargo.listarCargo()) {
                cargoModelo.addElement(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar combobox: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        cmbArea.setModel(areaModelo);
        cmbCargo.setModel(cargoModelo);
        cmbSexo.setModel(new DefaultComboBoxModel<>(new String[]{SELECCIONAR, "M", "F"}));
        cmbMes.setModel(new DefaultComboBoxModel<>(new String[]{SELECCIONAR, "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        String[] distritos = new String[DISTRITOS_LIMA.length + 1];
        distritos[0] = SELECCIONAR;
        System.arraycopy(DISTRITOS_LIMA, 0, distritos, 1, DISTRITOS_LIMA.length);
        cmbDistrito.setModel(new DefaultComboBoxModel<>(distritos));
    }

    private void limpiarCampos() {
        txtApellidos.setText("");
        txtAnio.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtDni.setText("");
        txtFechaNacimiento.setText("");
        txtNombres.setText("");

        cmbArea.setSelectedIndex(0);
        cmbCargo.setSelectedIndex(0);
        cmbDistrito.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        cmbMes.setSelectedIndex(0);
        cmbSexo.setSelectedIndex(0);

        buttonGroup1.clearSelection();
    }

    public void cargarDatos(Empleado e) {
        empleadoId = e.getIdEmpleado();

        txtApellidos.setText(e.getApellidos() == null ? "" : e.getApellidos());
        txtNombres.setText(e.getNombres() == null ? "" : e.getNombres());
        txtAnio.setText(e.getAnio() == null ? "" : e.getAnio());
        txtCelular.setText(e.getCelular() == null ? "" : e.getCelular());
        txtCorreo.setText(e.getCorreo() == null ? "" : e.getCorreo());
        txtDireccion.setText(e.getDireccion() == null ? "" : e.getDireccion());
        txtDni.setText(e.getDni() == null ? "" : e.getDni());
        txtFechaNacimiento.setText(e.getFechaNacimiento() == null ? "" : e.getFechaNacimiento().toString());

        cmbArea.setSelectedIndex(0);
        for (int i = 0; i < cmbArea.getItemCount(); i++) {
            if (e.getIdArea() == cmbArea.getItemAt(i).getIdArea()) {
                cmbArea.setSelectedIndex(i);
                break;
            }
        }
        cmbCargo.setSelectedIndex(0);
        for (int i = 0; i < cmbCargo.getItemCount(); i++) {
            if (e.getIdCargo() == cmbCargo.getItemAt(i).getIdCargo()) {
                cmbCargo.setSelectedIndex(i);
                break;
            }
        }
        cmbDistrito.setSelectedItem(e.getDistrito());
        cmbEstado.setSelectedItem(e.getEstado());
        cmbMes.setSelectedItem(e.getMes());
        cmbSexo.setSelectedItem(e.getSexo());

        buttonGroup1.clearSelection();
        switch (e.getIdTipoEmpleado()) {
            case 1:
                rbCompleto.setSelected(true);
                break;
            case 2:
                rbPartTime.setSelected(true);
                break;
            case 3:
                rbPracticante.setSelected(true);
                break;
            default:
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<Area> cmbArea;
    private javax.swing.JComboBox<Cargo> cmbCargo;
    private javax.swing.JComboBox<String> cmbDistrito;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton rbCompleto;
    private javax.swing.JRadioButton rbPartTime;
    private javax.swing.JRadioButton rbPracticante;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
