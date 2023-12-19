/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.Interfaz;

import modelo.ConexionOracle;
import java.sql.*;
/**
 *
 * @author leogu
 */
public interface CRUD {
    public ConexionOracle conexion = new ConexionOracle();
    public Connection sesion = conexion.conectar();
    public void consultar();
    public int insertar(Object o);
    public int actualizar(Object o);
    public int borrar(Object o);
}
