package principal;

import controlador.Controller;
import interfaz.CrearReserva;
import interfaz.MostrarReservas;
import modelo.AccesoDAO;

public class app {
    
    public static void main(String[] args) {
        MostrarReservas vista = new MostrarReservas();
        CrearReserva viewReserva = new CrearReserva();
        AccesoDAO modelo = new AccesoDAO();
        
        Controller controlador = new Controller(vista,viewReserva, modelo);
        
    }
}
