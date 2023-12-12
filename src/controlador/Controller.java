package controlador;

import interfaz.CrearReserva;
import interfaz.ActualizarDatos;
import interfaz.MostrarReservas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.AccesoDAO;

public class Controller implements ActionListener {

    AccesoDAO model;
    MostrarReservas view;
    CrearReserva viewReserva;
    ActualizarDatos actualizarDatos;

    public Controller(MostrarReservas view, AccesoDAO model, CrearReserva viewReserva, ActualizarDatos actualizarDatos) {
        this.view = view;
        this.model = model;
        this.viewReserva = viewReserva;
        this.actualizarDatos = actualizarDatos;
        agregarListener(this);
        cargarClientes();
        CargarObra();
        CargarClienteReserva();
        cargarTeatro();

    }

    public void agregarListener(ActionListener listener) {
        view.getBtnMostrar().addActionListener(listener);
        view.getBtnNuevaReserva().addActionListener(listener);
        view.btnEliminar.addActionListener(listener);
        view.btnNuevaReserva.addActionListener(listener);
        viewReserva.btnReservar.addActionListener(listener);
        actualizarDatos.btnActualizar.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String cliente = view.getDesplegableClientes().getSelectedItem().toString();
        if (comando.equals("Mostrar")) {
            if (cliente == null) {
                System.out.println("Elige un cliente!");
            } else {
                view.txtReservas.setText(model.consultaReserva(cliente));
                cargarReservas(cliente);
            }
        } else if (comando.equals("Eliminar Reserva")) {
            int idReserva = Integer.parseInt(view.desplegableReservas.getSelectedItem().toString());
            if (view.desplegableReservas.getSelectedIndex() == -1) {
                System.out.println("Elige una reseva");
            } else {
                if (model.eliminarReserva(idReserva)) {
                    System.out.println("Reserva eliminada!");
                    cargarReservas(cliente);
                    view.txtReservas.setText(model.consultaReserva(cliente));
                } else {
                    System.out.println("Algo salió mal");
                }
            }
        } else if (comando.equals("Crear nueva Reserva")) {
            viewReserva.setVisible(true);
        } else if (comando.equals("RESERVAR")) {
            String clienteReserva = viewReserva.desplegableClienteReserva.getSelectedItem().toString();
            String obras = viewReserva.desplegableObras.getSelectedItem().toString();
            String teatro = viewReserva.desplegableTeatros.getSelectedItem().toString();
            if (clienteReserva != null && obras != null && teatro != null) {
                if (!viewReserva.txtCoste.getText().isEmpty()) {
                    float precio = Float.parseFloat(viewReserva.txtCoste.getText());
                    model.insertarReserva(model.getIDCliente(clienteReserva), model.getIDObra(obras), model.getIDTeatro(teatro), precio);
                    model.insertarHistorico(model.getLastReserva());
                } else {
                    System.out.println("El campo de precio está vacío.");
                }

            } else {
                System.out.println("Por favor, seleccione un cliente, una obra y un teatro.");
            }
        } else if (comando.equals("Actualizar")) {
            int idObra = Integer.parseInt(actualizarDatos.txtObra.getText());
            int precio = Integer.parseInt( actualizarDatos.txtPrecio.getText());
            int idTeatro = Integer.parseInt(actualizarDatos.txtTeatro.getText());
        }
    }

    public void cargarClientes() {
        view.desplegableClientes.removeAllItems();
        ArrayList<String> clientes = model.recorrerClientes();
        for (String cliente : clientes) {
            view.desplegableClientes.addItem(cliente);
        }
    }

    public void cargarReservas(String cliente) {
        view.desplegableReservas.removeAllItems();
        ArrayList<Integer> reservas = model.idsReserva(cliente);
        for (int id : reservas) {
            view.desplegableReservas.addItem(id + "");
        }
    }

    public void CargarObra() {
        viewReserva.desplegableObras.removeAllItems();
        ArrayList<String> obras = model.nombreObras();
        for (String obra : obras) {
            viewReserva.desplegableObras.addItem(obra);
        }
    }

    public void CargarClienteReserva() {
        viewReserva.desplegableClienteReserva.removeAllItems();
        ArrayList<String> clientes = model.recorrerClientes();
        for (String cliente : clientes) {
            viewReserva.desplegableClienteReserva.addItem(cliente);
        }
    }

    public void cargarTeatro() {
        viewReserva.desplegableTeatros.removeAllItems();
        ArrayList<String> teatros = model.mostrarTeatro();
        for (String teatro : teatros) {
            viewReserva.desplegableTeatros.addItem(teatro);
        }
    }
}
