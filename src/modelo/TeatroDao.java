/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Modelo.ListaTeatros;
import Modelo.Teatro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class TeatroDao {

    private ConexionOracle con;
    private Connection conexion;
    public ListaTeatros listaTeatro;

    public TeatroDao() {
        con = new ConexionOracle();
        conexion = con.conectar();
        listaTeatro = new ListaTeatros();
    }

    public void consultar() {
        String consulta = "SELECT * FROM TEATRO";
        listaTeatro = new ListaTeatros();
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resul = sentencia.executeQuery();
            while (resul.next()) {
                int idTeatro = resul.getInt(1);
                String nombre = resul.getString(2);
                String direccion = resul.getString(3);
                listaTeatro.agregarTeatro(new Teatro(idTeatro, nombre, direccion));
               

            }
        } catch (SQLException ex) {
            Logger.getLogger(TeatroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertar(String nombre, String direccion) {
        try {
            String query = "INSERT INTO TEATRO (nombre, direccion) VALUES (?, ?,)";

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.executeUpdate();
            statement.close();
            Teatro t = new Teatro(nombre, direccion);
            System.out.println("teatro insertado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar el teatro: " + ex.getMessage());
        }
    }

    public boolean eliminarReserva(int idTeatro) {
        boolean hecho = false;
        String sqlString = "DELETE FROM TEATRO WHERE idteatro=?";
        try {
            PreparedStatement sentencia1 = conexion.prepareStatement(sqlString);
            sentencia1.setInt(1, idTeatro);
            sentencia1.executeUpdate();

            hecho = true;
        } catch (SQLException ex) {
            hecho = false;
            System.out.println(ex.toString());
        }
        return hecho;
    }

    public void actualizar(String nombre, String direccion) {

        try {

            String queryReserva = "UPDATE TEATRO SET nombre = ? WHERE direccion = ?";
            PreparedStatement statementReserva = conexion.prepareStatement(queryReserva);
            statementReserva.setString(1, nombre);
            statementReserva.setString(2, direccion);
            statementReserva.executeUpdate();
            statementReserva.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
