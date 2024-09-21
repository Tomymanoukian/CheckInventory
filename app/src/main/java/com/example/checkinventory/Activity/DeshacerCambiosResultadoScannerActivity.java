package com.example.checkinventory.Activity;

import android.view.View;

import com.example.checkinventory.modelo.Articulo;

public class DeshacerCambiosResultadoScannerActivity extends ResultadoScannerActivity{

    @Override
    public void botonAceptar(View view){
        Articulo articulo = databaseHandler.deschequearArticulo(modelo, talle);
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));

        finish();
    }
}
