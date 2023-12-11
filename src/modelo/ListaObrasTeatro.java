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
public class ListaObrasTeatro {
    private List<ObrasTeatro> obrasTeatro;

    public ListaObrasTeatro() {
        this.obrasTeatro = new ArrayList<>();
    }

    public List<ObrasTeatro> getObrasTeatro() {
        return obrasTeatro;
    }

    public void agregarObrasTeatro(ObrasTeatro obrasTeatroItem) {
        obrasTeatro.add(obrasTeatroItem);
    }
}