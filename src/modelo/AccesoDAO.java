/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        if (conexion != null) {
            System.out.println("Hello");
        }
        //con.desconectar();
    }
    
    public void queryTable(String tabla) {
        try {
            Statement sentencia = conexion.createStatement();
            String query = "SELECT * FROM " + tabla + ";";
            ResultSet resul = sentencia.executeQuery(query);
            while (resul.next()) {
                String nombre;
                String descripcion;
                String genero;
                
                nombre = resul.getString(1);
                descripcion = resul.getString(2);
                genero = resul.getString(3);
                
                System.out.println("Nombre: " + nombre + "\nDescripción: " + descripcion + "\nGénero: " + genero);
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
