package com.example.checkinventory.modelo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.room.Room;

import com.example.checkinventory.Activity.ErrorActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class GestionadorDeArchivos {

    private ArticuloDao articuloDao;

    public static void guardarNuevoArchivo(Uri uriArchivoAGuardar, Context context) throws IOException, FileAlreadyExistsException {
        
        InputStream archivoParaGuardar = context.getContentResolver().openInputStream(uriArchivoAGuardar);
        File archivoCopiado = new File(context.getFilesDir(), (new File(uriArchivoAGuardar.getPath())).getName());

        Files.copy(archivoParaGuardar, Paths.get(archivoCopiado.toURI()));

        ArticulosDatabase db = Room.databaseBuilder(MyApplication.getAppContext(),
                ArticulosDatabase.class, archivoCopiado.getName()).build();

        ArticuloDao articuloDao = db.articuloDao();
    }

    public static File[] listaDeArchivos(Context context){
        return context.getFilesDir().listFiles();
    }

    public static List<String> listaNombresDeArchivos(Context context){
        return Arrays.asList(context.fileList());
    }

    public static void borrarTodosLosArchivos(Context context){

        for(File file : context.getFilesDir().listFiles()){
            file.delete();
        }
    }

    private void crearDatabase(String nombreDB){

    }
}
