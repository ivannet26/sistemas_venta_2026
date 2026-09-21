package proyecto_gm.Marca;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosMarcas {

    public static List<Marca> listar() {
        List<Marca> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return lista;
        }
        try ( CallableStatement cstmt = conn.prepareCall("{ CALL listar_marcas() }")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("IdMarca"));
                marca.setDescripcion(rs.getString("Descripcion"));
                lista.add(marca);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar marcas: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
    public static boolean insertar(Marca marca) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL insertar_marca(?, ?) }")) {
            cstmt.setString(1, marca.getDescripcion());
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);

            if (cstmt.executeUpdate() > 0) {
                int idGenerado = cstmt.getInt(2);
                marca.setIdMarca(idGenerado);
                JOptionPane.showMessageDialog(null, "Marca registrada exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar marca: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean actualizar(Marca marca) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_marca(?, ?) }")) {
            cstmt.setInt(1, marca.getIdMarca());
            cstmt.setString(2, marca.getDescripcion());

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Marca actualizada exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar marca: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean eliminar(int id) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_marca(?) }")) {
            cstmt.setInt(1, id);

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Marca eliminada exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar marca: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
