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
    
    static final Connection conn = ConexionBD.getConnection();

    public static List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        
        try (CallableStatement cstmt = conn.prepareCall("{ CALL listar_categorias() }")) {
            
            ResultSet rs = cstmt.executeQuery();
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

    public static void insertar(Categoria categoria) {
        try (CallableStatement cstmt = conn.prepareCall("{ CALL insertar_categorias(?, ?) }")) { 
            
            cstmt.setString(1, categoria.getDescripcion());
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            
            cstmt.execute();
            
            int idGenerado = cstmt.getInt(2);
            categoria.setId(idGenerado);
            
            JOptionPane.showMessageDialog(null, "Categoría registrada exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar categoría: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void actualizar(Categoria categoria) {
        try (CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_categorias(?, ?) }")) {
            
            cstmt.setInt(1, categoria.getId());
            cstmt.setString(2, categoria.getDescripcion());
            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Categoría actualizada exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar categoría: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminar(int id) {
        try (CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_categorias(?) }")) {
            
            cstmt.setInt(1, id);
            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Categoría eliminada exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
}