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
public class ListaObras {
    private List<Obra> obras;

    public ListaObras() {
        this.obras = new ArrayList<>();
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void agregarObra(Obra obra) {
        obras.add(obra);
    }
}