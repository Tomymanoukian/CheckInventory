package com.example.checkinventory.modelo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ArticulosRepository {

    private final LiveData<List<Articulo>> listaArticulos;
    private final ArticuloDao articulosDao;

    public ArticulosRepository(Context context) {
        ArticulosDatabase db = ArticulosDatabase.getInstance(context);
        articulosDao = db.articuloDao();
        listaArticulos = articulosDao.getAll();
    }

    public LiveData<List<Articulo>> getAllArticulos() {
        return listaArticulos;
    }

    public void insert(Articulo articulo) {
        ArticulosDatabase.dbExecutor.execute(
                () -> articulosDao.insert(articulo)
        );
    }
}
