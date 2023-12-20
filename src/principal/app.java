package principal;


import controlador.Controlador;
import interfaz.CrearCliente;
import interfaz.CrearTeatro;
import interfaz.UI_Obra;
import interfaz.UI_Reserva;
import interfaz.Vista;
import modelo.AccesoDAO;
import modelo.TeatroDao;

public class app {

    public static void main(String[] args) {

        TeatroDao modeloTeatro= new TeatroDao();
        interfaz.Vista view= new Vista();
        interfaz.UI_Obra viewObras = new UI_Obra();
        interfaz.UI_Reserva viewReserva = new UI_Reserva();
        interfaz.CrearCliente viewCliente= new CrearCliente();
        interfaz.CrearTeatro viewTeatro = new CrearTeatro();
        
        Controlador con = new Controlador(modeloTeatro, view, viewObras, viewReserva, viewCliente, viewTeatro);
    }
}
