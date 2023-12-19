package controlador;

import interfaz.UI_Obra;

import interfaz.UI_Reserva;
import modelo.DAO.AccesoDAO;
import interfaz.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Clases.Obra;
import modelo.Clases.Teatro;

public class Controlador implements ActionListener {

    AccesoDAO modelo;

    Vista vista;

    UI_Obra vistaObra;

    UI_Reserva vistaReserva;

    public Controlador(AccesoDAO modelo, Vista vista, UI_Obra vistaObra, UI_Reserva vistaReserva) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaObra = vistaObra;
        this.vistaReserva = vistaReserva;
        agregarListener(this);
        cargarIdObra();
    }

    public void agregarListener(ActionListener listener) {
        // Vista principal
        vista.btnCRUDcliente.addActionListener(listener);
        vista.btnCRUDobras.addActionListener(listener);
        vista.btnCRUDreserva.addActionListener(listener);
        vista.btnCRUDteatro.addActionListener(listener);
        // Vista obra
        vistaObra.btnConsultar.addActionListener(listener);
        vistaObra.btnEliminar.addActionListener(listener);
        vistaObra.btnInsertar.addActionListener(listener);
        vistaObra.btnModificar.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        switch (comando) {
            case "CRUD Reserva":
                vistaReserva.setVisible(true);
                break;
            case "CRUD Obras":
                vistaObra.setVisible(true);
                break;
            case "Consultar Obra":
                modelo.obraDAO.consultar();
                for (Obra obra : modelo.obraDAO.listaobra.getObras()) {
                    int idObra = Integer.parseInt(vistaObra.cboIDsObras.getSelectedItem().toString());
                    if (obra.getIdObra() == idObra) {
                        vistaObra.txtAreaObras.setText(obra.toString());
                        break;
                    }
                }
                break;
            case "Eliminar Obra":
                modelo.obraDAO.consultar();
                for (Obra obra : modelo.obraDAO.listaobra.getObras()) {
                    int idObra = Integer.parseInt(vistaObra.cboIDsObras.getSelectedItem().toString());
                    if (obra.getIdObra() == idObra) {
                        int filas = modelo.obraDAO.borrar(idObra);
                        JOptionPane.showMessageDialog(null, "Filas borradas: " + filas);
                        break;
                    }
                }
                break;
            case "Insertar Obra":
                String nombreObra = vistaObra.txtNombre.getText();
                String descripcionObra = vistaObra.txtDescripcion.getText();
                String generoObra = vistaObra.txtGenero.getText();
                if (nombreObra.length() != 0 && descripcionObra.length() != 0 && generoObra.length() != 0) {
                    int filas = modelo.obraDAO.insertar(new Obra(nombreObra, descripcionObra, generoObra));
                    JOptionPane.showMessageDialog(null, "Filas insertadas: " + filas);
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "Modificar Obra":
                int idObra = Integer.parseInt(vistaObra.cboIDsObras.getSelectedItem().toString());
                nombreObra = vistaObra.txtNombre.getText();
                descripcionObra = vistaObra.txtDescripcion.getText();
                generoObra = vistaObra.txtGenero.getText();
                if (nombreObra.length() != 0 && descripcionObra.length() != 0 && generoObra.length() != 0) {
                    int filas = modelo.obraDAO.actualizar(new Obra(idObra, nombreObra, descripcionObra, generoObra));
                    JOptionPane.showMessageDialog(null, "Filas insertadas: " + filas);
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "CRUD Teatro":

                break;
            case "CRUD Cliente":

                break;

        }
    }

    public void cargarIdObra() {
        modelo.obraDAO.consultar();
        vistaObra.cboIDsObras.removeAllItems();
        for (Obra obra : modelo.obraDAO.listaobra.getObras()) {
            vistaObra.cboIDsObras.addItem(obra.getIdObra() + "");
        }

    }
}
