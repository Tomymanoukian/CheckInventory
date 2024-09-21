package com.example.checkinventory.Activity;

import android.content.Intent;

import me.dm7.barcodescanner.zbar.Result;

public class DeshacerCambiosScannerActivity extends ScannerActivity{

    @Override
    public void handleResult(Result rawResult) {

        Intent intent = new Intent(this, DeshacerCambiosResultadoScannerActivity.class);
        intent.putExtra("resultadoScanner", rawResult.getContents());
        intent.putExtra("archivoDeBusqueda", archivoDeBusqueda);
        startActivity(intent);
        finish();
    }
}
