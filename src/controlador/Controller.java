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
    CrearReserva viewInsert;

    public Controller(MostrarReservas view, AccesoDAO model) {
        this.view = view;
        this.model = model;
        agregarListener(this);
        cargarClientes();

    }

    public void agregarListener(ActionListener listener) {
        view.getBtnMostrar().addActionListener(listener);
        view.getBtnNuevaReserva().addActionListener(listener);
        view.btnEliminar.addActionListener(listener);
        view.btnNuevaReserva.addActionListener(listener);
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
}
