package com.example.checkinventory.Activity;

import  android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScannerActivity extends Activity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;
    String archivoDeBusqueda;

    @Override
    public void onCreate(Bundle state) {

        archivoDeBusqueda = getIntent().getStringExtra("archivoDeBusqueda");

        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        //Toast.makeText(this, rawResult.getContents(), Toast.LENGTH_SHORT).show();
        //Log.v(TAG, rawResult.getContents()); // Prints scan results
        //Log.v(TAG, rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        Intent intent = new Intent(this, ResultadoScannerActivity.class);
        intent.putExtra("resultadoScanner", rawResult.getContents());
        intent.putExtra("archivoDeBusqueda", archivoDeBusqueda);
        startActivity(intent);
        finish();
    }
}