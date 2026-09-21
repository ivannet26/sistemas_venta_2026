package proyecto_gm.Categoria;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosCategoria {

    public static List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return lista;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL listar_categorias() }");  ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("IdCategoria"));
                categoria.setDescripcion(rs.getString("Descripcion"));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar categorías: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public static boolean insertar(Categoria categoria) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL insertar_categorias(?, ?) }")) {
            cstmt.setString(1, categoria.getDescripcion());
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);

            if (cstmt.executeUpdate() > 0) {
                int idGenerado = cstmt.getInt(2);
                categoria.setId(idGenerado);
                JOptionPane.showMessageDialog(null, "Categoría registrada exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar categoría: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean actualizar(Categoria categoria) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_categorias(?, ?) }")) {
            cstmt.setInt(1, categoria.getId());
            cstmt.setString(2, categoria.getDescripcion());

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Categoría actualizada exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar categoría: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean eliminar(int id) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_categorias(?) }")) {
            cstmt.setInt(1, id);

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Categoría eliminada exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
