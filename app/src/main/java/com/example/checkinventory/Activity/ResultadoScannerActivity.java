package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.checkinventory.R;

public class ResultadoScannerActivity extends AppCompatActivity {

    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_scanner);

        resultado = (TextView) findViewById(R.id.resultado);
        resultado.setText(getIntent().getStringExtra("resultadoScanner"));

    }

    public void VolverAEscanear(View view){
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }
}