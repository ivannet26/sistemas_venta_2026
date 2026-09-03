package proyecto_gm.Articulo;

import java.sql.Connection;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import proyecto_gm.Categoria.Categoria;
import proyecto_gm.Categoria.DatosCategoria;
import principal.ConexionBD;
import principal.ControladorExportar;


public class frmListaArticulo extends javax.swing.JInternalFrame {
    private static frmListaArticulo instancia;   
    
    DefaultTableModel modelo;
    List<Articulo> listaArticulos;
    TableRowSorter<DefaultTableModel> sorter;

    public frmListaArticulo() {
        initComponents();
        
        modelo = (DefaultTableModel) tblarticulo.getModel();
//        modelo.addColumn("ID");
//        modelo.addColumn("Descripción");
//        modelo.addColumn("Características");
//        modelo.addColumn("Categoría");
//        modelo.addColumn("Marca");
//        modelo.addColumn("Cantidad");
        tblarticulo.setModel(modelo);
        
        // Asignar el sorter para permitir filtrado dinámico
        sorter = new TableRowSorter<>(modelo);
        tblarticulo.setRowSorter(sorter);
        tblarticulo.setShowGrid(true); 
        tblarticulo.setGridColor(java.awt.Color.BLACK);
        tblarticulo.setShowHorizontalLines(true);
        tblarticulo.setShowVerticalLines(true);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
        cargarDatos();
        cargarComboCategorias();
    }
    
    public static frmListaArticulo getInstancia() {
        if (instancia == null) {
            instancia = new frmListaArticulo();
        }
        return instancia;
    }
    
    @Override
    public void dispose() {
        super.dispose();
        instancia = null;
    }
    
    public void cargarDatos() {
        tblarticulo.setModel(new DefaultTableModel()); 
        modelo.setRowCount(0);
        listaArticulos = DatosArticulos.listar();
        for (Articulo art : listaArticulos) {
            modelo.addRow(new Object[]{
                art.getId(),
                art.getCategoria().getDescripcion(),
                art.getMarca().getDescripcion(),
                art.getCaracteristicas(),
                art.getDescripcion(),                                                
                art.getCantidad()
            });
        }
        tblarticulo.setModel(modelo);
        tblarticulo.setRowSorter(sorter); 
    }
    
    public void generarReporteJasper(int idCategoria, String nombreCategoria) {
        try {
            Connection conn = ConexionBD.getConnection();
            String path = System.getProperty("user.dir") +"/reportes/RPTArticulo.jasper";
            JasperReport reporte = JasperCompileManager.compileReport(path);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("p_id_categoria", idCategoria);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, conn);

            JasperViewer view = new JasperViewer(jprint, false);
            view.setTitle("Reporte de: " + nombreCategoria);
            view.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace(); 
        }
    }
    
   private void cargarComboCategorias() {
  
        javax.swing.SwingWorker<List<Categoria>, Void> worker = new javax.swing.SwingWorker<List<Categoria>, Void>() {
            @Override
            protected List<Categoria> doInBackground() throws Exception {
                return DatosCategoria.listar();
            }
            @Override
            protected void done() {
                try {
                    List<Categoria> lista = get(); 
                    for (java.awt.event.ActionListener al : cmbCategoria.getActionListeners()) {
                        cmbCategoria.removeActionListener(al);
                    }
                    cmbCategoria.removeAllItems();
                    for (Categoria cat : lista) {
                        cmbCategoria.addItem(cat);
                    }
                    cmbCategoria.addActionListener(e -> cmbCategoriaActionPerformed(e));
                } catch (Exception e) {
                    System.out.println("Error cargando categorías en segundo plano: " + e.getMessage());
                }
            }
        };

        worker.execute();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblarticulo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnReporte = new javax.swing.JButton();
        cmbCategoria = new javax.swing.JComboBox<>();
        btnExportarExcel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("ARTICULO");

        tblarticulo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdArticulo", "Categoria", "Marca", "Caracteristicas", "Descripcion", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblarticulo);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Buscar");

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

        jLabel2.setText("Estado:");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activo", "Inactivo" }));
        cboEstado.setEnabled(false);
        cboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(btnBuscar)
                .addComponent(jLabel2)
                .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator2);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/agregar.png"))); // NOI18N
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNuevo);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);
        jToolBar1.add(jSeparator1);

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-doc-25.png"))); // NOI18N
        btnReporte.setToolTipText("Generar Reporte");
        btnReporte.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReporte.setFocusable(false);
        btnReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReporte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnReporte);

        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jToolBar1.add(cmbCategoria);

        btnExportarExcel.setText("Exportar Excel");
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportarExcel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnExportarExcel)
        );

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(escritorioLayout.createSequentialGroup()
                        .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoActionPerformed
        //        String nombreEstado = this.cboEstado.getSelectedItem().toString().toLowerCase();
        //        DefaultTableModel modelo = (DefaultTableModel) tblEmpleados.getModel();
        //        switch (nombreEstado) {
            //            case "activo":
            //            DatosEmpleados.Listar(modelo,"A");
            //
            //            break;
            //            case "inactivo":
            //            DatosEmpleados.Listar(modelo, "I");
            //
            //            default:
            //            DatosEmpleados.Listar(modelo);
            //            break;
            //        }
    }//GEN-LAST:event_cboEstadoActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        if(evt.getKeyChar()== KeyEvent.VK_ENTER){
           if(!txtBusqueda.getText().trim().equals("")){
               filtrarArticulos(txtBusqueda.getText());
           }else{
                filtrarArticulos("");
           }     
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblarticulo.getSelectedRow();
        if (fila >= 0) {
           
            Object[] opciones = {"Sí", "No"};
            
        
            int confirmacion = JOptionPane.showOptionDialog(
                    this, 
                    "¿Está seguro de eliminar este artículo?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0] 
            );

           
            if (confirmacion == 0) {
                int idArticulo = (int) tblarticulo.getValueAt(fila, 0);
                DatosArticulos.eliminar(idArticulo);
                cargarDatos(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un artículo para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = tblarticulo.getSelectedRow();
    
        if (fila >= 0) {
            JDesktopPane desktopPane = getDesktopPane();
            for (javax.swing.JInternalFrame frame : desktopPane.getAllFrames()) {
                if (frame instanceof frmArticulo) {
                    try {
                        if (frame.isIcon()) {
                            frame.setIcon(false);
                        }
                        frame.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        System.out.println("Error al enfocar la ventana: " + e.getMessage());
                    }
                    return; 
                }
            }
            int filaModelo = tblarticulo.convertRowIndexToModel(fila);
            Articulo artSeleccionado = listaArticulos.get(filaModelo);
            frmArticulo frm = new frmArticulo(this, artSeleccionado);

            desktopPane.add(frm);
            frm.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                    cargarDatos();
                }
            });

            frm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un artículo para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        JDesktopPane desktopPane = getDesktopPane();
        for (javax.swing.JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof frmArticulo) {
                try {
                    if (frame.isIcon()) {
                        frame.setIcon(false);
                    }
                    frame.setSelected(true);
                } catch (java.beans.PropertyVetoException e) {
                    System.out.println("Error al enfocar la ventana: " + e.getMessage());
                }
                return; 
            }
        }
        frmArticulo frm = new frmArticulo(this, null);
        desktopPane.add(frm);
        frm.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                cargarDatos();
            }
        });

        frm.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        filtrarArticulos(txtBusqueda.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
          try {
               
                Object seleccionado = cmbCategoria.getSelectedItem();

               
                if (seleccionado instanceof Categoria) {
                    Categoria cat = (Categoria) seleccionado;
                    String nombreCategoria = cat.getDescripcion();

                   
                    filtrarArticulos(nombreCategoria);
                }
            } catch (Exception e) {
                System.out.println("Error al filtrar combo: " + e.getMessage());
            }
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        int idCategoria = ((Categoria) cmbCategoria.getSelectedItem()).getId(); 
    
        java.util.Map<String, Object> parametros = new java.util.HashMap<>();
        parametros.put("p_id_categoria", idCategoria);

//        reportes.GeneradorReportes gen = new reportes.GeneradorReportes();
//        gen.mostrarReporte("RPTArticulos", parametros);
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
//        proyecto_gm.ControladorExportar.exportarTablaExcel(tblarticulo);
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    private void filtrarArticulos(String texto) {
        try {
            if (texto.trim().isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                // Filtra por columnas 1 (Descripción), 3 (Categoría), 4 (Marca)
                sorter.setRowFilter(RowFilter.regexFilter("(?i).*" + Pattern.quote(texto) + ".*", 1, 3, 4));
            }
        } catch (PatternSyntaxException ex) {
            System.err.println("Error en el filtro: " + ex.getMessage());
        }
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<Object> cmbCategoria;
    private javax.swing.JPanel escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblarticulo;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
