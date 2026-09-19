package proyecto_gm.TipoEmpleado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosTipoEmpleado {

    public static List<TipoEmpleado> listar() {
        List<TipoEmpleado> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return lista;
        try (CallableStatement stmt = conn.prepareCall("CALL listar_tipoempleado()");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new TipoEmpleado(
                        rs.getInt("IdTipoEmpleado"),
                        rs.getString("Descripcion")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}
