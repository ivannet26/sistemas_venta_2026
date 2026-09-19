package proyecto_gm.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import proyecto_gm.Categoria.Categoria;
import principal.ConexionBD;


public class DatosArticulos {

    public static List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return lista;
        try (CallableStatement cstmt = conn.prepareCall("{ CALL listar_categorias() }")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("IdCategoria"));
                cat.setDescripcion(rs.getString("Descripcion"));
                lista.add(cat);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar categorías: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
    public static List<Marca> listarMarcas() {
        List<Marca> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return lista;
        try (CallableStatement cstmt = conn.prepareCall("{ CALL listar_marcas() }")) {
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

    public static List<Articulo> listar() {
        List<Articulo> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return lista;
        try (CallableStatement cstmt = conn.prepareCall("{ CALL listar_articulos() }")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Articulo art = new Articulo();
                art.setId(rs.getInt("xCodigo"));
                art.setDescripcion(rs.getString("xDescripcion"));
                art.setCaracteristicas(rs.getString("xCaracteristicas"));
                art.setCantidad(rs.getDouble("xCantidad"));

                // Crear objeto Categoria COMPLETO (con ID y Descripción)
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("xIdCategoria"));
                cat.setDescripcion(rs.getString("xDescripcionCat"));
                art.setCategoria(cat);

                // Crear objeto Marca COMPLETO (con ID y Descripción)
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
        if (conn == null) return;
        try (CallableStatement cstmt = conn.prepareCall("{ CALL insertar_articulos(?, ?, ?, ?, ?, ?) }")) {
            cstmt.setInt(1, 0); 
            cstmt.setInt(2, art.getCategoria().getId());
            cstmt.setInt(3, art.getMarca().getIdMarca());
            cstmt.setString(4, art.getCaracteristicas());
            cstmt.setString(5, art.getDescripcion());
            cstmt.setDouble(6, art.getCantidad());

            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Artículo registrado.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar artículo: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void actualizar(Articulo art) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return;
        try (CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_articulos(?, ?, ?, ?, ?, ?) }")) {
            cstmt.setInt(1, art.getId());
            cstmt.setInt(2, art.getCategoria().getId());
            cstmt.setInt(3, art.getMarca().getIdMarca());
            cstmt.setString(4, art.getCaracteristicas());
            cstmt.setString(5, art.getDescripcion());
            cstmt.setDouble(6, art.getCantidad());
            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Artículo actualizado.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar artículo: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void eliminar(int id) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return;
        try (CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_articulos(?) }")) {
            cstmt.setInt(1, id);
            cstmt.execute();
            JOptionPane.showMessageDialog(null, "Artículo eliminado.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar artículo: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}