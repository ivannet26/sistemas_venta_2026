/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// DatosGuiaRemision.java
package proyecto_gm.GuiaRemision;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosGuiaRemision {

    public static List<GuiaRemision> listar(long idGuia) {
        List<GuiaRemision> listaEntidad = new ArrayList<>();

        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return listaEntidad;
        try (CallableStatement cstmt
                      = conn.prepareCall("{ CALL sp_obtener_guiaremision(?) }")) {

            cstmt.setLong(1, idGuia);

            boolean tieneResultado = cstmt.execute();
            int numeroResultado = 0;
            GuiaRemision guia = null;

            while (true) {
                if (tieneResultado) {
                    try (ResultSet rs = cstmt.getResultSet()) {
                        if (numeroResultado == 0) {
                            if (rs.next()) {
                                guia = mapearGuia(rs);
                                listaEntidad.add(guia);
                            }
                        } else if (numeroResultado == 1 && guia != null) {
                            while (rs.next()) {
                                guia.getDetalles().add(mapearDetalle(rs));
                            }
                        } else if (numeroResultado == 2 && guia != null) {
                            while (rs.next()) {
                                guia.getConformidades().add(
                                        mapearConformidad(rs)
                                );
                            }
                        }
                    }

                    numeroResultado++;
                } else if (cstmt.getUpdateCount() == -1) {
                    break;
                }

                tieneResultado = cstmt.getMoreResults(
                        Statement.CLOSE_CURRENT_RESULT
                );
            }

        } catch (SQLException ex) {
            mostrarError("Error al obtener la guía de remisión", ex);
        }

        return listaEntidad;
    }

    public static GuiaRemision obtener(long idGuia) {
        List<GuiaRemision> listaEntidad = listar(idGuia);
        return listaEntidad.isEmpty() ? null : listaEntidad.get(0);
    }

    public static boolean insertar(GuiaRemision guia) {
        if (guia == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "La guía de remisión no puede ser nula.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (guia.getDetalles() == null || guia.getDetalles().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "La guía debe contener al menos un artículo.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        String detallesJson = construirDetallesJson(guia.getDetalles());

        String procedimiento
                = "{ CALL sp_registrar_guiaremision("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?) }";

        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            mostrarError("Error al registrar la guía de remisión", new SQLException("Sin conexión a BD"));
            return false;
        }
        try (CallableStatement cstmt = conn.prepareCall(procedimiento)) {

            cstmt.setInt(1, guia.getIdEmpresa());
            setLongNulo(cstmt, 2, guia.getIdComprobante());
            cstmt.setInt(3, guia.getIdCliente());
            cstmt.setInt(4, guia.getIdUsuario());
            cstmt.setInt(5, guia.getIdAlmacen());
            cstmt.setInt(6, guia.getIdSerieGuia());
            setTimestampNulo(cstmt, 7, guia.getFechaEmision());
            setDateNulo(cstmt, 8, guia.getFechaInicioTraslado());
            cstmt.setInt(9, guia.getIdMotivo());
            cstmt.setString(10, guia.getModalidadTraslado());
            cstmt.setString(11, guia.getDireccionOrigen());
            cstmt.setString(12, guia.getIdUbigeoOrigen());
            cstmt.setString(13, guia.getDireccionDestino());
            cstmt.setString(14, guia.getIdUbigeoDestino());
            setIntegerNulo(cstmt, 15, guia.getIdTransportista());
            setIntegerNulo(cstmt, 16, guia.getIdConductor());
            setIntegerNulo(cstmt, 17, guia.getIdVehiculo());
            setDecimalNulo(cstmt, 18, guia.getPesoTotal());
            cstmt.setInt(19, guia.getNumeroBultos());
            setStringNulo(cstmt, 20, guia.getObservaciones());
            cstmt.setString(21, detallesJson);

            cstmt.registerOutParameter(22, Types.BIGINT);
            cstmt.registerOutParameter(23, Types.BIGINT);

            cstmt.execute();

            guia.setIdGuia(cstmt.getLong(22));
            guia.setCorrelativo(cstmt.getLong(23));
            guia.setEstado("EMITIDA");

            JOptionPane.showMessageDialog(
                    null,
                    "Guía de remisión registrada correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return true;

        } catch (SQLException ex) {
            mostrarError("Error al registrar la guía de remisión", ex);
            return false;
        }
    }

    public static boolean despachar(long idGuia, int idUsuario) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return false;
        try (CallableStatement cstmt = conn.prepareCall(
                      "{ CALL sp_despachar_guiaremision(?, ?) }"
              )) {

            cstmt.setLong(1, idGuia);
            cstmt.setInt(2, idUsuario);
            cstmt.execute();

            JOptionPane.showMessageDialog(
                    null,
                    "Guía despachada correctamente.",
                    "Despacho exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return true;

        } catch (SQLException ex) {
            mostrarError("Error al despachar la guía de remisión", ex);
            return false;
        }
    }

    public static boolean registrarEntrega(
            long idGuia,
            int idUsuario,
            Timestamp fechaEntrega,
            String receptorNombre,
            String receptorDocumento,
            String observaciones) {

        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return false;
        try (CallableStatement cstmt = conn.prepareCall(
                      "{ CALL sp_registrar_entregaguia(?, ?, ?, ?, ?, ?) }"
              )) {

            cstmt.setLong(1, idGuia);
            cstmt.setInt(2, idUsuario);
            setTimestampNulo(cstmt, 3, fechaEntrega);
            cstmt.setString(4, receptorNombre);
            setStringNulo(cstmt, 5, receptorDocumento);
            setStringNulo(cstmt, 6, observaciones);

            cstmt.execute();

            JOptionPane.showMessageDialog(
                    null,
                    "Entrega registrada correctamente.",
                    "Entrega exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return true;

        } catch (SQLException ex) {
            mostrarError("Error al registrar la entrega de la guía", ex);
            return false;
        }
    }

    public static boolean anular(
            long idGuia,
            int idUsuario,
            String motivo) {

        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return false;
        try (CallableStatement cstmt = conn.prepareCall(
                      "{ CALL sp_anular_guiaremision(?, ?, ?) }"
              )) {

            cstmt.setLong(1, idGuia);
            cstmt.setInt(2, idUsuario);
            cstmt.setString(3, motivo);
            cstmt.execute();

            JOptionPane.showMessageDialog(
                    null,
                    "Guía de remisión anulada correctamente.",
                    "Anulación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return true;

        } catch (SQLException ex) {
            mostrarError("Error al anular la guía de remisión", ex);
            return false;
        }
    }

    private static GuiaRemision mapearGuia(ResultSet rs)
            throws SQLException {

        GuiaRemision guia = new GuiaRemision();

        guia.setIdGuia(rs.getLong("idguia"));
        guia.setIdEmpresa(rs.getInt("idempresa"));
        guia.setIdComprobante(
                obtenerLongNulo(rs, "idcomprobante")
        );
        guia.setIdCliente(rs.getInt("idcliente"));
        guia.setIdUsuario(rs.getInt("idusuario"));
        guia.setIdAlmacen(rs.getInt("idalmacen"));
        guia.setIdSerieGuia(rs.getInt("idserieguia"));
        guia.setCorrelativo(rs.getLong("correlativo"));
        guia.setFechaEmision(rs.getTimestamp("fechaemision"));
        guia.setFechaInicioTraslado(
                rs.getDate("fechainiciotraslado")
        );
        guia.setIdMotivo(rs.getInt("idmotivo"));
        guia.setModalidadTraslado(
                rs.getString("modalidadtraslado")
        );
        guia.setDireccionOrigen(rs.getString("direccionorigen"));
        guia.setIdUbigeoOrigen(rs.getString("idubigeoorigen"));
        guia.setDireccionDestino(rs.getString("direcciondestino"));
        guia.setIdUbigeoDestino(rs.getString("idubigeodestino"));
        guia.setDestinatarioTipoDoc(
                rs.getString("destinatariotipodoc")
        );
        guia.setDestinatarioNumeroDoc(
                rs.getString("destinatarionumerodoc")
        );
        guia.setDestinatarioNombre(
                rs.getString("destinatarionombre")
        );
        guia.setIdTransportista(
                obtenerIntegerNulo(rs, "idtransportista")
        );
        guia.setIdConductor(
                obtenerIntegerNulo(rs, "idconductor")
        );
        guia.setIdVehiculo(
                obtenerIntegerNulo(rs, "idvehiculo")
        );
        guia.setPesoTotal(rs.getBigDecimal("pesototal"));
        guia.setUnidadPeso(rs.getString("unidadpeso"));
        guia.setNumeroBultos(rs.getInt("numerobultos"));
        guia.setEstado(rs.getString("estado"));
        guia.setFechaDespacho(rs.getTimestamp("fechadespacho"));
        guia.setFechaEntrega(rs.getTimestamp("fechaentrega"));
        guia.setObservaciones(rs.getString("observaciones"));
        guia.setIdUsuarioAnulacion(
                obtenerIntegerNulo(rs, "idusuarioanulacion")
        );
        guia.setFechaAnulacion(rs.getTimestamp("fechaanulacion"));
        guia.setFechaCreacion(rs.getTimestamp("fechacreacion"));
        guia.setFechaActualizacion(
                rs.getTimestamp("fechaactualizacion")
        );
        guia.setSerie(rs.getString("serie"));
        guia.setCodigoMotivo(rs.getString("codigomotivo"));
        guia.setMotivoDescripcion(
                rs.getString("motivodescripcion")
        );
        guia.setAlmacen(rs.getString("almacen"));

        return guia;
    }

    private static GuiaRemision.Detalle mapearDetalle(ResultSet rs)
            throws SQLException {

        GuiaRemision.Detalle detalle = new GuiaRemision.Detalle();

        detalle.setIdDetalleGuia(rs.getLong("iddetalleguia"));
        detalle.setIdGuia(rs.getLong("idguia"));
        detalle.setNumeroItem(rs.getInt("nroitem"));
        detalle.setIdArticulo(rs.getInt("idarticulo"));
        detalle.setIdDetalleVenta(
                obtenerLongNulo(rs, "iddetalleventa")
        );
        detalle.setCodigoArticulo(rs.getString("codigoarticulo"));
        detalle.setDescripcion(rs.getString("descripcion"));
        detalle.setIdUnidadMedida(
                obtenerIntegerNulo(rs, "idunidadmedida")
        );
        detalle.setUnidadMedida(rs.getString("unidadmedida"));
        detalle.setCantidad(rs.getBigDecimal("cantidad"));
        detalle.setPesoUnitario(rs.getBigDecimal("pesounitario"));
        detalle.setPesoTotal(rs.getBigDecimal("pesototal"));

        return detalle;
    }

    private static GuiaRemision.ConformidadEntrega mapearConformidad(
            ResultSet rs) throws SQLException {

        GuiaRemision.ConformidadEntrega conformidad
                = new GuiaRemision.ConformidadEntrega();

        conformidad.setIdConformidad(rs.getLong("idconformidad"));
        conformidad.setIdGuia(rs.getLong("idguia"));
        conformidad.setIdUsuario(rs.getInt("idusuario"));
        conformidad.setFechaEntrega(rs.getTimestamp("fechaentrega"));
        conformidad.setReceptorNombre(
                rs.getString("receptornombre")
        );
        conformidad.setReceptorDocumento(
                rs.getString("receptordocumento")
        );
        conformidad.setObservaciones(
                rs.getString("observaciones")
        );

        return conformidad;
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
                    ? detalle.getCantidad() : BigDecimal.ZERO;

            BigDecimal pesoUnitario = detalle.getPesoUnitario() != null
                    ? detalle.getPesoUnitario() : BigDecimal.ZERO;

            json.append("{")
                .append("\"idArticulo\":")
                .append(detalle.getIdArticulo());

            if (detalle.getIdDetalleVenta() != null) {
                json.append(",\"idDetalleVenta\":")
                    .append(detalle.getIdDetalleVenta());
            }

            if (detalle.getIdUnidadMedida() != null) {
                json.append(",\"idUnidadMedida\":")
                    .append(detalle.getIdUnidadMedida());
            }

            json.append(",\"cantidad\":")
                .append(cantidad.toPlainString())
                .append(",\"pesoUnitario\":")
                .append(pesoUnitario.toPlainString())
                .append("}");
        }

        json.append("]");
        return json.toString();
    }

    private static Long obtenerLongNulo(
            ResultSet rs,
            String columna) throws SQLException {

        long valor = rs.getLong(columna);
        return rs.wasNull() ? null : valor;
    }

    private static Integer obtenerIntegerNulo(
            ResultSet rs,
            String columna) throws SQLException {

        int valor = rs.getInt(columna);
        return rs.wasNull() ? null : valor;
    }

    private static void setLongNulo(
            CallableStatement cstmt,
            int indice,
            Long valor) throws SQLException {

        if (valor == null) {
            cstmt.setNull(indice, Types.BIGINT);
        } else {
            cstmt.setLong(indice, valor);
        }
    }

    private static void setIntegerNulo(
            CallableStatement cstmt,
            int indice,
            Integer valor) throws SQLException {

        if (valor == null) {
            cstmt.setNull(indice, Types.INTEGER);
        } else {
            cstmt.setInt(indice, valor);
        }
    }

    private static void setStringNulo(
            CallableStatement cstmt,
            int indice,
            String valor) throws SQLException {

        if (valor == null || valor.trim().isEmpty()) {
            cstmt.setNull(indice, Types.VARCHAR);
        } else {
            cstmt.setString(indice, valor);
        }
    }

    private static void setTimestampNulo(
            CallableStatement cstmt,
            int indice,
            Timestamp valor) throws SQLException {

        if (valor == null) {
            cstmt.setNull(indice, Types.TIMESTAMP);
        } else {
            cstmt.setTimestamp(indice, valor);
        }
    }

    private static void setDateNulo(
            CallableStatement cstmt,
            int indice,
            Date valor) throws SQLException {

        if (valor == null) {
            cstmt.setNull(indice, Types.DATE);
        } else {
            cstmt.setDate(indice, valor);
        }
    }

    private static void setDecimalNulo(
            CallableStatement cstmt,
            int indice,
            BigDecimal valor) throws SQLException {

        if (valor == null) {
            cstmt.setNull(indice, Types.DECIMAL);
        } else {
            cstmt.setBigDecimal(indice, valor);
        }
    }

    private static void mostrarError(
            String mensaje,
            SQLException ex) {

        JOptionPane.showMessageDialog(
                null,
                mensaje + ": " + ex.getMessage(),
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE
        );
    }
}