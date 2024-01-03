package com.example.checkinventory.modelo;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ArticuloViewModel extends AndroidViewModel {

    private final ArticulosRepository articulosRepository;

    private final LiveData<List<Articulo>> mShoppingLists;

    public ArticuloViewModel(@NonNull Application application) {
        super(application);
        articulosRepository = new ArticulosRepository(application);
        mShoppingLists = articulosRepository.getAllArticulos();
    }

    public LiveData<List<Articulo>> getShoppingLists() {
        return mShoppingLists;
    }

    public void insert(Articulo shoppingList) {
        articulosRepository.insert(shoppingList);
    }
}
