package principal;

import controlador.Controller;
import interfaz.MostrarReservas;
import modelo.AccesoDAO;

public class app {
    
    public static void main(String[] args) {
        MostrarReservas vista = new MostrarReservas();
        AccesoDAO modelo = new AccesoDAO();
        Controller controlador = new Controller(vista, modelo);
        
    }
}
