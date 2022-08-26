package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.checkinventory.R;

public class SeleccionDeTalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_de_talle);

        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};


        Spinner spinner = (Spinner) findViewById(R.id.spinnerTalles);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        // Apply the adapter to the spinner
        spinner.setAdapter(spinnerArrayAdapter);
    }
}