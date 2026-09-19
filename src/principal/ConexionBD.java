package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static String codPerfil = "";
    public static String nomPerfil = "";
    public static String nomUsuario = "";
    private static Connection conexionCompartida;

    public ConexionBD() {
        // Evitar instanciación 
    }

    private static String obtenerUrl(String baseDatos) {
        String host = "";
        String puerto = "";

        return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos
                + "?useSSL=true&requireSSL=true&allowPublicKeyRetrieval=true";
    }

    public static synchronized Connection getConexionCompartida() {
        try {
            if (conexionCompartida != null && !conexionCompartida.isClosed() && conexionCompartida.isValid(2)) {
                return conexionCompartida;
            }
        } catch (SQLException e) {
            // cae a reabrir
        }
        conexionCompartida = getConnection();
        return conexionCompartida;
    }

    public static Connection getConnection() {
        try {
            String user = "";
            String pass = "";
            String db = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(obtenerUrl(db), user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
            return null;
        }
    }

    public static Connection getConnectionAsistencia() {
        try {
            String user = "";
            String pass = "";
            String dbReloj = "gmadministracion";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(obtenerUrl(dbReloj), user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error en la conexión: " + ex.getMessage());
            return null;
        }
    }
}
