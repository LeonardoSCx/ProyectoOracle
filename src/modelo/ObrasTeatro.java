/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Diurno
 */
public class ObrasTeatro {
    private int idTeatro;
    private int idObra;
    private float precio;

    public ObrasTeatro(int idTeatro, int idObra, float precio) {
        this.idTeatro = idTeatro;
        this.idObra = idObra;
        this.precio = precio;
    }

    public int getIdTeatro() {
        return idTeatro;
    }

    public void setIdTeatro(int idTeatro) {
        this.idTeatro = idTeatro;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}