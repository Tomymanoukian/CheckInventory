package com.example.checkinventory.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.GestionadorDeArchivos;

import java.util.List;

import me.dm7.barcodescanner.zbar.Result;

public class SeleccionarMetodoDeBusquedaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected String archivoDeBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_metodo_de_busqueda);

        List<String> archivos = GestionadorDeArchivos.listaNombresDeArchivos(this);
        getIntent();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerArchivos);
        // Create an ArrayAdapter using the string array and a default spinner layout

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, archivos);
        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerArrayAdapter);


    }

    //Funciones para el manejo del spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        archivoDeBusqueda = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }

    public void escanearCodidoDeBarras(View view){
        Intent intent = new Intent(this, ScannerActivity.class);
        intent.putExtra("archivoDeBusqueda", archivoDeBusqueda);
        startActivity(intent);
    }

    public void ingresarCodigoManualmente(View view){
        Intent intent = new Intent(this, BusquedaManualActivity.class);
        intent.putExtra("archivoDeBusqueda", archivoDeBusqueda);
        startActivity(intent);
    }

}
