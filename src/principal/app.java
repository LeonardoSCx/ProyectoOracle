package principal;

import java.sql.*;
import modelo.AccesoDAO;
public class app {
    public static void main(String[] args) {
        AccesoDAO modelo = new AccesoDAO();
        modelo.queryTable("teatro");
    }
}
