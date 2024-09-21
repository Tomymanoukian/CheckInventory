package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkinventory.R;
import com.example.checkinventory.excepciones.BusquedaInfrucuosaException;
import com.example.checkinventory.modelo.Articulo;
import com.example.checkinventory.modelo.ArticuloDao;
import com.example.checkinventory.modelo.ArticulosDatabase;
import com.example.checkinventory.modelo.DatabaseHandler;
import com.example.checkinventory.modelo.ListaDeArticulos;
import com.example.checkinventory.modelo.MyApplication;

import java.util.List;

public class ResultadoScannerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String archivoDeBusqueda;
    String resultadoScanner;
    String modelo;
    String talle;

    DatabaseHandler databaseHandler;

    protected TextView textView_modelo;
    protected TextView textView_marca;
    protected TextView textView_descripcion;
    protected TextView textView_Stock;
    protected TextView textView_CantidadRevisada;
    protected TextView textView_Talle;

    protected Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_scanner);

        textView_modelo = findViewById(R.id.textView_Scanner_ResultadoModelo);
        textView_marca = findViewById(R.id.textView_Scanner_ResultadoMarca);
        textView_descripcion = findViewById(R.id.textView_Scanner_ResultadoDescripcion);
        textView_Stock = findViewById(R.id.textView_Scanner_ResultadoStock);
        textView_CantidadRevisada = findViewById(R.id.textView_Scanner_ResultadoCantidad);
        textView_Talle = findViewById(R.id.textView_Scanner_ResultadoTalle);

        spinner = (Spinner) findViewById(R.id.spinner_Scanner_Talles);

        textView_Talle.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);

        archivoDeBusqueda = getIntent().getStringExtra("archivoDeBusqueda");

        databaseHandler = new DatabaseHandler(archivoDeBusqueda);

        resultadoScanner = getIntent().getStringExtra("resultadoScanner");
        resultadoScanner  = "1806051210414634";

        if(esIDC(resultadoScanner)){
            busquedaPorIdc(resultadoScanner);
        }
        else{
            busquedaPorModelo(resultadoScanner);
        }

        getIntent();
    }

    public void VolverAEscanear(View view){
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }

    protected boolean esIDC(String string){
        try {
            Long.parseLong(string);
        }
        catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    protected void busquedaPorIdc(String idc){
        Articulo articulo = null;
        try {
            articulo = databaseHandler.busquedaPorIDC(idc);
        }
        catch (BusquedaInfrucuosaException e){
            finish();
            emitirMsjDeArticuloNoEncontrado();
            return;
        }

        modelo = articulo.modelo;
        talle = articulo.talle;

        textView_Talle.setVisibility(View.VISIBLE);

        textView_marca.setText(articulo.marca);
        textView_modelo.setText(articulo.modelo);
        textView_descripcion.setText(articulo.descripcion);
        textView_Talle.setText(articulo.talle);
        textView_Stock.setText(String.valueOf(articulo.stockOriginal));
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));
    }

    protected void busquedaPorModelo(String modeloABuscar){
        ListaDeArticulos articulos = null;
        try {
            articulos = databaseHandler.busquedaPorModelo(modeloABuscar);
        }
        catch (BusquedaInfrucuosaException e){
            finish();
            emitirMsjDeArticuloNoEncontrado();
            return;
        }

        List<String> talles = articulos.getTalles();

        modelo = modeloABuscar;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, talles);
        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setVisibility(View.VISIBLE);

        textView_marca.setText(articulos.getMarca());
        textView_modelo.setText(articulos.getModelo());
        textView_descripcion.setText(articulos.getDescripcion());



    }

    //Funciones para el manejo del spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        talle = parent.getItemAtPosition(pos).toString();

        Articulo articulo = databaseHandler.busquedaPorModeloYTalle(modelo, talle);

        textView_Stock.setText(String.valueOf(articulo.stockOriginal));
        textView_CantidadRevisada.setText(String.valueOf(articulo.stockChequeado));
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

    protected void botonCancelar(View view){ finish(); }
}