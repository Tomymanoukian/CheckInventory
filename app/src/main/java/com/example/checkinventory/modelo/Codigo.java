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

    public String getTalle(){
        return talle;
    }
    public String getIdc(){
        return idc;
    }
    public String getModelo(){
        return modelo;
    }
    public String getDescripcion(){
        return descripcion;
    }


}
