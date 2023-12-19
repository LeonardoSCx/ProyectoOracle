/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Clases;

/**
 *
 * @author Diurno
 */
public class Obra {
    private int idObra;
    private String nombre;
    private String descripcion;
    private String genero;

    public Obra(int idObra, String nombre, String descripcion, String genero) {
        this.idObra = idObra;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Obra{" + "idObra=" + idObra + ", nombre=" + nombre + ", descripcion=" + descripcion + ", genero=" + genero + '}';
    }
    
}
