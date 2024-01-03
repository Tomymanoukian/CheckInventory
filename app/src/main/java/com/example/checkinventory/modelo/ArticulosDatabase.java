package com.example.checkinventory.modelo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Articulo.class}, version = 1)

public abstract class ArticulosDatabase extends RoomDatabase {

    public abstract ArticuloDao articuloDao();

    private static final String DATABASE_NAME = "articulosDb";

    private static ArticulosDatabase INSTANCE;

    private static final int THREADS = 4;

    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);


    public static ArticulosDatabase getInstance(final Context context) { //¿ES NECESARIO EL PATRON SINGLETON?
        if (INSTANCE == null) {
            synchronized (ArticulosDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(), ArticulosDatabase.class,
                                    DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

