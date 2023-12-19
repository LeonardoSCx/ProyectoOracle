/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Clases;

/**
 *
 * @author Diurno
 */
 
    public class Reserva {
    private int idReserva;
    private String idcliente;
    private int idObra;
    private int idteatro;
    private float precio;

    public Reserva(int idReserva, String idcliente, int idObra, int idteatro, float precio) {
        this.idReserva = idReserva;
        this.idcliente = idcliente;
        this.idObra = idObra;
        this.idteatro = idteatro;
        this.precio = precio;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdteatro() {
        return idteatro;
    }

    public void setIdteatro(int idteatro) {
        this.idteatro = idteatro;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

