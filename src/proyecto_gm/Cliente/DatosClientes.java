package proyecto_gm.Cliente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosClientes {

    public static List<Cliente> listar(String filtro) {
        List<Cliente> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return lista;
        }
        try ( CallableStatement cstmt = conn.prepareCall("{ CALL listar_clientes(?) }")) {
            cstmt.setString(1, filtro);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNombreEmpresa(rs.getString("NombreEmpresa"));
                cliente.setProyecto(rs.getString("proyecto"));
                cliente.setTipoCliente(rs.getString("TipoCliente"));
                lista.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar clientes: " + ex.getMessage(), "Error de BD", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public static boolean insertar(Cliente cliente) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL insertar_cliente(?, ?, ?) }")) {
            cstmt.setString(1, cliente.getNombreEmpresa());
            cstmt.setString(2, cliente.getProyecto());
            cstmt.setString(3, cliente.getTipoCliente());

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar cliente: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean actualizar(Cliente cliente) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL actualizar_cliente(?, ?, ?, ?) }")) {
            cstmt.setInt(1, cliente.getIdCliente());
            cstmt.setString(2, cliente.getNombreEmpresa());
            cstmt.setString(3, cliente.getProyecto());
            cstmt.setString(4, cliente.getTipoCliente());

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean eliminar(int id) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) {
            return false;
        }

        try ( CallableStatement cstmt = conn.prepareCall("{ CALL eliminar_cliente(?) }")) {
            cstmt.setInt(1, id);

            if (cstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
