package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkinventory.R;
import com.example.checkinventory.excepciones.BusquedaInfrucuosaException;
import com.example.checkinventory.modelo.Articulo;
import com.example.checkinventory.modelo.DatabaseHandler;
import com.example.checkinventory.modelo.ListaDeArticulos;

import java.util.ArrayList;
import java.util.List;

public class BusquedaManualActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected String archivoDeBusqueda;
    protected String modelo;
    protected String talle;

    protected DatabaseHandler databaseHandler;

    protected EditText editText_Modelo;

    protected TextView textView_modelo;
    protected TextView textView_marca;
    protected TextView textView_descripcion;
    protected TextView textView_Stock;
    protected TextView textView_CantidadRevisada;

    protected Spinner spinner;

    protected Button botonAceptar;


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

        botonAceptar = findViewById(R.id.button_BusquedaManual_aceptar);
        botonAceptar.setVisibility(View.GONE);

        getIntent();

    }

    public void buscarArticulo(View view){
        ListaDeArticulos articulos = null;
        try {
            articulos = databaseHandler.busquedaPorModelo(editText_Modelo.getText().toString());
        }
        catch (BusquedaInfrucuosaException e){
            borrarResultadoAnterior();
            emitirMsjDeArticuloNoEncontrado();
            return;
        }

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

        botonAceptar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }

    public void botonAceptar(View view){

        Articulo articulo = databaseHandler.chequearArticulo(modelo, talle);
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));

        finish();
    }

    protected void emitirMsjDeArticuloNoEncontrado(){
        Toast.makeText(this, "Articulo no encotrado", Toast.LENGTH_LONG).show();
    }

    protected void borrarResultadoAnterior(){
        textView_marca.setText("");
        textView_modelo.setText("");
        textView_descripcion.setText("");
        textView_Stock.setText("");
        textView_CantidadRevisada.setText("");

        List<String> talles = new ArrayList<>();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, talles);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        botonAceptar.setVisibility(View.GONE);
    }

    public void botonCerrar(View view){ finish(); }
}