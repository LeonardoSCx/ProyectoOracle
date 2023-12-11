package controlador;

import interfaz.MostrarReservas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.AccesoDAO;

public class Controller implements ActionListener {
    AccesoDAO model;
    MostrarReservas view;
    

    public Controller(MostrarReservas view, AccesoDAO model) {
        this.view = view;
        this.model = model;
        agregarListener(this);
        cargarClientes();
        
    }

    public void agregarListener(ActionListener listener) {
        view.getBtnMostrar().addActionListener(listener);
        view.getBtnNuevaReserva().addActionListener(listener);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        if(comando.equals("Mostrar")){
            String cliente = view.getDesplegableClientes().getSelectedItem().toString();
            if (cliente == null) {
                System.out.println("Elige un cliente!");
            }else{
                view.txtReservas.setText(model.consultaReserva(cliente));
            }
            
        }

    }
    public void cargarClientes(){
        ArrayList<String> clientes  = model.recorrerClientes();
        for (String cliente : clientes) {
            view.desplegableClientes.addItem(cliente);
        }
    }

}
