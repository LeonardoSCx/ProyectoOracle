package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConexionOracle {
    String user = "system";
    String passwd = "admin";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    Connection conexionOracle;

    public ConexionOracle() {
    }
    
    public Connection conectar(){
        try {
            conexionOracle = DriverManager.getConnection(url,user,passwd);
            if (conexionOracle != null) {
                System.out.println("Conexion establecida!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexionOracle;
    }
    
}
