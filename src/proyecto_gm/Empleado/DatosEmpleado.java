package proyecto_gm.Empleado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosEmpleado {

    static final Connection conn = ConexionBD.getConnection();

    public List<EmpleadoDTO> listar(String nombre, String estado, Integer idArea, Integer idTipo) {
        List<EmpleadoDTO> lista = new ArrayList<>();
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

    private EmpleadoDTO mapearDTO(ResultSet rs) throws SQLException {
        return new EmpleadoDTO(
                rs.getInt("IdEmpleado"),
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
                rs.getInt("IdArea"),
                rs.getString("Area"),
                rs.getInt("IdCargo"),
                rs.getString("Cargo"),
                rs.getInt("IdTipoEmpleado"),
                rs.getString("TipoEmpleado"));
    }

    public boolean insertar(Empleado entidad) {
        try ( CallableStatement stmt = conn.prepareCall("CALL insertar_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
            stmt.setInt(1, entidad.getIdArea());
            stmt.setInt(2, entidad.getIdCargo());
            stmt.setInt(3, entidad.getIdTipoEmpleado());
            stmt.setString(4, entidad.getApellidos());
            stmt.setString(5, entidad.getNombres());
            stmt.setDate(6, entidad.getFechaNacimiento());
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
        try ( CallableStatement stmt = conn.prepareCall("CALL eliminar_empleados(?)");) {
            stmt.setInt(1, idEmpleado);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean actualizar(Empleado entidad) {
        try ( CallableStatement stmt = conn.prepareCall("CALL actualizar_empleados(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {
            stmt.setInt(1, entidad.getIdEmpleado());
            stmt.setInt(2, entidad.getIdArea());
            stmt.setInt(3, entidad.getIdCargo());
            stmt.setInt(4, entidad.getIdTipoEmpleado());
            stmt.setString(5, entidad.getApellidos());
            stmt.setString(6, entidad.getNombres());
            stmt.setDate(7, entidad.getFechaNacimiento());
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
