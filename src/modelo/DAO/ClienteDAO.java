package modelo.DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Clases.Cliente;
import modelo.Clases.ListaClientes;
import modelo.Interfaz.CRUD;

public class ClienteDAO implements CRUD {
    public ListaClientes listaClientes;

    public ClienteDAO() {
        listaClientes = new ListaClientes();
    }

    public void consultar() {
        String sql = "SELECT * FROM CLIENTE";
        listaClientes = new ListaClientes();
        try {
            PreparedStatement sentencia = sesion.prepareStatement(sql);
            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                String dni = res.getString(1);
                String nombre = res.getString(2);
                int numero = res.getInt(3);
                String direccion = res.getString(4);
                String correo = res.getString(5);
                listaClientes.add(new Cliente(dni, nombre, numero, direccion, correo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int insertar(Object o) {
        
        String sql = "INSERT INTO CLIENTE (dni, nombre, telefono, direccion, correo) VALUES (?,?,?,?,?)";
        int filasAfectadas = 0;
        Cliente c = (Cliente) o;
        try {
            PreparedStatement sentencia = sesion.prepareStatement(sql);
            sentencia.setString(1, c.getDni());
            sentencia.setString(2, c.getNombre());
            sentencia.setInt(3, c.getTelefono());
            sentencia.setString(4, c.getDireccion());
            sentencia.setString(5, c.getDireccion());
            filasAfectadas = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAfectadas;
    }

    @Override
    public int actualizar(Object o) {
        String sql = "UPDATE CLIENTE SET nombre=?, telefono=?, direccion=?,correo=? WHERE dni=?";
        Cliente c = (Cliente) o;
        int filasAfectadas = 0;
        try {
            PreparedStatement sentencia = sesion.prepareStatement(sql);
            sentencia.setString(1, c.getNombre());
            sentencia.setInt(2, c.getTelefono());
            sentencia.setString(3, c.getDireccion());
            sentencia.setString(4, c.getCorreo());
            sentencia.setString(5, c.getDni());
            filasAfectadas = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAfectadas;
    }

    @Override
    public int borrar(Object o) {
        String sql = "DELETE FROM CLIENTE WHERE dni=?";
        String dni = (String) o;
        int filasAfectadas = 0;
        try {
            PreparedStatement sentencia = sesion.prepareStatement(sql);
            sentencia.setString(1, dni);
            filasAfectadas = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAfectadas;
    }

}
