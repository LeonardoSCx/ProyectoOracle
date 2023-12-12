package principal;

import controlador.Controller;
import interfaz.ActualizarDatos;
import interfaz.CrearReserva;
import interfaz.MostrarReservas;
import modelo.AccesoDAO;

public class app {
    
    public static void main(String[] args) {
        MostrarReservas vista = new MostrarReservas();
        CrearReserva viewReserva = new CrearReserva();
        ActualizarDatos viewActualizar = new ActualizarDatos();
        AccesoDAO modelo = new AccesoDAO();
        
        Controller controlador = new Controller(vista,viewReserva,viewActualizar, modelo);
        
    }
}
