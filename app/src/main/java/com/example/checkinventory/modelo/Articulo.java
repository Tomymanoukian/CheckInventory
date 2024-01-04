package com.example.checkinventory.modelo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Articulo {

    @PrimaryKey
    @NonNull
    public String idc;

    public String marca;
    public String modelo;
    public String descripcion;
    public int stockOriginal;
    public int stockChequeado;
    public String talle;

    public Articulo(String idc,
                    String marca,
                    String modelo,
                    String descripcion,
                    int stockOriginal,
                    String talle){

        this.idc = idc;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.stockOriginal = stockOriginal;
        this.stockChequeado = 0;
        this.talle = talle;

    }

    //@ColumnInfo(name = IDC) ESTE ES EL FORMATO PARA UTILIZAR OTRO NOMBRE DE COLUMNA
    //public String idc;
}
