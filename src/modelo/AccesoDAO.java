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
    }

    public String consultaReserva(String nomCliente) {
        String contenido = "";
        try {
            String consulta = "SELECT r.idreserva, c.nombre,c.dni, t.nombre, o.nombre, r.precio from RESERVA r inner join CLIENTE c on r.idcliente = c.dni INNER JOIN OBRA o on r.idobra = o.idobra INNER JOIN TEATRO t on r.idteatro = t.idteatro WHERE c.nombre=?";
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

                contenido += "ID Reserva: " + idReserva + " Nombre: " + nombreCliente + " DNI: " + idCliente + " Teatro: " + nombreTeatro + "Obra: " + nombreObra + " Precio: " + precio;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contenido;
    }

    public void actualizarPrecioEnReservaYTablasRelacionadas(int idReserva, float nuevoPrecio) {
    
        try {

            String queryReserva = "UPDATE Reserva SET precio = ? WHERE idReserva = ?";
            PreparedStatement statementReserva = conexion.prepareStatement(queryReserva);
            statementReserva.setFloat(1, nuevoPrecio);
            statementReserva.setInt(2, idReserva);
            statementReserva.executeUpdate();
            statementReserva.close();
            
            
            actualizarPrecioEnObraTeatro(idReserva, nuevoPrecio);
            
           
            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    private void actualizarPrecioEnObraTeatro(int idReserva, float nuevoPrecio) {
       
        try {
            String queryObraTeatro = "UPDATE Obras_Teatro OT "
                    + "INNER JOIN Reserva R ON R.idObra = OT.id_obras "
                    + "SET OT.precio = ? "
                    + "WHERE R.idReserva = ?";
            PreparedStatement statementObraTeatro = conexion.prepareStatement(queryObraTeatro);
            statementObraTeatro.setFloat(1, nuevoPrecio);
            statementObraTeatro.setInt(2, idReserva);
            statementObraTeatro.executeUpdate();
            statementObraTeatro.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void insertarReserva(String idCliente, String idObra, String idTeatro, float precio) {
        try {
            String query = "INSERT INTO RESERVA (idcliente, idObra, idteatro, precio) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, idCliente);
            statement.setString(2, idObra);
            statement.setString(3, idTeatro);
            statement.setFloat(4, precio);
            statement.executeUpdate();
            statement.close();

            System.out.println("Reserva insertada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar la reserva: " + ex.getMessage());
        }
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
