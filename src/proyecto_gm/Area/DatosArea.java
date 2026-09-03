package proyecto_gm.Area;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import principal.ConexionBD;

public class DatosArea {

    static final Connection conn = ConexionBD.getConnection();
    
    public static List<Area> listar() {
        List<Area> listaAreas = new ArrayList<>();
        
            try (CallableStatement stmt = conn.prepareCall("CALL listar_areas()"); 
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Area area = new Area(rs.getInt("IdArea"), rs.getString("descripcion"));
                    listaAreas.add(area);
                }
            }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        return listaAreas;
    }

    public static boolean insertar(Area area) {
        boolean exito = false;
            try (CallableStatement stmt = conn.prepareCall("{CALL insertar_areas(?)}")) {
                stmt.setString(1, area.getDescripcionArea());
                if (stmt.executeUpdate() > 0) exito = true;
            }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        return exito;
    }

    public static boolean actualizar(Area area) {
        boolean exito = false;
            try (CallableStatement stmt = conn.prepareCall("{CALL actualizar_areas(?, ?)}")) {
                stmt.setInt(1, area.getIdArea());
                stmt.setString(2, area.getDescripcionArea());
                if (stmt.executeUpdate() > 0) exito = true;
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        return exito;
    }

    public static boolean eliminar(int idArea) {
        boolean exito = false;
            try (CallableStatement stmt = conn.prepareCall("{CALL eliminar_areas(?)}")) {
                stmt.setInt(1, idArea);
                stmt.execute();
                exito = true;
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        return exito;
    }
}