package proyecto_gm.Empleado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosEmpleado {

    public List<Empleado> listar(String nombre, String estado, Integer idArea, Integer idTipo) {
        List<Empleado> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return lista;
        try ( CallableStatement stmt = conn.prepareCall("CALL listar_empleados(?,?,?,?)")) {
            stmt.setString(1, (nombre == null || nombre.isBlank()) ? null : nombre.trim());
            stmt.setString(2, (estado == null || estado.isBlank()) ? "T" : estado);
            stmt.setObject(3, (idArea == null || idArea == 0) ? null : idArea);
            stmt.setObject(4, (idTipo == null || idTipo == 0) ? null : idTipo);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearDTO(rs));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    private Empleado mapearDTO(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getInt("IdEmpleado"),
                rs.getInt("IdArea"),
                rs.getInt("IdTipoEmpleado"),
                rs.getInt("IdCargo"),
                rs.getString("Apellidos"),
                rs.getString("Nombres"),
                rs.getDate("FechaNacimiento"),
                rs.getString("Sexo"),
                rs.getString("Correo"),
                rs.getString("Dni"),
                rs.getString("Celular"),
                rs.getString("Distrito"),
                rs.getString("Direccion"),
                rs.getString("Estado"),
                rs.getString("Anio"),
                rs.getString("Mes"),
                rs.getString("Area"),
                rs.getString("Cargo"),
                rs.getString("TipoEmpleado"));
    }

    public boolean insertar(Empleado entidad) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return false;
        try ( CallableStatement stmt = conn.prepareCall("CALL insertar_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
            // id 0 = sin elegir -> NULL ; fecha null -> NULL
            stmt.setObject(1, entidad.getIdArea() == 0 ? null : entidad.getIdArea(), Types.INTEGER);
            stmt.setObject(2, entidad.getIdTipoEmpleado() == 0 ? null : entidad.getIdTipoEmpleado(), Types.INTEGER);
            stmt.setObject(3, entidad.getIdCargo() == 0 ? null : entidad.getIdCargo(), Types.INTEGER);
            stmt.setString(4, entidad.getApellidos());
            stmt.setString(5, entidad.getNombres());
            stmt.setObject(6, entidad.getFechaNacimiento(), Types.DATE);
            stmt.setString(7, entidad.getSexo());
            stmt.setString(8, entidad.getCorreo());
            stmt.setString(9, entidad.getDni());
            stmt.setString(10, entidad.getCelular());
            stmt.setString(11, entidad.getDistrito());
            stmt.setString(12, entidad.getDireccion());
            stmt.setString(13, entidad.getEstado());
            stmt.setString(14, entidad.getAnio());
            stmt.setString(15, entidad.getMes());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean eliminar(Integer idEmpleado) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return false;
        try ( CallableStatement stmt = conn.prepareCall("CALL eliminar_empleados(?)");) {
            stmt.setInt(1, idEmpleado);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean actualizar(int empleadoId, Empleado entidad) {
        Connection conn = ConexionBD.getConexionCompartida();
        if (conn == null) return false;
        try ( CallableStatement stmt = conn.prepareCall("CALL actualizar_empleados(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
            stmt.setInt(1, empleadoId);
            stmt.setObject(2, entidad.getIdArea() == 0 ? null : entidad.getIdArea(), Types.INTEGER);
            stmt.setObject(3, entidad.getIdTipoEmpleado() == 0 ? null : entidad.getIdTipoEmpleado(), Types.INTEGER);
            stmt.setObject(4, entidad.getIdCargo() == 0 ? null : entidad.getIdCargo(), Types.INTEGER);
            stmt.setString(5, entidad.getApellidos());
            stmt.setString(6, entidad.getNombres());
            stmt.setObject(7, entidad.getFechaNacimiento(), Types.DATE);
            stmt.setString(8, entidad.getSexo());
            stmt.setString(9, entidad.getCorreo());
            stmt.setString(10, entidad.getDni());
            stmt.setString(11, entidad.getCelular());
            stmt.setString(12, entidad.getDistrito());
            stmt.setString(13, entidad.getDireccion());
            stmt.setString(14, entidad.getEstado());
            stmt.setString(15, entidad.getAnio());
            stmt.setString(16, entidad.getMes());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
