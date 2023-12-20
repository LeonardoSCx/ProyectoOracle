/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Modelo.Teatro;
import interfaz.CrearCliente;
import interfaz.CrearTeatro;
import interfaz.UI_Obra;
import interfaz.UI_Reserva;
import interfaz.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.AccesoDAO;
import modelo.TeatroDao;

/**
 *
 * @author LENOVO
 */
public class Controlador implements ActionListener {

    TeatroDao modeloTeatro;
    interfaz.Vista view;
    interfaz.UI_Obra viewObras;
    interfaz.UI_Reserva viewReserva;
    interfaz.CrearCliente viewCliente;
    interfaz.CrearTeatro viewTeatro;

    public Controlador(TeatroDao modeloTeatro, Vista view, UI_Obra viewObras, UI_Reserva viewReserva, CrearCliente viewCliente, CrearTeatro viewTeatro) {
        this.modeloTeatro = modeloTeatro;
        this.view = view;
        this.viewObras = viewObras;
        this.viewReserva = viewReserva;
        this.viewCliente = viewCliente;
        this.viewTeatro = viewTeatro;
        agregarListener(this);
    }

    public void agregarListener(ActionListener listener) {
        view.btnCRUDcliente.addActionListener(this);
        view.btnCRUDreserva.addActionListener(this);
        view.btnCRUDteatro.addActionListener(this);
        view.btnCRUDobras.addActionListener(this);

        //teatro
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();
        String idTeatro = viewCliente.cboCliente.getSelectedItem().toString();
        if (comando.equals("CRUD Teatro")) {
            viewTeatro.setVisible(true);

        } else if (comando.equals("CRUD Cliente")) {
            viewCliente.setVisible(true);
        } else if (comando.equals("CRUD Obras")) {
            viewObras.setVisible(true);
        } else if (comando.equals("CRUD Reservas")) {
            viewReserva.setVisible(true);
        } else if (comando.equals("ConsultarCliente")) {
            if (idTeatro != null) {
                modeloTeatro.consultar();
                String detalles = "";
                for (Teatro teatro : modeloTeatro.listaTeatro.getTeatros()) {
                    detalles += teatro.toString() + "\n";
                }

                viewTeatro.txtaInfoTeatro.setText(detalles);
            } else {
                viewTeatro.txtaInfoTeatro.setText("Por favor, seleccione un teatro antes de consultar.");

            }

        } else if (comando.equals("Eliminar")) {
            if (idTeatro != null) {
                int idTeatroEliminar = Integer.parseInt(idTeatro);
                boolean eliminado = modeloTeatro.eliminarReserva(idTeatroEliminar);
                if (eliminado) {
                    viewTeatro.txtaInfoTeatro.setText("Teatro eliminado correctamente.");
                } else {
                    viewTeatro.txtaInfoTeatro.setText("Error al eliminar el teatro.");
                }
            } else {
                viewTeatro.txtaInfoTeatro.setText("Por favor, seleccione un teatro antes de eliminar.");
            }
        } else if (comando.equals("Insertar")) {
            String nombre = viewTeatro.txtNombreTeatro.getText();
            String direccion = viewTeatro.txtDireccionTeatro.getText();
            if (!nombre.isEmpty() && !direccion.isEmpty()) {
                modeloTeatro.insertar(nombre, direccion);
            } else {
                System.out.println("Los textos no pueden estar vacios");
            }
        } else if (comando.equals("Modificar")) {
            String nuevoNombre = viewTeatro.txtNombreTeatro.getText();
            String nuevaDireccion = viewTeatro.txtDireccionTeatro.getText();

            if (!idTeatro.isEmpty() && !nuevoNombre.isEmpty() && !nuevaDireccion.isEmpty()) {

                modeloTeatro.actualizar(nuevoNombre, nuevaDireccion);
                viewTeatro.txtaInfoTeatro.setText("Teatro actualizado correctamente.");
            } else {
                viewTeatro.txtaInfoTeatro.setText("Todos los campos deben estar llenos.");
            }
        }

    }

}
