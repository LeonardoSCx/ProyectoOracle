/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO;

import modelo.Clases.ListaReservas;
import modelo.Interfaz.CRUD;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Clases.Reserva;

/**
 *
 * @author leogu
 */
public class ReservaDAO implements CRUD{
    
    public ListaReservas listaReservas;
    
    public ReservaDAO() {
        listaReservas = new ListaReservas();
    }

    @Override
    public void consultar() {
        listaReservas = new ListaReservas();
        String consulta = "Select * from Reserva";
        try {
            PreparedStatement ps = sesion.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int idReserva = rs.getInt(1);
                String idCliente = rs.getString(2);
                int idObra = rs.getInt(3);
                int idTeatro = rs.getInt(4);
                float precio = rs.getFloat(5);
                listaReservas.agregarReserva(new Reserva(idReserva, idCliente, idObra, idTeatro, precio));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int insertar(Object o) {
        int filasAfectadas = 0;
        Reserva reserva = (Reserva) o;
        String consulta = "insert into Reserva (idCliente, idObra, idTeatro, precio) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = sesion.prepareStatement(consulta);
            ps.setString(1, reserva.getIdcliente());
            ps.setInt(2, reserva.getIdObra());
            ps.setInt(3, reserva.getIdteatro());
            ps.setFloat(4, reserva.getPrecio());
            
            filasAfectadas = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filasAfectadas;
    }

    @Override
    public int actualizar(Object o) {
        Reserva reserva = (Reserva) o;
        int filasAfectadas = 0;
        String consulta = "update Reserva set idCliente = ?, idObra = ?, idTeatro = ?, precio = ? WHERE idReserva = ?";
        try {
            PreparedStatement ps = sesion.prepareStatement(consulta);
            ps.setString(1, reserva.getIdcliente());
            ps.setInt(2, reserva.getIdObra());
            ps.setInt(3, reserva.getIdteatro());
            ps.setFloat(4, reserva.getPrecio());
            
            filasAfectadas = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filasAfectadas;
    }

    @Override
    public int borrar(Object o) {
        int filasAfectadas = 0;
        String consulta = "delete from Reserva where idReserva = ?";
        
        try {
            PreparedStatement ps = sesion.prepareStatement(consulta);
            ps.setInt(1, (int)o);
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filasAfectadas;
    }
    
}
