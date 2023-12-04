package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class AccesoDAO {
    private ConexionOracle con;
    private Connection conexion;

    public AccesoDAO() {

        con = new ConexionOracle();
        conexion = con.conectar();
//        if (conexion != null) {
//            System.out.println("Hello");
//        }
        //con.desconectar();
    }
    
    public void queryTable(String tabla) {
        try {
            Statement sentencia = conexion.createStatement();
            String query = "SELECT * FROM obra";
            ResultSet resul = sentencia.executeQuery(query);
            while (resul.next()) {
                int idObra;
                String nombre;
                String descripcion;
                String genero;
                idObra = resul.getInt(1);
                nombre = resul.getString(2);
                descripcion = resul.getString(3);
                genero = resul.getString(4);
                System.out.println("Nombre: " + nombre + "\nDescripción: " + descripcion + "\nGénero: " + genero);
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
