/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Clases;

import java.util.Date;

/**
 *
 * @author Diurno
 */
public class Historico {
    private int idHistorico;
    private String reservado;
    private Date fecha;
    private int idReserva;

    public Historico(int idHistorico, String reservado, Date fecha, int idReserva) {
        this.idHistorico = idHistorico;
        this.reservado = reservado;
        this.fecha = fecha;
        this.idReserva = idReserva;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}