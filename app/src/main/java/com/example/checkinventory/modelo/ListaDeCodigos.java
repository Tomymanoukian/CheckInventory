package com.example.checkinventory.modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaDeCodigos {

    private List<Codigo> lista;

    public ListaDeCodigos(List<Codigo> lista){
        this.lista = lista;
    }

    public ArrayList<String> listaDeTalles(){

        ArrayList<String> array= new ArrayList<>();

        for(Codigo codigo: lista){
            array.add(codigo.getTalle());
        }
        return array;
    }
}
