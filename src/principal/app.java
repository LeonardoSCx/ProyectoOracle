package principal;


import controlador.Controlador;
import interfaz.UI_Cliente;
import interfaz.UI_Obra;
import interfaz.UI_Reserva;
import interfaz.UI_Teatro;
import interfaz.Vista;
import modelo.DAO.AccesoDAO;
import modelo.DAO.ClienteDAO;
import modelo.DAO.ObraDAO;
import modelo.DAO.ReservaDAO;
import modelo.DAO.TeatroDAO;

public class app {
    
    public static void main(String[] args) {
        AccesoDAO modelo = new AccesoDAO(new ClienteDAO(), new ObraDAO(), new TeatroDAO(), new ReservaDAO());
        Vista vista = new Vista();
        UI_Obra vistaObra = new UI_Obra();
        UI_Reserva vistaReserva = new UI_Reserva();
        UI_Teatro vistaTeatro = new UI_Teatro();
        UI_Cliente vistaCliente = new UI_Cliente();
        Controlador controlado = new Controlador(modelo, vista, vistaObra, vistaCliente, vistaTeatro, vistaReserva);
    }
}
