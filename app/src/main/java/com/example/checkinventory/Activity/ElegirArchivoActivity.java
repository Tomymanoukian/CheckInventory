package com.example.checkinventory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.checkinventory.R;
import com.example.checkinventory.modelo.GestionadorDeArchivos;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ElegirArchivoActivity extends AppCompatActivity {

    // Request code for selecting a PDF document.
    private static final int PICK_EXCEL_FILE = 2;

    private void openFile(/*Uri pickerInitialUri*/) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Optionally, specify a URI for the file that should appear in the
        // system file picker when it loads.
        //intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

        startActivityForResult(intent, PICK_EXCEL_FILE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_archivo);
        openFile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        //InputStream archivoParaCopiar = null;
        //File archivoCopiado = new File(this.getFilesDir(), (new File(resultData.getData().getPath())).getName());

        /*try {
            archivoParaCopiar = this.getContentResolver().openInputStream(resultData.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }*/

        if (requestCode == PICK_EXCEL_FILE
                && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.

            /*try {
                Files.copy(archivoParaCopiar, Paths.get(archivoCopiado.toURI()));

            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
                Toast.makeText(this, "El archivo ya existe", Toast.LENGTH_LONG).show();
                finish();
            }
            catch (IOException e) {
                e.printStackTrace();

                finish();
                Intent intent = new Intent(this, ErrorActivity.class);
                startActivity(intent);
            }*/


            try {
                GestionadorDeArchivos.guardarNuevoArchivo(resultData.getData(), this);
            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
                Toast.makeText(this, "El archivo ya existe", Toast.LENGTH_LONG).show();
                finish();
            }
            catch (IOException e) {
                e.printStackTrace();
                finish();
                Intent intent = new Intent(this, ErrorActivity.class);
                startActivity(intent);
            }
            catch (InvalidFormatException e) {
                e.printStackTrace();
                Toast.makeText(this, "Archivo  no guardado: El archivo debe ser .xlsx", Toast.LENGTH_LONG).show();
            }

            //BORRAR
            /*List<String> archivos  = Arrays.asList(this.fileList());
            Iterator<String> iterator = archivos.iterator();
            //Toast.makeText(this,"Hay archivos en el la memoria = " + iterator.hasNext(), Toast.LENGTH_LONG).show();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }*/
            //BORRAR

            Toast.makeText(this,"Archivo guardado correctamente", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}