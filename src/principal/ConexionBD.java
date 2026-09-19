package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static String codPerfil = "";
    public static String nomPerfil = "";
    public static String nomUsuario = "";

    public ConexionBD() {
        // Evitar instanciación
    }

    private static String getUrlDinamica(String baseDatos) {
        String host = System.getenv("DB_HOST");
        String puerto = System.getenv().getOrDefault("DB_PORT", "3306");

        return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos
                + "?useSSL=true&requireSSL=true&allowPublicKeyRetrieval=true";
    }

    public static Connection getConnection() {
        try {
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");
            String db = "dbventa";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    getUrlDinamica(db),
                    user,
                    pass
            );

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
            return DriverManager.getConnection(
                    getUrlDinamica(dbReloj),
                    user,
                    pass
            );

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error en la conexión: " + ex.getMessage());
            return null;
        }
    }
}