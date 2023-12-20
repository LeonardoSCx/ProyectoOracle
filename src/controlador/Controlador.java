package controlador;

import interfaz.UI_Cliente;
import interfaz.UI_Obra;

import interfaz.UI_Reserva;
import interfaz.UI_Teatro;
import modelo.DAO.AccesoDAO;
import interfaz.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Clases.Cliente;
import modelo.Clases.Obra;
import modelo.Clases.Reserva;
import modelo.Clases.Teatro;

public class Controlador implements ActionListener {

    AccesoDAO modelo;

    Vista vista;

    UI_Obra vistaObra;

    UI_Cliente vistaCliente;

    UI_Teatro vistaTeatro;

    UI_Reserva vistaReserva;

    public Controlador(AccesoDAO modelo, Vista vista, UI_Obra vistaObra, UI_Cliente vistaCliente, UI_Teatro vistaTeatro, UI_Reserva vistaReserva) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaObra = vistaObra;
        this.vistaCliente = vistaCliente;
        this.vistaTeatro = vistaTeatro;
        this.vistaReserva = vistaReserva;
        agregarListener(this);
        cargarIdObra();
        cargarIdTeatro();
        cargarIdCliente();
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
        // Vista Cliente
        vistaCliente.btnConsultarCliente.addActionListener(listener);
        vistaCliente.btnEliminarCliente.addActionListener(listener);
        vistaCliente.btnInsertarCliente.addActionListener(listener);
        vistaCliente.btnModificarCliente.addActionListener(listener);
        // Vista Teatro
        vistaTeatro.btnConsultarTeatro.addActionListener(listener);
        vistaTeatro.btnEliminarTeatro.addActionListener(listener);
        vistaTeatro.btnInsertarTeatro.addActionListener(listener);
        vistaTeatro.btnModificarTeatro.addActionListener(listener);
        // Vista Reserva
        vistaReserva.btnConsultar.addActionListener(listener);
        vistaReserva.btnEliminar.addActionListener(listener);
        vistaReserva.btnInsertar.addActionListener(listener);
        vistaReserva.btnModificar.addActionListener(listener);
        // Por lo menos un combo box para el precio
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        switch (comando) {
            case "CRUD Reserva":
                vistaReserva.setVisible(true);
                break;
            case "Consultar Reserva":

                break;
            case "Eliminar Reserva":

                break;
            case "Insertar Reserva":

                break;
            case "Modificar Reserva":

                break;
            case "CRUD Obras":
                vistaObra.setVisible(true);
                break;
            case "Consultar Obra":
                int idObra = Integer.parseInt(vistaObra.cboIDsObras.getSelectedItem().toString());
                for (Obra obra : modelo.obraDAO.listaobra.getObras()) {
                    if (obra.getIdObra() == idObra) {
                        vistaObra.txtAreaObras.setText(obra.toString());
                        break;
                    }
                }
                break;
            case "Eliminar Obra":
                idObra = Integer.parseInt(vistaObra.cboIDsObras.getSelectedItem().toString());
                int filas = modelo.obraDAO.borrar(idObra);
                JOptionPane.showMessageDialog(null, "Filas borradas: " + filas);
                cargarIdObra();
                break;
            case "Insertar Obra":
                String nombreObra = vistaObra.txtNombre.getText();
                String descripcionObra = vistaObra.txtDescripcion.getText();
                String generoObra = vistaObra.txtGenero.getText();
                if (nombreObra.length() != 0 && descripcionObra.length() != 0 && generoObra.length() != 0) {
                    filas = modelo.obraDAO.insertar(new Obra(nombreObra, descripcionObra, generoObra));
                    JOptionPane.showMessageDialog(null, "Filas insertadas: " + filas);
                    cargarIdObra();
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "Modificar Obra":
                idObra = Integer.parseInt(vistaObra.cboIDsObras.getSelectedItem().toString());
                nombreObra = vistaObra.txtNombre.getText();
                descripcionObra = vistaObra.txtDescripcion.getText();
                generoObra = vistaObra.txtGenero.getText();
                if (nombreObra.length() != 0 && descripcionObra.length() != 0 && generoObra.length() != 0) {
                    filas = modelo.obraDAO.actualizar(new Obra(idObra, nombreObra, descripcionObra, generoObra));
                    JOptionPane.showMessageDialog(null, "Filas Actualizadas: " + filas);
                    cargarIdObra();
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "CRUD Teatro":
                vistaTeatro.setVisible(true);
                break;
            case "Consultar Teatro":
                int idTeatro = Integer.parseInt(vistaTeatro.cboTeatroId.getSelectedItem().toString());
                for (Teatro teatro : modelo.teatroDAO.listaTeatro.getTeatros()) {
                    if (idTeatro == teatro.getIdteatro()) {
                        vistaTeatro.txtaInfoTeatro.setText(teatro.toString());
                        break;
                    }
                }
                break;
            case "Eliminar Teatro":
                idTeatro = Integer.parseInt(vistaTeatro.cboTeatroId.getSelectedItem().toString());
                filas = modelo.teatroDAO.borrar(idTeatro);
                JOptionPane.showMessageDialog(null, "Filas borradas: " + filas);
                cargarIdTeatro();
                break;
            case "Insertar Teatro":
                String nombreTeatro = vistaTeatro.txtNombreTeatro.getText();
                String direccionTeatro = vistaTeatro.txtDireccionTeatro.getText();
                if (nombreTeatro.length() != 0 && direccionTeatro.length() != 0) {
                    filas = modelo.teatroDAO.insertar(new Teatro(nombreTeatro, direccionTeatro));
                    JOptionPane.showMessageDialog(null, "Filas insertadas: " + filas);
                    cargarIdTeatro();
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "Modificar Teatro":
                idTeatro = Integer.parseInt(vistaTeatro.cboTeatroId.getSelectedItem().toString());
                nombreTeatro = vistaTeatro.txtNombreTeatro.getText();
                direccionTeatro = vistaTeatro.txtDireccionTeatro.getText();
                if (nombreTeatro.length() != 0 && direccionTeatro.length() != 0) {
                    filas = modelo.teatroDAO.actualizar(new Teatro(idTeatro, nombreTeatro, direccionTeatro));
                    JOptionPane.showMessageDialog(null, "Filas Actualizadas: " + filas);
                    cargarIdTeatro();
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "CRUD Cliente":
                vistaCliente.setVisible(true);
                break;
            case "Consultar Cliente":
                String dni = vistaCliente.cboCliente.getSelectedItem().toString();
                for (Cliente cliente : modelo.clienteDAO.listaClientes.getClientes()) {
                    if (dni.equals(cliente.getDni())) {
                        vistaCliente.txtaInfoCliente.setText(cliente.toString());
                    }
                }
                break;
            case "Eliminar Cliente":
                modelo.clienteDAO.consultar();
                dni = vistaCliente.cboCliente.getSelectedItem().toString();
                filas = modelo.clienteDAO.borrar(dni);
                JOptionPane.showMessageDialog(null, "Filas borradas: " + filas);
                cargarIdCliente();
                break;
            case "Insertar Cliente":
                dni = vistaCliente.txtDniCliente.getText();
                if (dni.length() == 0 || dni.length() != 9) {
                    JOptionPane.showMessageDialog(null, "El formato del dni no es correcto");
                    break;
                }
                String nombre = vistaCliente.txtNombreCliente.getText();
                int numero = Integer.parseInt(vistaCliente.txtTelefonoCliente.getText());
                String direccion = vistaCliente.txtDireccionCliente.getText();
                String correo = vistaCliente.txtCorreoCliente.getText();
                if (nombre.length() != 0 && direccion.length() != 0 && correo.length() != 0) {
                    filas = modelo.clienteDAO.insertar(new Cliente(dni, nombre, numero, direccion, correo));
                    JOptionPane.showMessageDialog(null, "Filas insertadas: " + filas);
                    cargarIdCliente();
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
                break;
            case "Modificar Cliente":
                dni = vistaCliente.cboCliente.getSelectedItem().toString();
                if (dni.length() == 0 || dni.length() != 9) {
                    JOptionPane.showMessageDialog(null, "El formato del dni no es correcto");
                    break;
                }
                nombre = vistaCliente.txtNombreCliente.getText();
                numero = Integer.parseInt(vistaCliente.txtTelefonoCliente.getText());
                direccion = vistaCliente.txtDireccionCliente.getText();
                correo = vistaCliente.txtCorreoCliente.getText();
                if (nombre.length() != 0 && direccion.length() != 0 && correo.length() != 0) {
                    filas = modelo.clienteDAO.actualizar(new Cliente(dni, nombre, numero, direccion, correo));
                    JOptionPane.showMessageDialog(null, "Filas insertadas: " + filas);
                    cargarIdCliente();
                } else {
                    JOptionPane.showMessageDialog(null, "Rellena todos los campos");
                }
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

    public void cargarIdTeatro() {
        modelo.teatroDAO.consultar();
        vistaTeatro.cboTeatroId.removeAllItems();
        for (Teatro teatro : modelo.teatroDAO.listaTeatro.getTeatros()) {
            vistaTeatro.cboTeatroId.addItem(teatro.getIdteatro() + "");
        }
    }

    public void cargarIdCliente() {
        modelo.clienteDAO.consultar();
        vistaCliente.cboCliente.removeAllItems();
        for (Cliente cliente : modelo.clienteDAO.listaClientes.getClientes()) {
            vistaCliente.cboCliente.addItem(cliente.getDni());
        }
    }
    public void cargarReservas(){
        modelo.reservaDAO.consultar();
        vistaReserva.cboIDsReserva.removeAllItems();
        vistaReserva.cbCliente.removeAllItems();
        vistaReserva.cbTeatro.removeAllItems();
        vistaReserva.cbObra.removeAllItems();
        for (Reserva reserva : modelo.reservaDAO.listaReservas.getReservas()) {
            vistaReserva.cboIDsReserva.addItem(reserva.getIdReserva()+"");
        vistaReserva.cbCliente.addItem(reserva.getIdcliente());
        vistaReserva.cbTeatro.addItem(reserva.getIdteatro()+"");
        vistaReserva.cbObra.addItem(reserva.getIdObra()+"");
        }
       
    }
    public void cargarPrecio(){
        
    }
}
