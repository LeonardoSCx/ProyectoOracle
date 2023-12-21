package modelo.DAO;

import modelo.Clases.ListaObras;
import modelo.Clases.Obra;
import modelo.Interfaz.CRUD;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObraDAO implements CRUD {

    public ListaObras listaobra;

    public ObraDAO() {
        listaobra = new ListaObras();
    }

    @Override
    public void consultar() {
        listaobra = new ListaObras();
        String query = "SELECT * FROM Obra";
        try {
            PreparedStatement preparedStatement = sesion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idObra = resultSet.getInt("idObra");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                String genero = resultSet.getString("genero");
                listaobra.agregarObra(new Obra(idObra, nombre, descripcion, genero));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ObraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int insertar(Object o) {
        int filasAfectadas = 0;
        Obra obra = (Obra) o;
        String query = "INSERT INTO Obra (nombre, descripcion, genero) VALUES (?, ?, ?)";
        try{
            PreparedStatement preparedStatement = sesion.prepareStatement(query);
            preparedStatement.setString(1, obra.getNombre());
            preparedStatement.setString(2, obra.getDescripcion());
            preparedStatement.setString(3, obra.getGenero());
            filasAfectadas = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;

    }

    @Override
    public int actualizar(Object o) {
        Obra obra = (Obra) o;
        int filasAfectadas = 0;
        String query = "UPDATE Obra SET nombre = ?, descripcion = ?, genero = ? WHERE idObra = ?";
        try (PreparedStatement preparedStatement = sesion.prepareStatement(query)) {
            preparedStatement.setString(1, obra.getNombre());
            preparedStatement.setString(2, obra.getDescripcion());
            preparedStatement.setString(3, obra.getGenero());
            preparedStatement.setInt(4, obra.getIdObra());
            filasAfectadas = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    @Override
    public int borrar(Object o) {
        int filasAfectadas = 0;
        String query = "DELETE FROM Obra WHERE idObra = ?";
        try (PreparedStatement preparedStatement = sesion.prepareStatement(query)) {
            preparedStatement.setInt(1, (int) o);
            filasAfectadas = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;
    }

}
