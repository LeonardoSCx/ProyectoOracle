/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Clases;

/**
 *
 * @author Diurno
 */
public class Teatro {
    private int idteatro;
    private String nombre;
    private String direccion;

    public Teatro(int idteatro, String nombre, String direccion) {
        this.idteatro = idteatro;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Teatro(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    
    

    public int getIdteatro() {
        return idteatro;
    }

    public void setIdteatro(int idteatro) {
        this.idteatro = idteatro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
