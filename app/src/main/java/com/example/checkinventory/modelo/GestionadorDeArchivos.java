package com.example.checkinventory.modelo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.room.Room;

import com.example.checkinventory.Activity.ErrorActivity;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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

    public static void guardarNuevoArchivo(Uri uriArchivoAGuardar, Context context) throws IOException, FileAlreadyExistsException, InvalidFormatException {
        
        InputStream archivoParaGuardar = context.getContentResolver().openInputStream(uriArchivoAGuardar);
        File archivoCopiado = new File(context.getFilesDir(), (new File(uriArchivoAGuardar.getPath())).getName());

        Files.copy(archivoParaGuardar, Paths.get(archivoCopiado.toURI()));

        crearDatabase(archivoCopiado.getName(), archivoCopiado);
    }

    public static File[] listaDeArchivos(Context context){
        return context.getFilesDir().listFiles();
    }

    public static List<String> listaNombresDeArchivos(Context context){
        return Arrays.asList(context.fileList());
    }

    public static void borrarTodosLosArchivos(Context context){

        ArticulosDatabase db;
        ArticuloDao articuloDao;

        for(File file : context.getFilesDir().listFiles()){
            file.delete();

            db = Room.databaseBuilder(MyApplication.getAppContext(),
                    ArticulosDatabase.class, file.getName()).allowMainThreadQueries().build();
            articuloDao = db.articuloDao();

            articuloDao.deleteAll();
        }
    }

    private static void crearDatabase(String nombreDB, File archivo) throws IOException, InvalidFormatException {

        ArticulosDatabase db = Room.databaseBuilder(MyApplication.getAppContext(),
                ArticulosDatabase.class, nombreDB).allowMainThreadQueries().build();

        ArticuloDao articuloDao = db.articuloDao();

        LectorDeArchivos lectorDeArchivos = new LectorDeArchivos(archivo);
        lectorDeArchivos.guardarArticulosEnDataBase(articuloDao);
    }
}
