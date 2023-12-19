/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import interfaz.UI_Obra;

import interfaz.UI_Reserva;
import modelo.DAO.AccesoDAO;
import interfaz.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Clases.Obra;

/**
 *
 * @author leogu
 */
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
