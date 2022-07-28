package com.example.checkinventory.modelo;

public class Codigo {

    private final String modelo;
    private final String talle;
    private final String idc;
    private final String descripcion;

    public Codigo(String idc, String modelo, String talle, String descripcion) {
        this.idc = idc;
        this.modelo = modelo;
        this.talle = talle;
        this.descripcion = descripcion;
    }

    public boolean esIgual(Codigo otroCodigo) {
        return modelo.equals(otroCodigo.modelo) && talle.equals(otroCodigo.talle) && idc.equals(otroCodigo.idc);
    }

    public void imprimir(){
        System.out.println("idc: " + idc);
        System.out.println("modelo: " + modelo);
        System.out.println("talle: " + talle);
        System.out.println("descripcion: " + descripcion);
    }
}
