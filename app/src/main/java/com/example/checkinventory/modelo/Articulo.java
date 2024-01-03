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
    public String stockOriginal;
    public String stockChequeado;
    public String talle;


    //@ColumnInfo(name = IDC) ESTE ES EL FORMATO PARA UTILIZAR OTRO NOMBRE DE COLUMNA
    //public String idc;
}
