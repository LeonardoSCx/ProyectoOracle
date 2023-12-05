/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diurno
 */
public class ListaTeatros {
    private List<Teatro> teatros;

    public ListaTeatros() {
        this.teatros = new ArrayList<>();
    }

    public List<Teatro> getTeatros() {
        return teatros;
    }

    public void agregarTeatro(Teatro teatro) {
        teatros.add(teatro);
    }
}