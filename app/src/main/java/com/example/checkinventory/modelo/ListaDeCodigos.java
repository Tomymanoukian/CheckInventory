package com.example.checkinventory.modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaDeCodigos {

    private List<Codigo> lista;

    public ListaDeCodigos(){ this.lista = new ArrayList<>(); }

    public void agregarCodigo(Codigo codigo){
        lista.add(codigo);
    }

    public ArrayList<String> listaDeTalles(){

        ArrayList<String> array= new ArrayList<>();

        for(Codigo codigo: lista){
            array.add(codigo.getTalle());
        }
        return array;
    }
}
