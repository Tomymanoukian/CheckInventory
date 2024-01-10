package com.example.checkinventory.modelo;

import com.example.checkinventory.excepciones.DiferentesArticulosEnUnaMismaBusquedaException;
import com.example.checkinventory.excepciones.VariosArticulosEnLaListaException;

import java.util.ArrayList;
import java.util.List;

public class ListaDeArticulos {

    private List<Articulo> lista;

    public ListaDeArticulos(List<Articulo> listaDeArticulos){
        this.lista = new ArrayList<>(listaDeArticulos);
    }

    public List<String> getTalles(){
        List<String> talles = new ArrayList<>();

        for(Articulo articulo: lista){
            talles.add(articulo.talle);
        }
        return talles;
    }

    public String getModelo(){
        String modeloAnterior = lista.get(0).modelo;

        for(Articulo articulo: lista){
            if(!articulo.modelo.equals(modeloAnterior)) throw new DiferentesArticulosEnUnaMismaBusquedaException();
        }
        return modeloAnterior;
    }

    public String getMarca(){
        String marcaAnterior = lista.get(0).marca;

        for(Articulo articulo: lista){
            if(!articulo.marca.equals(marcaAnterior)) throw new DiferentesArticulosEnUnaMismaBusquedaException();
        }
        return marcaAnterior;
    }

    public String getDescripcion(){
        String descripcionAnterior = lista.get(0).descripcion;

        for(Articulo articulo: lista){
            if(!articulo.descripcion.equals(descripcionAnterior)) throw new DiferentesArticulosEnUnaMismaBusquedaException();
        }
        return descripcionAnterior;
    }

    public Articulo getUnicoArticulo(){
        if(lista.size() > 1) throw new VariosArticulosEnLaListaException();
        return lista.get(0);
    }
}
