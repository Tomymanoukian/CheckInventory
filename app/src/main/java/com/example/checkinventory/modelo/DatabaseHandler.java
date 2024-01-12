package com.example.checkinventory.modelo;

import androidx.room.Room;

import com.example.checkinventory.excepciones.BusquedaInfrucuosaException;

import java.util.List;

public class DatabaseHandler {

    ArticuloDao articuloDao;

    public DatabaseHandler(String nombreDatabase){
        ArticulosDatabase db = Room.databaseBuilder(MyApplication.getAppContext(),
                ArticulosDatabase.class, nombreDatabase).allowMainThreadQueries().build();

        articuloDao = db.articuloDao();
    }

    public void vaciarDatabase(){
        articuloDao.deleteAll();
    }

    public Articulo busquedaPorIDC(String idc){
        Articulo articulo = articuloDao.busquedaPorIDC(idc);
        if(articulo == null) throw new BusquedaInfrucuosaException();

        return articulo;
    }

    public ListaDeArticulos busquedaPorModelo(String modelo){
        List<Articulo> busqueda = articuloDao.busquedaPorModelo(modelo);
        if(busqueda.size() == 0) throw new BusquedaInfrucuosaException();

        ListaDeArticulos articulos = new ListaDeArticulos(busqueda);

        return articulos;
    }

    public Articulo busquedaPorModeloYTalle(String modelo, String talle){
        Articulo articulo = articuloDao.busquedaPorModeloYTalle(modelo, talle);
        if(articulo == null) throw new BusquedaInfrucuosaException();

        return articulo;
    }

    public void actualizarArticulo(Articulo articulo){
        articuloDao.actualizarArticulo(articulo);
    }

    public Articulo chequearArticulo(Articulo articulo){
        articulo.checkearArticulo();
        articuloDao.actualizarArticulo(articulo);
        return articulo;
    }

    public Articulo chequearArticulo(String modelo, String talle){
        Articulo articulo = articuloDao.busquedaPorModeloYTalle(modelo, talle);
        articulo.checkearArticulo();
        articuloDao.actualizarArticulo(articulo);
        return articulo;
    }

    public Articulo chequearArticulo(String idc){
        Articulo articulo = articuloDao.busquedaPorIDC(idc);
        articulo.checkearArticulo();
        articuloDao.actualizarArticulo(articulo);
        return articulo;
    }
}