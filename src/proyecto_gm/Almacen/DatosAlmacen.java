package proyecto_gm.Almacen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosAlmacen {

    public static List<Almacen> listar() {
        List<Almacen> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return lista;
        }

        try (CallableStatement cstmt = conn.prepareCall("{ CALL listar_almacenes() }"); ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                Almacen almacen = new Almacen();
                almacen.setIdAlmacen(rs.getInt("idalmacen"));
                almacen.setDescripcion(rs.getString("descripcion"));
                lista.add(almacen);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar almacenes: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public static boolean insertar(Almacen almacen) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try (CallableStatement cstmt = conn.prepareCall("{ CALL insertar_almacenes(?) }")) {
            cstmt.setString(1, almacen.getDescripcion());

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Almacén registrado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar almacén: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean actualizar(Almacen almacen) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try (CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_almacen(?, ?) }")) {
            cstmt.setInt(1, almacen.getIdAlmacen());
            cstmt.setString(2, almacen.getDescripcion());

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Almacén actualizado exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar almacén: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean eliminar(int id) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try (CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_almacen(?) }")) {
            cstmt.setInt(1, id);

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Almacén eliminado exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar almacén: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
