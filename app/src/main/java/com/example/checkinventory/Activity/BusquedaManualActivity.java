package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.Articulo;
import com.example.checkinventory.modelo.DatabaseHandler;
import com.example.checkinventory.modelo.ListaDeArticulos;

import java.util.List;

public class BusquedaManualActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String archivoDeBusqueda;
    private String modelo;
    private String talle;

    //ArticuloDao articuloDao;
    private DatabaseHandler databaseHandler;

    private EditText editText_Modelo;

    private TextView textView_modelo;
    private TextView textView_marca;
    private TextView textView_descripcion;
    private TextView textView_Stock;
    private TextView textView_CantidadRevisada;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_manual);

        archivoDeBusqueda = getIntent().getStringExtra("archivoDeBusqueda");
        databaseHandler = new DatabaseHandler(archivoDeBusqueda);

        editText_Modelo = (EditText) findViewById(R.id.editText_Modelo);

        textView_modelo = findViewById(R.id.textView_BusquedaManual_ResultadoModelo);
        textView_marca = findViewById(R.id.textView_BusquedaManual_ResultadoMarca);
        textView_descripcion = findViewById(R.id.textView_BusquedaManual_ResultadoDescripcion);
        textView_Stock = findViewById(R.id.textView_BusquedaManual_ResultadoStock);
        textView_CantidadRevisada = findViewById(R.id.textView_BusquedaManual_ResultadoCantidad);

        spinner = (Spinner) findViewById(R.id.spinner_BusquedaManual_Talles);
        // Create an ArrayAdapter using the string array and a default spinner layout

        getIntent();

    }

    public void buscarArticulo(View view){

        ListaDeArticulos articulos = databaseHandler.busquedaPorModelo(editText_Modelo.getText().toString());
        List<String> talles = articulos.getTalles();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, talles);
        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerArrayAdapter);

        textView_marca.setText(articulos.getMarca());
        textView_modelo.setText(articulos.getModelo());
        textView_descripcion.setText(articulos.getDescripcion());

        modelo = articulos.getModelo();

        spinner.setOnItemSelectedListener(this);

    }

    //Funciones para el manejo del spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        talle = parent.getItemAtPosition(pos).toString();

        Articulo articulo = databaseHandler.busquedaPorModeloYTalle(modelo, talle);

        textView_Stock.setText(String.valueOf(articulo.stockOriginal) );
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }

    public void checkearArticulo(View view){

        Articulo articulo = databaseHandler.chequearArticulo(modelo, talle);
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));

        finish();
    }

    public void cerrarActivity(View view){ finish(); }
}