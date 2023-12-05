package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diurno
 */
public class AccesoDAO {

    private ConexionOracle con;
    private Connection conexion;
    ArrayList<String> obras = new ArrayList<>();

    public AccesoDAO() {

        con = new ConexionOracle();
        conexion = con.conectar();
        //con.desconectar();
    }

    public void queryTable(String tabla) {
        try {
            Statement sentencia = conexion.createStatement();
            String query = "SELECT * FROM " + tabla.toUpperCase();
            ResultSet resul = sentencia.executeQuery(query);
            while (resul.next()) {
                int idReserva;
                int idCliente;
                int idObra;
                int idTeatro;
                float precio;

                idReserva = resul.getInt(1);
                idCliente = resul.getInt(2);
                idObra = resul.getInt(3);
                idTeatro = resul.getInt(4);
                precio = resul.getInt(5);

                System.out.println("Reserva: " + idReserva + "\nID Cliente: " + idCliente
                        + "\nId Obra: " + idObra + "\nID Teatro: " + idTeatro + "\n"
                        + "Precio: " + precio);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] recorrerClientes() {
        String[] clientes = new String[5];
        int indice = 0;
        try {
            String consulta = "SELECT nombre from CLIENTE";
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));

                clientes[indice] = resultado.getString(1);
                indice++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public ArrayList mostrarTeatro() {

        Statement sentencia;
        ArrayList<String> lista = new ArrayList<>();
        try {
            sentencia = conexion.createStatement();
            String query = "SELECT * FROM TEATRO";
            ResultSet resul = sentencia.executeQuery(query);
            while (resul.next()) {
                String nombreTeatro = resul.getString(2);
                System.out.println(nombreTeatro);
                lista.add(nombreTeatro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }


    public ArrayList nombreObras() {

        String querySelect = "SELECT * FROM OBRA";
        Statement sentencia;
        String nomObra;
        try {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(querySelect);
            while (resultado.next()) {
                nomObra = resultado.getString(2);
                obras.add(nomObra);
                System.out.println(nomObra);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obras;

    }
}
