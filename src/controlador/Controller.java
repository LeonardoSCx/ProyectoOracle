package controlador;

import interfaz.MostrarReservas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author Diurno
 */
public class Controller implements ActionListener {

    MostrarReservas view;
    //interfaz.model modelo;
    Scanner escaner = new Scanner(System.in);

    //modelo que aun no se ha creado 
    public Controller(MostrarReservas view) {
        this.view = view;
        //this.modelo = modelo;

    }

    public void AnadirActionListener(ActionListener listener) {
        view.getBtnMostrar().addActionListener(listener);
        view.getBtnNuevaReserva().addActionListener(listener);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnMostrar()) {
            // por cada nombre del cliente se tiene que hacer dicha consulta 
            String clienteSeleccionado = (String) view.getDesplegableClientes().getSelectedItem();
            if (clienteSeleccionado.equals("Juan Pérez")) {
               //model.mostrarJuan(clienteSeleccionado);
            } else if (clienteSeleccionado.equals("Ana Gómez")) {
                //model.mostrarAna(clienteSeleccionado);
            } else if (clienteSeleccionado.equals("Pedro Rogrígez")) {
                //model.mostrarPedro(clienteSeleccionado);
            }else if(clienteSeleccionado.equals("María Sánchez")){
                //model.mostrarMaria(clienteSeleccionado);
            }else if(clienteSeleccionado.equals("Luis Torres")){
                //model.mostrarLuis(clienteSeleccionado);
            }
        } else if (e.getSource() == view.getBtnNuevaReserva()) {
            System.out.println("Indica si está reservado con true o false: ");
            String reservado = escaner.nextLine();
            System.out.print("Ingresa la fecha de reserva (formato: AAAA-MM-DD): ");
            String fechaTexto = escaner.nextLine();
            Date fecha = Date.valueOf(fechaTexto);
            System.out.println("Ingresa el id del historico:");
            int id_historico = escaner.nextInt();
            //model.insertarReserva(reservado,fecha,id_historico);
        }
    }

}
