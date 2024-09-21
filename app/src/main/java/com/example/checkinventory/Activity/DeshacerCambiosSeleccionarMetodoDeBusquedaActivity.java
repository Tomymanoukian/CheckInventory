package com.example.checkinventory.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.GestionadorDeArchivos;

import java.util.List;

public class DeshacerCambiosSeleccionarMetodoDeBusquedaActivity extends SeleccionarMetodoDeBusquedaActivity{

    @Override
    public void escanearCodidoDeBarras(View view){
        Intent intent = new Intent(this, DeshacerCambiosScannerActivity.class);
        intent.putExtra("archivoDeBusqueda", archivoDeBusqueda);
        startActivity(intent);
    }

    @Override
    public void ingresarCodigoManualmente(View view){
        Intent intent = new Intent(this, DeshacerCambiosBusquedaManualActivity.class);
        intent.putExtra("archivoDeBusqueda", archivoDeBusqueda);
        startActivity(intent);
    }
}
