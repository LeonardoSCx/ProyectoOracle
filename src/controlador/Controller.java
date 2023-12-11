package controlador;

import interfaz.CrearReserva;
import interfaz.MostrarReservas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.AccesoDAO;

public class Controller implements ActionListener {

    AccesoDAO model;
    MostrarReservas view;
    CrearReserva viewReserva;

    public Controller(MostrarReservas view, CrearReserva viewReserva, AccesoDAO model) {
        this.view = view;
        this.model = model;
        this.viewReserva = viewReserva;
        agregarListener(this);
        cargarClientes();
        CargarObra();
        CargarClienteReserva();
        cargarTeatro();
    }

    public void agregarListener(ActionListener listener) {
        view.getBtnMostrar().addActionListener(listener);
        view.getBtnNuevaReserva().addActionListener(listener);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        if (comando.equals("Mostrar")) {
            String cliente = view.getDesplegableClientes().getSelectedItem().toString();
            if (cliente == null) {
                System.out.println("Elige un cliente!");
            } else {
                view.txtReservas.setText(model.consultaReserva(cliente));
            }

        } else if (comando.equals("CREAR NUEVA RESERVA")) {
            viewReserva.setVisible(true);

        } else if (comando.equals("RESERVAR")) {
            String clienteReserva = viewReserva.desplegableClienteReserva.getSelectedItem().toString();
            String obras = viewReserva.desplegableObras.getSelectedItem().toString();
            String teatro = viewReserva.desplegableTeatros.getSelectedItem().toString();
            if (clienteReserva != null && obras != null && teatro != null) {
                if (!viewReserva.txtCoste.getText().isEmpty()) {

                    float precio = Float.parseFloat(viewReserva.txtCoste.getText());
                    model.insertarReserva(clienteReserva, obras, teatro, precio);
                } else {
                    System.out.println("El campo de precio está vacío.");
                }

            } else {
                System.out.println("Por favor, seleccione un cliente, una obra y un teatro.");
            }

        }
    }

    public void cargarClientes() {
        ArrayList<String> clientes = model.recorrerClientes();
        for (String cliente : clientes) {
            view.desplegableClientes.addItem(cliente);
        }
    }

    public void CargarObra() {
        ArrayList<String> obras = model.nombreObras();
        for (String obra : obras) {
            viewReserva.desplegableObras.addItem(obra);
        }
    }

    public void CargarClienteReserva() {
        ArrayList<String> clientes = model.recorrerClientes();
        for (String cliente : clientes) {
            viewReserva.desplegableClienteReserva.addItem(cliente);
        }
    }

    public void cargarTeatro() {
        ArrayList<String> teatros = model.mostrarTeatro();
        for (String teatro : teatros) {
            viewReserva.desplegableTeatros.addItem(teatro);
        }
    }
}
