package principal;


import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static String codPerfil = "";
    public static String nomPerfil = "";
    public static String nomUsuario = "";
    //conexion a base de datoa Reloj de actividad GMADMINISTRACIOn MYSQL
    
    public ConexionBD() {
        // Evitar instanciación 
    }
    
  private static String getUrlDinamica(String baseDatos) {
        String host = "mysql-a21bb78-sistemasnet26-321c.k.aivencloud.com";
        String puerto = "10658";
        
        return "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos + 
               "?useSSL=true&requireSSL=true&allowPublicKeyRetrieval=true";
    }

    public static Connection getConnection() {
        try {
            String user = "avnadmin";
            String pass = "AVNS_3EXlVnZgr-iQnJNfzBM";
            String db = "gmingenieros"; 

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(getUrlDinamica(db), user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
            return null;
        }
    }
    
    public static Connection getConnectionAsistencia(){
        try{
            String user = "";
            String pass = "";
            String dbReloj = "gmadministracion";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(getUrlDinamica(dbReloj), user, pass);
            
        }catch(ClassNotFoundException | SQLException ex){
            System.err.println("Error en la conexión: " + ex.getMessage());
            return null;
        }
    } 
}