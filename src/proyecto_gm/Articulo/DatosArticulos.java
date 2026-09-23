package proyecto_gm.Articulo;

import proyecto_gm.Marca.Marca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import proyecto_gm.Categoria.Categoria;
import principal.ConexionBD;

public class DatosArticulos {

    public static List<Articulo> listar() {
        List<Articulo> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return lista;
        }
        try ( CallableStatement cstmt = conn.prepareCall("{ CALL listar_articulos() }")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Articulo art = new Articulo();
                art.setId(rs.getInt("xCodigo"));
                art.setDescripcion(rs.getString("xDescripcion"));
                art.setCaracteristicas(rs.getString("xCaracteristicas"));
                art.setCantidad(rs.getDouble("xCantidad"));

                Categoria cat = new Categoria();
                cat.setId(rs.getInt("xIdCategoria"));
                cat.setDescripcion(rs.getString("xDescripcionCat"));
                art.setCategoria(cat);

                Marca mar = new Marca();
                mar.setIdMarca(rs.getInt("xIdMarca"));
                mar.setDescripcion(rs.getString("xDescripcionMar"));
                art.setMarca(mar);

                lista.add(art);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar artículos: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public static void insertar(Articulo art) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return;
        }
        try ( CallableStatement cstmt = conn.prepareCall("{ CALL insertar_articulos(?, ?, ?, ?, ?, ?) }")) {
            cstmt.setInt(1, 0);
            cstmt.setString(2, art.getDescripcion());
            cstmt.setString(3, art.getCaracteristicas());
            cstmt.setDouble(4, art.getCantidad());
            cstmt.setInt(5, art.getCategoria().getId());
            cstmt.setInt(6, art.getMarca().getIdMarca());

            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Artículo registrado.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar artículo: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void actualizar(Articulo art) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return;
        }
        try ( CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_articulos(?, ?, ?, ?, ?, ?) }")) {
            cstmt.setInt(1, art.getId());
            cstmt.setString(2, art.getDescripcion());
            cstmt.setString(3, art.getCaracteristicas());
            cstmt.setDouble(4, art.getCantidad());
            cstmt.setInt(5, art.getCategoria().getId());
            cstmt.setInt(6, art.getMarca().getIdMarca());
            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Artículo actualizado.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar artículo: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminar(int id) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return;
        }
        try ( CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_articulos(?) }")) {
            cstmt.setInt(1, id);
            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Artículo eliminado.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar artículo: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}
