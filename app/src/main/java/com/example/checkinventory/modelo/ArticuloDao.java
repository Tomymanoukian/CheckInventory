package com.example.checkinventory.modelo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArticuloDao {

    @Insert()
    void insert(Articulo articulo);

    @Delete
    void delete(Articulo articulo);

    @Query("DELETE FROM articulo")
    void deleteAll();

    @Query("SELECT * FROM Articulo WHERE IDC LIKE :idcABuscar")
    Articulo busquedaPorIDC(String idcABuscar);

    @Query("SELECT * FROM Articulo WHERE modelo LIKE :modeloABuscar")
    List<Articulo> busquedaPorModelo(String modeloABuscar);

    @Query("SELECT * FROM Articulo WHERE modelo LIKE :modeloABuscar AND talle LIKE :talleABuscar")
    Articulo busquedaPorModeloYTalle(String modeloABuscar, String talleABuscar);

    @Query("SELECT * FROM articulo")
    LiveData<List<Articulo>> getAll();

    @Update
    public void actualizarArticulo(Articulo articulo);

/*
    @Query("SELECT * FROM Articulo WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Articulo findByName(String first, String last);
*/
}