package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoDAO {

    private ConexionOracle con;
    private Connection conexion;

    public AccesoDAO() {
        con = new ConexionOracle();
        conexion = con.conectar();
    }

    public String consultaReserva(String nomCliente) {
        String contenido = "";
        try {
            String consulta = "SELECT r.idreserva, c.nombre,c.dni, t.nombre, o.nombre, r.precio from RESERVA r inner join CLIENTE c on r.idcliente = c.dni INNER JOIN OBRA o on r.idobra = o.idobra INNER JOIN TEATRO t on r.idteatro = t.idteatro INNER JOIN HISTORICO h ON r.idreserva = h.idreserva WHERE c.nombre=? AND h.reservado='Reservado'";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nomCliente);
            ResultSet resul = sentencia.executeQuery();

            while (resul.next()) {
                int idReserva;
                String nombreCliente;
                String idCliente;
                String nombreTeatro;
                String nombreObra;
                float precio;

                idReserva = resul.getInt(1);
                nombreCliente = resul.getString(2);
                idCliente = resul.getString(3);
                nombreTeatro = resul.getString(4);
                nombreObra = resul.getString(5);
                precio = resul.getFloat(6);

                contenido += "ID Reserva: " + idReserva + " | Nombre: " + nombreCliente + " | DNI: " + idCliente + " | Teatro: " + nombreTeatro + " | Obra: " + nombreObra + " | Precio: " + precio;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contenido;
    }

    public ArrayList<String> recorrerClientes() {
        ArrayList<String> lista = new ArrayList<>();
        try {
            String consulta = "SELECT nombre FROM CLIENTE";
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                lista.add(resultado.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
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

    public void introducirDatos(String dniCliente, String idTeatro, String idObra, double precio) {

    }

    public ArrayList nombreObras() {
        ArrayList<String> obras = new ArrayList<>();
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

    public ArrayList idsReserva(String nomCliente) {
        String consulta = "SELECT idreserva FROM RESERVA r INNER JOIN CLIENTE c ON r.idcliente = c.dni WHERE c.nombre=?";
        ArrayList<Integer> lista = new ArrayList<>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nomCliente);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                lista.add(resultado.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean eliminarReserva(int idReserva) {
        boolean hecho = false;
        String sqlString = "DELETE FROM HISTORICO WHERE idreserva=?";
        String sqlString2 = "DELETE FROM RESERVA WHERE idreserva=?";
        try {
            PreparedStatement sentencia1 = conexion.prepareStatement(sqlString);
            sentencia1.setInt(1, idReserva);
            sentencia1.executeUpdate();
            PreparedStatement sentencia2 = conexion.prepareStatement(sqlString2);
            sentencia2.setInt(1, idReserva);
            sentencia2.executeUpdate();
            hecho = true;
        } catch (SQLException ex) {
            hecho = false;
            System.out.println(ex.toString());
        }
        return hecho;
    }
}
