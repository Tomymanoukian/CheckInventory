package com.example.checkinventory.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.Articulo;
import com.example.checkinventory.modelo.DatabaseHandler;


public class DeshacerCambiosBusquedaManualActivity extends BusquedaManualActivity{

    @Override
    public void botonAceptar(View view){
        Articulo articulo = databaseHandler.deschequearArticulo(modelo, talle);
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));

        finish();
    }
}
