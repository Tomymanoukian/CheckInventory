package com.example.checkinventory.modelo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticuloDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Articulo articulo);

    @Delete
    void delete(Articulo articulo);

    @Query("SELECT * FROM articulo")
    LiveData<List<Articulo>> getAll();

    @Query("SELECT * FROM Articulo WHERE idc IN (:ArticuloIds)")
    List<Articulo> loadAllByIds(int[] ArticuloIds);

    /*@Query("SELECT * FROM Articulo WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Articulo findByName(String first, String last);*/
}