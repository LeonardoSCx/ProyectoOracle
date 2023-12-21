/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diurno
 */
public class ListaClientes {
    private List<Cliente> clientes;

    public ListaClientes() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void add(Cliente cliente) {
        clientes.add(cliente);
    }
}
