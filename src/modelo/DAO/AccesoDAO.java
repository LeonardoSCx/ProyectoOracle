/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO;

/**
 *
 * @author leogu
 */
public class AccesoDAO {
    public ClienteDAO clienteDAO;
    public ObraDAO obraDAO;
    public TeatroDAO teatroDAO;
    public ReservaDAO reservaDAO;

    public AccesoDAO(ClienteDAO clienteDAO, ObraDAO obraDAO, TeatroDAO teatroDAO, ReservaDAO reservaDAO) {
        this.clienteDAO = clienteDAO;
        this.obraDAO = obraDAO;
        this.teatroDAO = teatroDAO;
        this.reservaDAO = reservaDAO;
    }
    
}
