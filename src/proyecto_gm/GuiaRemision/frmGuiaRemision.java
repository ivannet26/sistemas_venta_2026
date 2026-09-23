/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_gm.GuiaRemision;

import java.awt.Image;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import principal.ConexionBD;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author LAB-USR-LCENTRO
 */
public class frmGuiaRemision extends javax.swing.JInternalFrame {

        private DefaultTableModel modeloDetalle;

    private GuiaRemision destinatarioSeleccionado;

    private List<GuiaRemision> motivosFormulario =
            new ArrayList<>();

    private final SimpleDateFormat formatoFecha =
            new SimpleDateFormat("dd/MM/yyyy");

    private final Map<Integer, GuiaRemision.Detalle>
        articulosSeleccionados =
                new LinkedHashMap<>();

    private List<GuiaRemision.Categoria>
            categoriasFormulario =
                    new ArrayList<>();

    private static final String RUTA_LOGO =
            "/iconos/fondogm.png";

    private static final BigDecimal CANTIDAD_MAXIMA =
        new BigDecimal("99999999.99");

    /**
     * Creates new form frmGuiaRemision
     */
    public frmGuiaRemision() {
        super(
        "Guía de Remisión",
        true,  // resizable
        true,  // closable
        true,  // maximizable
        true   // iconifiable
    );

        initComponents();

            setDefaultCloseOperation(
        javax.swing.WindowConstants.DISPOSE_ON_CLOSE
        );

        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        inicializarFormulario();
    }

    private static frmGuiaRemision instancia;

    public static frmGuiaRemision getInstancia() {
        if (instancia == null) {
            instancia = new frmGuiaRemision();
        }
        return instancia;
    }

    @Override
    public void dispose() {
        super.dispose();
        instancia = null;
    }

    private void inicializarFormulario() {

        setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        formatoFecha.setLenient(false);

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText(
                formatoFecha.format(new java.util.Date()));
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");

        configurarValidaciones();
        configurarTabla();
        configurarEventos();

        cargarLogo();
        cargarMotivos();
        cargarDatosEmpresa();
        cargarCategorias();
        cargarArticulos();
    }

    private void configurarEventos() {

        jButton1.addActionListener(
                this::jButton1ActionPerformed);

        jButton5.addActionListener(
                this::jButton5ActionPerformed);

        jButton6.addActionListener(
                this::jButton6ActionPerformed);

        // Permite aplicar la búsqueda presionando Enter.
        jTextField9.addActionListener(
                evento -> aplicarFiltros());
    }

    private void configurarValidaciones() {

        // RUC/DNI del destinatario: solamente números, máximo 11.
        aplicarFiltro(
                jTextField1,
                11,
                "\\d*",
                false);

        // Fecha: números y barras, máximo dd/MM/yyyy.
        aplicarFiltro(
                jTextField3,
                10,
                "[0-9/]*",
                false);

        // Direcciones: hasta 300 caracteres.
        aplicarFiltro(
                jTextField4,
                300,
                null,
                false);

        aplicarFiltro(
                jTextField5,
                300,
                null,
                false);

        // RUC/DNI del transportista.
        aplicarFiltro(
                jTextField6,
                11,
                "\\d*",
                false);

        // Licencia: letras, números y guion.
        aplicarFiltro(
                jTextField7,
                30,
                "[A-Z0-9-]*",
                true);

        // Marca y placa: texto alfanumérico y separadores.
        aplicarFiltro(
                jTextField8,
                120,
                "[\\p{L}\\p{N} ._/-]*",
                true);

        // La dirección puede cargarse desde la BD o escribirse manualmente.
        aplicarFiltro(
                jTextField2,
                300,
                null,
                false);

        // Texto para buscar por código, descripción o categoría.
        aplicarFiltro(
                jTextField9,
                400,
                null,
                false);
        jTextField2.setEditable(true);
    }

    private void aplicarFiltro(
            JTextField campo,
            int maximo,
            String expresion,
            boolean convertirMayusculas) {

        Pattern patron = expresion == null
                ? null
                : Pattern.compile(expresion);

        ((AbstractDocument) campo.getDocument())
                .setDocumentFilter(
                        new FiltroEntrada(
                                maximo,
                                patron,
                                convertirMayusculas));
    }

    private static final class FiltroEntrada
            extends DocumentFilter {

        private final int maximo;
        private final Pattern patron;
        private final boolean convertirMayusculas;

        private FiltroEntrada(
                int maximo,
                Pattern patron,
                boolean convertirMayusculas) {

            this.maximo = maximo;
            this.patron = patron;
            this.convertirMayusculas =
                    convertirMayusculas;
        }

        @Override
        public void insertString(
                FilterBypass fb,
                int offset,
                String texto,
                AttributeSet atributos)
                throws BadLocationException {

            replace(
                    fb,
                    offset,
                    0,
                    texto,
                    atributos);
        }

        @Override
        public void replace(
                FilterBypass fb,
                int offset,
                int longitud,
                String texto,
                AttributeSet atributos)
                throws BadLocationException {

            String nuevoTexto =
                    texto == null ? "" : texto;

            if (convertirMayusculas) {
                nuevoTexto =
                        nuevoTexto.toUpperCase(
                                Locale.ROOT);
            }

            String actual =
                    fb.getDocument().getText(
                            0,
                            fb.getDocument().getLength());

            String candidato =
                    actual.substring(0, offset)
                    + nuevoTexto
                    + actual.substring(
                            offset + longitud);

            boolean longitudValida =
                    candidato.length() <= maximo;

            boolean caracteresValidos =
                    patron == null
                    || patron.matcher(candidato).matches();

            if (longitudValida && caracteresValidos) {
                fb.replace(
                        offset,
                        longitud,
                        nuevoTexto,
                        atributos);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    private void cargarDatosEmpresa() {
        String[] datosEmpresa =
                DatosGuiaRemision
                        .obtenerDatosEmpresaPorUsuario(
                                ConexionBD.nomUsuario);
        if (datosEmpresa != null) {
            jLabel3.setText(
                    "RUC: " + datosEmpresa[0]);
        }
    }

    private void configurarTabla() {

        modeloDetalle = new DefaultTableModel(
                new Object[]{
                    "Código",
                    "Descripción",
                    "Categoría",
                    "Cantidad",
                    "Operaciones",
                    "IdArtículo",
                    "IdCategoría"
                },
                0) {

            @Override
            public Class<?> getColumnClass(
                    int columna) {

                if (columna == 4) {
                    return Boolean.class;
                }

                if (columna == 5
                        || columna == 6) {

                    return Integer.class;
                }

                return Object.class;
            }

            @Override
            public boolean isCellEditable(
                    int fila,
                    int columna) {

                // La casilla siempre puede cambiarse.
                if (columna == 4) {
                    return true;
                }

                // La cantidad solo puede escribirse
                // cuando el artículo está incluido.
                return columna == 3
                        && Boolean.TRUE.equals(
                                getValueAt(fila, 4));
            }

            @Override
            public void setValueAt(
                    Object valor,
                    int fila,
                    int columna) {

                super.setValueAt(
                        valor,
                        fila,
                        columna);

                // Al desmarcar un artículo se elimina
                // su cantidad visual.
                if (columna == 4
                        && !Boolean.TRUE.equals(valor)) {

                    super.setValueAt(
                            "",
                            fila,
                            3);
                }
            }
        };

        jTable1.setModel(modeloDetalle);

        jTable1.setSelectionMode(
                javax.swing.ListSelectionModel
                        .SINGLE_SELECTION);

        jTable1.setRowHeight(30);

        jTable1.putClientProperty(
                "terminateEditOnFocusLost",
                Boolean.TRUE);

        jTable1.getTableHeader()
                .setReorderingAllowed(false);

        JTextField campoCantidad =
                new JTextField();

        aplicarFiltro(
                campoCantidad,
                11,
                "\\d{0,8}([.,]\\d{0,2})?",
                false);

        jTable1.getColumnModel()
                .getColumn(3)
                .setCellEditor(
                        new DefaultCellEditor(
                                campoCantidad));

        jTable1.getColumnModel()
                .getColumn(4)
                .setMinWidth(90);

        jTable1.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(90);

        jTable1.getColumnModel()
                .getColumn(4)
                .setMaxWidth(110);

        /*
         * Los identificadores se conservan en el modelo,
         * pero no se muestran al usuario.
         */
        jTable1.removeColumn(
                jTable1.getColumnModel()
                        .getColumn(6));

        jTable1.removeColumn(
                jTable1.getColumnModel()
                        .getColumn(5));
    }

    private String textoCelda(
            int fila,
            int columna) {

        Object valor =
                modeloDetalle.getValueAt(
                        fila,
                        columna);

        return valor == null
                ? ""
                : valor.toString().trim();
    }

    private BigDecimal convertirCantidad(
            String cantidadTexto) {

        return new BigDecimal(
                cantidadTexto
                        .trim()
                        .replace(',', '.'));
    }

    private void detenerEdicionTabla() {

        if (jTable1.isEditing()
                && jTable1.getCellEditor() != null) {

            if (!jTable1.getCellEditor()
                    .stopCellEditing()) {

                jTable1.getCellEditor()
                        .cancelCellEditing();
            }
        }
    }

    private boolean documentoValido(
            String documento) {

        return documento.matches(
                "\\d{8}|\\d{11}");
    }

    private void cargarLogo() {
        java.net.URL recurso =
                getClass().getResource("/iconos/fondogm.png");

        if (recurso == null) {
            jLabel5.setIcon(null);
            jLabel5.setText("LOGO DE LA EMPRESA");
            return;
        }

        ImageIcon original =
                new ImageIcon(recurso);

        int anchoMaximo = 360;
        int altoMaximo = 50;

        double escala = Math.min(
                (double) anchoMaximo / original.getIconWidth(),
                (double) altoMaximo / original.getIconHeight()
        );

        int ancho = Math.max(
                1,
                (int) (original.getIconWidth() * escala)
        );

        int alto = Math.max(
                1,
                (int) (original.getIconHeight() * escala)
        );

        Image imagen =
                original.getImage().getScaledInstance(
                        ancho,
                        alto,
                        Image.SCALE_SMOOTH
                );

        jLabel5.setText("");
        jLabel5.setIcon(new ImageIcon(imagen));
        jLabel5.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER
        );
        jLabel5.setVerticalAlignment(
                javax.swing.SwingConstants.CENTER
        );
    }

    private void cargarMotivos() {
        motivosFormulario =
                DatosGuiaRemision
                        .listarMotivosFormulario();

        jComboBox1.removeAllItems();

        for (GuiaRemision motivo : motivosFormulario) {
            jComboBox1.addItem(
                    motivo.getCodigoMotivo());
        }

        if (!motivosFormulario.isEmpty()) {
            jComboBox1.setSelectedIndex(0);
        }
    }

    private void cargarCategorias() {

    categoriasFormulario =
            DatosGuiaRemision
                    .listarCategoriasFormulario();

    DefaultComboBoxModel<String> modelo =
            new DefaultComboBoxModel<>();

    modelo.addElement("TODAS");

    for (GuiaRemision.Categoria categoria
            : categoriasFormulario) {

        modelo.addElement(
                categoria.getDescripcion());
    }

    jComboBox3.setModel(modelo);
    jComboBox3.setSelectedIndex(0);
}

private Integer obtenerIdCategoriaFiltro() {

    int indice =
            jComboBox3.getSelectedIndex();

    if (indice <= 0
            || indice - 1
                    >= categoriasFormulario.size()) {

        return null;
    }

    return categoriasFormulario
            .get(indice - 1)
            .getIdCategoria();
}

    private void cargarArticulos() {

        String busqueda =
                jTextField9.getText().trim();

        Integer idCategoria =
                obtenerIdCategoriaFiltro();

        List<GuiaRemision.Detalle> articulos =
                DatosGuiaRemision
                        .listarArticulosFormulario(
                                busqueda,
                                idCategoria);

        modeloDetalle.setRowCount(0);

        for (GuiaRemision.Detalle articulo
                : articulos) {

            GuiaRemision.Detalle seleccionado =
                    articulosSeleccionados.get(
                            articulo.getIdArticulo());

            boolean incluir =
                    seleccionado != null;

            String cantidad = "";

            if (seleccionado != null
                    && seleccionado.getCantidad() != null
                    && seleccionado.getCantidad()
                            .compareTo(
                                    BigDecimal.ZERO) > 0) {

                cantidad =
                        seleccionado.getCantidad()
                                .stripTrailingZeros()
                                .toPlainString();
            }

            modeloDetalle.addRow(
                    new Object[]{
                        articulo.getCodigoArticulo(),
                        articulo.getDescripcion(),
                        articulo.getCategoriaDescripcion(),
                        cantidad,
                        incluir,
                        articulo.getIdArticulo(),
                        articulo.getIdCategoria()
                    });
        }
    }

    private void aplicarFiltros() {

        detenerEdicionTabla();

        /*
         * Conserva los artículos seleccionados aunque
         * dejen de aparecer por un nuevo filtro.
         */
        if (!sincronizarArticulosSeleccionados()) {
            return;
        }

        cargarArticulos();
    }

    private boolean sincronizarArticulosSeleccionados() {

        detenerEdicionTabla();

        for (int fila = 0;
                fila < modeloDetalle.getRowCount();
                fila++) {

            Object valorId =
                    modeloDetalle.getValueAt(
                            fila,
                            5);

            if (!(valorId instanceof Number)) {
                return advertirFila(
                        fila,
                        "No se pudo identificar el artículo.");
            }

            int idArticulo =
                    ((Number) valorId).intValue();

            boolean incluir =
                    Boolean.TRUE.equals(
                            modeloDetalle.getValueAt(
                                    fila,
                                    4));

            if (!incluir) {
                articulosSeleccionados.remove(
                        idArticulo);
                continue;
            }

            String cantidadTexto =
                    textoCelda(fila, 3);

            if (cantidadTexto.isEmpty()) {
                return advertirFila(
                        fila,
                        "Ingrese la cantidad vendida "
                        + "del artículo seleccionado.");
            }

            BigDecimal cantidad;

            try {
                cantidad =
                        convertirCantidad(
                                cantidadTexto);
            } catch (NumberFormatException ex) {
                return advertirFila(
                        fila,
                        "La cantidad debe ser numérica.");
            }

            if (cantidad.compareTo(
                    BigDecimal.ZERO) <= 0) {

                return advertirFila(
                        fila,
                        "La cantidad debe ser mayor que cero.");
            }

            if (cantidad.scale() > 2
                    || cantidad.compareTo(
                            CANTIDAD_MAXIMA) > 0) {

                return advertirFila(
                        fila,
                        "La cantidad admite hasta "
                        + "8 enteros y 2 decimales.");
            }

            GuiaRemision.Detalle detalle =
                    new GuiaRemision.Detalle();

            detalle.setIdArticulo(idArticulo);

            detalle.setCodigoArticulo(
                    textoCelda(fila, 0));

            detalle.setDescripcion(
                    textoCelda(fila, 1));

            detalle.setCategoriaDescripcion(
                    textoCelda(fila, 2));

            Object valorCategoria =
                    modeloDetalle.getValueAt(
                            fila,
                            6);

            if (valorCategoria instanceof Number) {
                detalle.setIdCategoria(
                        ((Number) valorCategoria)
                                .intValue());
            }

            detalle.setCantidad(cantidad);

            articulosSeleccionados.put(
                    idArticulo,
                    detalle);
        }

        return true;
    }

    private boolean advertirFila(
            int filaModelo,
            String mensaje) {

        int filaVista =
                jTable1.convertRowIndexToView(
                        filaModelo);

        if (filaVista >= 0) {
            jTable1.setRowSelectionInterval(
                    filaVista,
                    filaVista);
        }

        return advertir(mensaje);
    }

    private void jButton1ActionPerformed(
            java.awt.event.ActionEvent evt) {

        String documento =
                jTextField1.getText().trim();

        if (!documentoValido(documento)) {
            advertir(
                    "El destinatario debe tener "
                    + "8 dígitos para DNI "
                    + "u 11 dígitos para RUC.");

            jTextField1.requestFocus();
            return;
        }

        GuiaRemision encontrado =
                DatosGuiaRemision
                        .buscarDestinatarioFormulario(
                                documento);

        if (encontrado == null) {

            destinatarioSeleccionado = null;

            advertir(
                    "No se encontró un destinatario "
                    + "registrado con ese documento.");

            // No se borra la dirección escrita.
            return;
        }

        destinatarioSeleccionado =
                encontrado;

        jTextField1.setText(
                encontrado
                        .getDestinatarioNumeroDoc());

        jTextField2.setText(
                encontrado
                        .getDestinatarioDireccion());

        if (jTextField5.getText()
                .trim()
                .isEmpty()) {

            jTextField5.setText(
                    encontrado
                            .getDireccionDestino());
        }
    }

    private boolean validarFormulario() {

        detenerEdicionTabla();

        String documentoDestinatario =
                jTextField1.getText().trim();

        if (!documentoValido(
                documentoDestinatario)) {

            return advertir(
                    "El RUC/DNI del destinatario "
                    + "debe tener 8 u 11 dígitos.");
        }

        if (jTextField2.getText()
                .trim()
                .isEmpty()) {

            return advertir(
                    "Ingrese la dirección del destinatario.");
        }

        String fechaTexto =
                jTextField3.getText().trim();

        if (!fechaTexto.matches(
                "\\d{2}/\\d{2}/\\d{4}")) {

            return advertir(
                    "La fecha debe tener formato "
                    + "dd/MM/yyyy.");
        }

        try {
            formatoFecha.parse(fechaTexto);

        } catch (ParseException ex) {
            return advertir(
                    "La fecha ingresada no existe "
                    + "o no es válida.");
        }

        if (jTextField4.getText().trim().isEmpty()
                || jTextField5.getText().trim().isEmpty()) {

            return advertir(
                    "Origen y destino son obligatorios.");
        }

        int indiceMotivo =
                jComboBox1.getSelectedIndex();

        if (indiceMotivo < 0
                || indiceMotivo
                        >= motivosFormulario.size()) {

            return advertir(
                    "Seleccione el motivo del traslado.");
        }

        String documentoTransportista =
                jTextField6.getText().trim();

        if (!documentoValido(
                documentoTransportista)) {

            return advertir(
                    "El RUC/DNI del transportista "
                    + "debe tener 8 u 11 dígitos.");
        }

        String licencia =
                jTextField7.getText().trim();

        String vehiculo =
                jTextField8.getText().trim();

        boolean tieneLicencia =
                !licencia.isEmpty();

        boolean tieneVehiculo =
                !vehiculo.isEmpty();

        if (tieneLicencia != tieneVehiculo) {
            return advertir(
                    "Debe completar licencia y vehículo "
                    + "o dejar ambos campos vacíos.");
        }

        if (!sincronizarArticulosSeleccionados()) {
            return false;
        }

        if (articulosSeleccionados.isEmpty()) {
            return advertir(
                    "Seleccione al menos un artículo "
                    + "en la columna Operaciones.");
        }

        return true;
    }

    private boolean advertir(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Validación",
                JOptionPane.WARNING_MESSAGE
        );

        return false;
    }

    private List<GuiaRemision.Detalle>
            obtenerDetallesSeleccionados() {

        List<GuiaRemision.Detalle> detalles =
                new ArrayList<>();

        int numeroItem = 1;

        for (GuiaRemision.Detalle seleccionado
                : articulosSeleccionados.values()) {

            GuiaRemision.Detalle detalle =
                    new GuiaRemision.Detalle();

            detalle.setNumeroItem(
                    numeroItem++);

            detalle.setIdArticulo(
                    seleccionado.getIdArticulo());

            detalle.setIdCategoria(
                    seleccionado.getIdCategoria());

            detalle.setCodigoArticulo(
                    seleccionado.getCodigoArticulo());

            detalle.setDescripcion(
                    seleccionado.getDescripcion());

            detalle.setCategoriaDescripcion(
                    seleccionado
                            .getCategoriaDescripcion());

            detalle.setCantidad(
                    seleccionado.getCantidad());

            detalles.add(detalle);
        }

        return detalles;
    }

        private GuiaRemision construirGuia()
            throws ParseException {

        int indiceMotivo =
                jComboBox1.getSelectedIndex();

        GuiaRemision motivo =
                motivosFormulario.get(indiceMotivo);

        java.util.Date fecha =
                formatoFecha.parse(
                        jTextField3.getText().trim());

        GuiaRemision guia =
                new GuiaRemision();

        String documentoDestinatario =
        jTextField1.getText().trim();

        boolean destinatarioEncontrado =
        destinatarioSeleccionado != null
        && documentoDestinatario.equals(
                destinatarioSeleccionado
                        .getDestinatarioNumeroDoc());

        guia.setIdCliente(
                destinatarioEncontrado
                        ? destinatarioSeleccionado
                                .getIdCliente()
                        : null);

        guia.setFechaEmision(
                new java.sql.Timestamp(
                        System.currentTimeMillis()));

        guia.setFechaInicioTraslado(
                new java.sql.Date(fecha.getTime()));

        guia.setIdMotivo(
                motivo.getIdMotivo());

        guia.setDireccionOrigen(
                jTextField4.getText().trim());

        guia.setDireccionDestino(
                jTextField5.getText().trim());

        guia.setDestinatarioTipoDoc(
        destinatarioEncontrado
                ? destinatarioSeleccionado
                        .getDestinatarioTipoDoc()
                : documentoDestinatario.length() == 8
                        ? "DNI"
                        : "RUC");

        guia.setDestinatarioNumeroDoc(
               documentoDestinatario);

        guia.setDestinatarioNombre(
        destinatarioEncontrado
                ? destinatarioSeleccionado
                        .getDestinatarioNombre()
                : null);

        guia.setDestinatarioDireccion(
                jTextField2.getText().trim());

        guia.setDocumentoTransportista(
                jTextField6.getText().trim());

        guia.setLicenciaConducir(
                jTextField7.getText().trim());

        guia.setVehiculoMarcaPlaca(
                jTextField8.getText().trim());

        guia.setDetalles(
            obtenerDetallesSeleccionados());

        return guia;
    }

    private void jButton5ActionPerformed(
            java.awt.event.ActionEvent evt) {

        if (!validarFormulario()) {
            return;
        }

        try {
            GuiaRemision guia =
                    construirGuia();

            if (DatosGuiaRemision.insertar(guia)) {

                jLabel2.setText(
                        "E001-"
                        + String.format(
                                "%09d",
                                guia.getCorrelativo()));

                jButton5.setEnabled(false);
            }

        } catch (NumberFormatException ex) {
            advertir(
                    "Existe una cantidad inválida en la tabla.");

        } catch (ParseException ex) {
            advertir(
                    "La fecha ingresada no es válida.");

        } catch (IllegalArgumentException ex) {
            advertir(ex.getMessage());
        }
    }

    private void jButton6ActionPerformed(
            java.awt.event.ActionEvent evt) {

        detenerEdicionTabla();

        destinatarioSeleccionado = null;
        articulosSeleccionados.clear();

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText(
                formatoFecha.format(
                        new java.util.Date()));
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");

        jTextField9.setText("");

        if (jComboBox3.getItemCount() > 0) {
            jComboBox3.setSelectedIndex(0);
        }

        cargarArticulos();

        jButton5.setEnabled(true);
        jTextField1.requestFocus();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lblBuscar3 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        lblArea3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

        jButton4.setText("Eliminar producto");

        jButton3.setText("Actualizar/validar producto");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("GUIA REMISION REMITENTE");

        jLabel2.setText("E001-000002344");

        jLabel3.setText("RUC:20555841095");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel2)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Motivo del traslado");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VENTA", "COMPRA", "DEVOLUCION O CAMBIO", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Fecha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("LOGO DE LA EMPRESA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 132, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 133, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 10, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 10, Short.MAX_VALUE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del destinatario"));
        jPanel4.setToolTipText("");

        jLabel7.setText("RUC/DNI");

        jLabel8.setText("Direccion");

        jButton1.setText("...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jTextField2))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Traslado"));

        jLabel9.setText("Origen");

        jLabel11.setText("Destino");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del transportista"));

        jLabel12.setText("RUC/DNI");

        jLabel13.setText("Licencia de conducir");

        jLabel14.setText("Vehiculo Marca_Placa");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jTextField8)
                    .addComponent(jTextField6))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(3, 3, 3))))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Descripción", "Categoria", "Cantidad", "Operaciones"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setText("Guardar guía");

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setText("Nuevo/Limpiar");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Filtros"));

        lblBuscar3.setText("Buscar:");

        jButton7.setText("Aceptar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lblArea3.setText("Categoria");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscar3)
                .addGap(18, 18, 18)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblArea3)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar3)
                    .addComponent(lblArea3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       aplicarFiltros();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

    }//GEN-LAST:event_jComboBox3ActionPerformed

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
            java.util.logging.Logger.getLogger(frmGuiaRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGuiaRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGuiaRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGuiaRemision.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGuiaRemision().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblArea3;
    private javax.swing.JLabel lblBuscar3;
    // End of variables declaration//GEN-END:variables
}
