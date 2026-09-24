package proyecto_gm.GuiaRemision;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import principal.ConexionBD;

public class DatosGuiaRemision {

        private static Connection obtenerConexion()
                        throws SQLException {

                Connection conn = ConexionBD.getConnection();

                if (conn == null) {
                        throw new SQLException(
                                        "No existe conexión con la base de datos.");
                }

                return conn;
        }

        public static String[] obtenerDatosEmpresaPorUsuario(
                        String usuario) {

                String sql = "{CALL sp_datos_empresa_por_usuario(?)}";

                try (Connection conn = obtenerConexion();
                                CallableStatement cstmt = conn.prepareCall(sql)) {

                        cstmt.setString(1, usuario);

                        try (ResultSet rs = cstmt.executeQuery()) {
                                if (rs.next()) {
                                        return new String[] {
                                                        rs.getString("ruc"),
                                                        rs.getString("razonsocial"),
                                                        rs.getString("direccion")
                                        };
                                }
                        }

                } catch (SQLException ex) {
                        mostrarError(
                                        "Error al cargar datos de empresa",
                                        ex);
                }

                return null;
        }

        public static List<GuiaRemision> listarMotivosFormulario() {

                List<GuiaRemision> lista = new ArrayList<>();

                String sql = "{CALL sp_listar_motivos_formulario()}";

                try (Connection conn = obtenerConexion();
                                CallableStatement cstmt = conn.prepareCall(sql);
                                ResultSet rs = cstmt.executeQuery()) {

                        while (rs.next()) {
                                GuiaRemision motivo = new GuiaRemision();

                                motivo.setIdMotivo(
                                                rs.getInt("idmotivo"));

                                motivo.setCodigoMotivo(
                                                rs.getString("codigo"));

                                motivo.setMotivoDescripcion(
                                                rs.getString("descripcion"));

                                lista.add(motivo);
                        }

                } catch (SQLException ex) {
                        mostrarError(
                                        "Error al cargar motivos de traslado",
                                        ex);
                }

                return lista;
        }

        public static GuiaRemision buscarDestinatarioFormulario(
                        String documento) {

                String sql = "{CALL sp_buscar_destinatario_formulario(?)}";

                try (Connection conn = obtenerConexion();
                                CallableStatement cstmt = conn.prepareCall(sql)) {

                        cstmt.setString(1, documento);

                        try (ResultSet rs = cstmt.executeQuery()) {
                                if (rs.next()) {
                                        GuiaRemision destinatario = new GuiaRemision();

                                        destinatario.setIdCliente(
                                                        rs.getInt("idcliente"));

                                        destinatario.setDestinatarioTipoDoc(
                                                        rs.getString("tipodocumento"));

                                        destinatario.setDestinatarioNumeroDoc(
                                                        rs.getString("numerodocumento"));

                                        destinatario.setDestinatarioNombre(
                                                        rs.getString("nombre"));

                                        destinatario.setDestinatarioDireccion(
                                                        rs.getString("direccion"));

                                        destinatario.setDireccionDestino(
                                                        rs.getString("direccion"));

                                        return destinatario;
                                }
                        }

                } catch (SQLException ex) {
                        mostrarError(
                                        "Error al buscar destinatario",
                                        ex);
                }

                return null;
        }

        public static List<GuiaRemision.Categoria> listarCategoriasFormulario() {

                List<GuiaRemision.Categoria> lista = new ArrayList<>();

                String sql = "{CALL listar_categorias()}";

                try (Connection conn = obtenerConexion();
                                CallableStatement cstmt = conn.prepareCall(sql);
                                ResultSet rs = cstmt.executeQuery()) {

                        while (rs.next()) {

                                GuiaRemision.Categoria categoria = new GuiaRemision.Categoria();

                                categoria.setIdCategoria(
                                                rs.getInt("IdCategoria"));

                                categoria.setDescripcion(
                                                rs.getString("Descripcion"));

                                lista.add(categoria);
                        }

                } catch (SQLException ex) {
                        mostrarError(
                                        "Error al cargar las categorías",
                                        ex);
                }

                return lista;
        }

        public static List<GuiaRemision.Detalle> listarArticulosFormulario(
                        String busqueda,
                        Integer idCategoria) {

                List<GuiaRemision.Detalle> lista = new ArrayList<>();

                String sql = "{CALL "
                                + "sp_listar_articulos_guia_formulario"
                                + "(?,?)}";

                try (Connection conn = obtenerConexion();
                                CallableStatement cstmt = conn.prepareCall(sql)) {

                        if (busqueda == null
                                        || busqueda.trim().isEmpty()) {

                                cstmt.setNull(
                                                1,
                                                Types.VARCHAR);
                        } else {
                                cstmt.setString(
                                                1,
                                                busqueda.trim());
                        }

                        if (idCategoria == null) {
                                cstmt.setNull(
                                                2,
                                                Types.INTEGER);
                        } else {
                                cstmt.setInt(
                                                2,
                                                idCategoria);
                        }

                        try (ResultSet rs = cstmt.executeQuery()) {

                                while (rs.next()) {

                                        GuiaRemision.Detalle articulo = new GuiaRemision.Detalle();

                                        articulo.setIdArticulo(
                                                        rs.getInt("idarticulo"));

                                        articulo.setCodigoArticulo(
                                                        rs.getString("codigoarticulo"));

                                        articulo.setDescripcion(
                                                        rs.getString("descripcion"));

                                        articulo.setIdCategoria(
                                                        obtenerIntegerNulo(
                                                                        rs,
                                                                        "idcategoria"));

                                        articulo.setCategoriaDescripcion(
                                                        rs.getString("categoria"));

                                        lista.add(articulo);
                                }
                        }

                } catch (SQLException ex) {
                        mostrarError(
                                        "Error al cargar los artículos",
                                        ex);
                }

                return lista;
        }

        public static boolean insertar(
                        GuiaRemision guia) {

                if (guia == null) {
                        advertir(
                                        "La guía de remisión no puede ser nula.");
                        return false;
                }

                if (guia.getDetalles() == null
                                || guia.getDetalles().isEmpty()) {

                        advertir(
                                        "La guía debe contener al menos un artículo.");
                        return false;
                }

                String detallesJson = construirDetallesJson(
                                guia.getDetalles());

                String sql = "{CALL sp_registrar_guiaremision_formulario("
                                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

                try (Connection conn = obtenerConexion();
                                CallableStatement cstmt = conn.prepareCall(sql)) {

                        cstmt.setString(
                                        1,
                                        ConexionBD.nomUsuario);

                        if (guia.getIdCliente() == null) {
                                cstmt.setNull(
                                                2,
                                                Types.INTEGER);
                        } else {
                                cstmt.setInt(
                                                2,
                                                guia.getIdCliente());
                        }

                        setDateNulo(
                                        cstmt,
                                        3,
                                        guia.getFechaInicioTraslado());

                        cstmt.setInt(
                                        4,
                                        guia.getIdMotivo());

                        setStringNulo(
                                        cstmt,
                                        5,
                                        guia.getDestinatarioTipoDoc());

                        setStringNulo(
                                        cstmt,
                                        6,
                                        guia.getDestinatarioNumeroDoc());

                        setStringNulo(
                                        cstmt,
                                        7,
                                        guia.getDestinatarioNombre());

                        setStringNulo(
                                        cstmt,
                                        8,
                                        guia.getDestinatarioDireccion());

                        setStringNulo(
                                        cstmt,
                                        9,
                                        guia.getDireccionOrigen());

                        setStringNulo(
                                        cstmt,
                                        10,
                                        guia.getDireccionDestino());

                        setStringNulo(
                                        cstmt,
                                        11,
                                        guia.getDocumentoTransportista());

                        setStringNulo(
                                        cstmt,
                                        12,
                                        guia.getLicenciaConducir());

                        setStringNulo(
                                        cstmt,
                                        13,
                                        guia.getVehiculoMarcaPlaca());

                        cstmt.setString(
                                        14,
                                        detallesJson);

                        cstmt.registerOutParameter(
                                        15,
                                        Types.BIGINT);

                        cstmt.registerOutParameter(
                                        16,
                                        Types.BIGINT);

                        cstmt.execute();

                        guia.setIdGuia(
                                        cstmt.getLong(15));

                        guia.setCorrelativo(
                                        cstmt.getLong(16));

                        guia.setEstado("EMITIDA");

                        JOptionPane.showMessageDialog(
                                        null,
                                        "Guía de remisión registrada correctamente.",
                                        "Registro exitoso",
                                        JOptionPane.INFORMATION_MESSAGE);

                        return true;

                } catch (SQLException ex) {
                        mostrarError(
                                        "Error al registrar la guía de remisión",
                                        ex);
                        return false;
                }
        }

        private static String construirDetallesJson(
                        List<GuiaRemision.Detalle> detalles) {

                StringBuilder json = new StringBuilder("[");

                for (int i = 0; i < detalles.size(); i++) {

                        GuiaRemision.Detalle detalle = detalles.get(i);

                        if (i > 0) {
                                json.append(",");
                        }

                        BigDecimal cantidad = detalle.getCantidad() != null
                                        ? detalle.getCantidad()
                                        : BigDecimal.ZERO;

                        json.append("{")
                                        .append("\"idArticulo\":")
                                        .append(detalle.getIdArticulo())
                                        .append(",\"cantidad\":")
                                        .append(cantidad.toPlainString())
                                        .append("}");
                }

                json.append("]");

                return json.toString();
        }

        private static Integer obtenerIntegerNulo(
                        ResultSet rs,
                        String columna) throws SQLException {

                int valor = rs.getInt(columna);

                return rs.wasNull()
                                ? null
                                : valor;
        }

        private static void setStringNulo(
                        CallableStatement cstmt,
                        int indice,
                        String valor) throws SQLException {

                if (valor == null
                                || valor.trim().isEmpty()) {

                        cstmt.setNull(
                                        indice,
                                        Types.VARCHAR);

                } else {
                        cstmt.setString(
                                        indice,
                                        valor.trim());
                }
        }

        private static void setDateNulo(
                        CallableStatement cstmt,
                        int indice,
                        Date valor) throws SQLException {

                if (valor == null) {
                        cstmt.setNull(
                                        indice,
                                        Types.DATE);
                } else {
                        cstmt.setDate(indice, valor);
                }
        }

        private static void advertir(String mensaje) {
                JOptionPane.showMessageDialog(
                                null,
                                mensaje,
                                "Validación",
                                JOptionPane.WARNING_MESSAGE);
        }

        private static void mostrarError(
                        String mensaje,
                        SQLException ex) {

                JOptionPane.showMessageDialog(
                                null,
                                mensaje + ": " + ex.getMessage(),
                                "Error de base de datos",
                                JOptionPane.ERROR_MESSAGE);
        }
}