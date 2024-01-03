package com.example.checkinventory.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.GestionadorDeArchivos;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static {
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
        );
        System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
        );
    }

    public void leerCodigo(View view){
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }

    public void abrirArchivo(View view){
        Intent intent = new Intent(this, ElegirArchivoActivity.class);
        startActivity(intent);
    }

    public void borrarArchivos(View view){
        GestionadorDeArchivos.borrarTodosLosArchivos(this);

        List<String> archivos  = Arrays.asList(this.fileList());
        Iterator<String> iterator = archivos.iterator();
        Toast.makeText(this,"Hay archivos en el la memoria = " + iterator.hasNext(), Toast.LENGTH_LONG).show();

    }

    public void prueba(View view){
        /*LectorDeArchivos lectorDeArchivos = null;
        try {
            lectorDeArchivos = new LectorDeArchivos(new File(this.getFilesDir(), "listadoarticulos.xlsx"));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        long idc = Long.parseLong("2007201111562393");
        lectorDeArchivos.buscarArticulo(idc).imprimir();*/
        Intent intent = new Intent(this, SeleccionDeTalleActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
