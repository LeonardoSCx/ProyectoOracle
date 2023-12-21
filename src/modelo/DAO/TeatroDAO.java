package modelo.DAO;

import modelo.Clases.ListaTeatros;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Clases.Teatro;
import modelo.Interfaz.CRUD;

public class TeatroDAO implements CRUD {

    public ListaTeatros listaTeatro;

    public TeatroDAO() {
        listaTeatro = new ListaTeatros();
    }

    public void consultar() {
        String consulta = "SELECT * FROM TEATRO";
        listaTeatro = new ListaTeatros();
        try {
            PreparedStatement sentencia = sesion.prepareStatement(consulta);
            ResultSet resul = sentencia.executeQuery();
            while (resul.next()) {
                int idTeatro = resul.getInt(1);
                String nombre = resul.getString(2);
                String direccion = resul.getString(3);
                listaTeatro.agregarTeatro(new Teatro(idTeatro, nombre, direccion));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(TeatroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    @Override
    public int insertar(Object o) {
        Teatro t = (Teatro) o;
        int filasAfectadas = 0;
        String query = "INSERT INTO TEATRO (nombre, direccion) VALUES (?, ?)";
        try {
            PreparedStatement statement = sesion.prepareStatement(query);
            statement.setString(1, t.getNombre());
            statement.setString(2, t.getDireccion());
            filasAfectadas = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeatroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAfectadas;
    }

    @Override
    public int actualizar(Object o) {
        int filasAfectadas = 0;
        Teatro t = (Teatro) o;
        String queryReserva = "UPDATE TEATRO SET nombre = ?, direccion = ? WHERE idteatro=?";
        PreparedStatement statementReserva;
        try {
            statementReserva = sesion.prepareStatement(queryReserva);
            statementReserva.setString(1, t.getNombre());
            statementReserva.setString(2, t.getDireccion());
            statementReserva.setInt(3, t.getIdteatro());
            filasAfectadas = statementReserva.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(TeatroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return filasAfectadas;
    }

    @Override
    public int borrar(Object o) {
        int filasAfectadas = 0;
        String sqlString = "DELETE FROM TEATRO WHERE idteatro=?";
        try {
            PreparedStatement sentencia1 = sesion.prepareStatement(sqlString);
            sentencia1.setInt(1, (int)o);
            filasAfectadas = sentencia1.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return filasAfectadas;
    }
}
