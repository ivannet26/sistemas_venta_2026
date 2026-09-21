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
        String host = "mysql-2488cd51-iatanacio-db42.h.aivencloud.com";
        String puerto = "17476";

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
            // ponytail: credenciales por entorno, nunca hardcodeadas (push protection GH013 las bloquea)
            String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "avnadmin";
            String pass = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";
            String db = "dbventa";

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
