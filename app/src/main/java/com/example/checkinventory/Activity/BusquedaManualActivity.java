package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.Articulo;
import com.example.checkinventory.modelo.ArticuloDao;
import com.example.checkinventory.modelo.ArticulosDatabase;
import com.example.checkinventory.modelo.GestionadorDeArchivos;
import com.example.checkinventory.modelo.ListaDeArticulos;
import com.example.checkinventory.modelo.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class BusquedaManualActivity extends AppCompatActivity {

    String archivoDeBusqueda;
    ArticuloDao articuloDao;

    private TextView textView_modelo;
    private TextView textView_marca;
    private TextView textView_descripcion;
    private EditText editText_Modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_manual);

        archivoDeBusqueda = getIntent().getStringExtra("archivoDeBusqueda");

        editText_Modelo = (EditText) findViewById(R.id.editText_Modelo);
        textView_modelo = findViewById(R.id.textView_BusquedaManual_ResultadoModelo);
        textView_marca = findViewById(R.id.textView_BusquedaManual_ResultadoMarca);
        textView_descripcion = findViewById(R.id.textView_BusquedaManual_ResultadoDescripcion);


        List<String> talles = new ArrayList<>();

        getIntent();

        ArticulosDatabase db = Room.databaseBuilder(MyApplication.getAppContext(),
                ArticulosDatabase.class, archivoDeBusqueda).allowMainThreadQueries().build();

        articuloDao = db.articuloDao();

    }

    public void buscarArticulo(View view){
        List<Articulo> busqueda = articuloDao.busquedaPorModelo(editText_Modelo.getText().toString());
        ListaDeArticulos articulos = new ListaDeArticulos(busqueda);
        List<String> talles = articulos.getTalles();

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTalles);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, talles);
        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerArrayAdapter);

        textView_marca.setText(articulos.getMarca());
        textView_modelo.setText(articulos.getModelo());
        textView_descripcion.setText(articulos.getDescripcion());


    }
}