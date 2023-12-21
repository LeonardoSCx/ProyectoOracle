/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diurno
 */
public class ListaHistorico {
    private List<Historico> historico;

    public ListaHistorico() {
        this.historico = new ArrayList<>();
    }

    public List<Historico> getHistorico() {
        return historico;
    }

    public void agregarHistorico(Historico historicoItem) {
        historico.add(historicoItem);
    }
}