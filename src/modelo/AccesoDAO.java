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

    public String getIDCliente(String nombreCliente) {
        String consulta = "SELECT dni FROM CLIENTE WHERE nombre=?";
        String idcliente = "";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreCliente);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                idcliente = resultado.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idcliente;
    }

    public int getIDObra(String nombreCliente) {
        String consulta = "SELECT idobra FROM OBRA WHERE nombre=?";
        int idobra = 0;
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreCliente);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                idobra = resultado.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idobra;
    }

    public int getIDTeatro(String nombreCliente) {
        String consulta = "SELECT idteatro FROM TEATRO WHERE nombre=?";
        int idTeatro = 0;
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreCliente);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                idTeatro = resultado.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idTeatro;
    }

    public void insertarReserva(String idCliente, int idObra, int idTeatro, float precio) {
        try {
            String query = "INSERT INTO RESERVA (idcliente, idObra, idteatro, precio) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, idCliente);
            statement.setInt(2, idObra);
            statement.setInt(3, idTeatro);
            statement.setFloat(4, precio);
            statement.executeUpdate();
            statement.close();

            System.out.println("Reserva insertada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar la reserva: " + ex.getMessage());
        }
    }

    public int getLastReserva() {
        int idReserva = 0;
        String consulta = "SELECT MAX(idreserva) FROM RESERVA";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                idReserva = resultado.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idReserva;
    }

    public void insertarHistorico(int idReserva) {
        String sql = "{call pa_insertar_historico(?,?,?)}";
        try {
            CallableStatement llamada = conexion.prepareCall(sql);
            llamada.setString(1, "Reservado");
            llamada.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            llamada.setInt(3, idReserva);
            llamada.executeUpdate();
            llamada.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Mondongo");
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
//            conexion.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarPrecioEnObraTeatro(int idReserva, float nuevoPrecio) {

        try {
            String queryObraTeatro = "UPDATE (SELECT OT.precio  FROM RESERVA R inner join OBRAS_TEATRO  OT on R.idteatro = OT.id_obras WHERE r.idreserva = ?) t set t.precio = ?";
//            String queryObraTeatro = "UPDATE Obras_Teatro OT "
//                    + "INNER JOIN Reserva R ON R.idObra = OT.id_obras "
//                    + "SET OT.precio = ? "
//                    + "WHERE R.idReserva = ?";
            PreparedStatement statementObraTeatro = conexion.prepareStatement(queryObraTeatro);
            statementObraTeatro.setInt(1, idReserva);
            statementObraTeatro.setFloat(2, nuevoPrecio);
            statementObraTeatro.executeUpdate();
            statementObraTeatro.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
