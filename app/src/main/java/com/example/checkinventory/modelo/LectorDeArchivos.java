package com.example.checkinventory.modelo;


import androidx.room.Room;

import com.example.checkinventory.excepciones.FilasNoOrdenadasException;
import com.example.checkinventory.excepciones.NoExisteColumnaException;
import com.example.checkinventory.excepciones.NoExisteElArticuloException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivos {

    private static final String IDC = "IdC";
    private static final String MARCA = "Marca";
    private static final String MODELO = "Modelo";
    private static final String DESCRIPCION = "Descripcion";
    private static final String STOCK = "Suc 1";
    private static final String TALLE = "Talle";

    private final Sheet sheet;

    private final int IDC_INDEX;
    private final int MARCA_INDEX;
    private final int MODELO_INDEX;
    private final int DESCRIPCION_INDEX;
    private final int STOCK_INDEX;
    private final int TALLE_INDEX;


    public LectorDeArchivos(File archivo)
            throws IOException, InvalidFormatException, NoExisteColumnaException/*, FilasNoOrdenadasException*/ {

        Workbook wb = WorkbookFactory.create(archivo);
        sheet = wb.getSheetAt(0);


        IDC_INDEX = indiceDeColumna(sheet, IDC);
        MARCA_INDEX = indiceDeColumna(sheet, MARCA);
        MODELO_INDEX = indiceDeColumna(sheet, MODELO);
        DESCRIPCION_INDEX = indiceDeColumna(sheet, DESCRIPCION);
        STOCK_INDEX = indiceDeColumna(sheet, STOCK);
        TALLE_INDEX = indiceDeColumna(sheet, TALLE);

        //YA NO ES NECESARIO VERIFICAR ORDEN PORQUE USO LA BASE DE DATOS PARA BUSCAR COSAS
        /*
        if(!filasOrdenadas()){
            throw new FilasNoOrdenadasException();
        }*/


    }

    private int indiceDeColumna(Sheet sheet, String columna) throws NoExisteColumnaException{

        int index = -1;

        Row firstRow = sheet.getRow(0);
        for (Cell cell : firstRow) {
            if(cell.getStringCellValue().equals(columna)){
                index = cell.getColumnIndex();
            }
        }

        if(index == -1){
            throw new NoExisteColumnaException();
        }
        return index;
    }

    //YA NO ES NECSARIO GRACIAS A LA BASE DE DATOS
    /*
    private boolean filasOrdenadas(){

        int primeraFila = sheet.getFirstRowNum() + 1;
        int ultimaFila = sheet.getLastRowNum();
        int filaAnterior = primeraFila;

        Cell anterior;
        Cell siguiente;

        for (int filaActual = primeraFila; filaActual <= ultimaFila; filaActual++) {

            anterior = sheet.getRow(filaAnterior).getCell(MODELO_INDEX);
            siguiente = sheet.getRow(filaActual).getCell(MODELO_INDEX);
            filaAnterior = filaActual;

            if(anterior.getStringCellValue().compareTo(siguiente.getStringCellValue()) > 0){
                return false;
            }
        }
        return true;
    }*/

    public Codigo buscarArticulo(long idc){

        String idcstr = Long.toString(idc);

        for(Row row: sheet){
            if(row.getCell(IDC_INDEX).getStringCellValue().equals(idcstr))
                return new Codigo(row.getCell(IDC_INDEX).getStringCellValue(), row.getCell(MODELO_INDEX).getStringCellValue(), row.getCell(TALLE_INDEX).getStringCellValue(), row.getCell(DESCRIPCION_INDEX).getStringCellValue());
        }

        throw new NoExisteElArticuloException();
    }

    public List<Codigo> buscarArticulo(String modelo){

        List<Codigo> codigos = new ArrayList<>();
        modelo = modelo.toLowerCase();
        int index = busquedaBinaria(modelo);
        Row row = sheet.getRow(index);

        if(index == -1)
            throw new NoExisteElArticuloException();

        while(row.getCell(MODELO_INDEX).getStringCellValue().toLowerCase().equals(modelo)) {

            codigos.add(new Codigo(row.getCell(IDC_INDEX).getStringCellValue(),
                    row.getCell(MODELO_INDEX).getStringCellValue(),
                    row.getCell(TALLE_INDEX).getStringCellValue(),
                    row.getCell(DESCRIPCION_INDEX).getStringCellValue()));

            index += 1;
            row = sheet.getRow(index);
        }
        return codigos;
    }

    private int busquedaBinaria(String modelo){

        int inferior = sheet.getFirstRowNum();
        int superior = sheet.getLastRowNum();
        int centro;
        String modeloCentro;
        modelo = modelo.toLowerCase();

        while(inferior <= superior){

            centro = ((superior + inferior) / 2);
            modeloCentro = sheet.getRow(centro).getCell(MODELO_INDEX).getStringCellValue().toLowerCase();

            if(modeloCentro.equals(modelo)){
                while(modeloCentro.equals(modelo)){
                    centro = centro - 1;
                    modeloCentro = sheet.getRow(centro).getCell(MODELO_INDEX).getStringCellValue().toLowerCase();
                }
                return centro + 1;
            }
            else if(modeloCentro.compareTo(modelo) > 0) superior = centro-1;
            else inferior = centro+1;
        }
        return -1;
    }

    public void guardarArticulosEnDataBase(ArticuloDao articuloDao){
        int filaActual = sheet.getFirstRowNum() + 1;
        int ultimaFila = sheet.getLastRowNum();
        Row row;

        while(filaActual <= ultimaFila) {

            row = sheet.getRow(filaActual);

            String talle = null;
            Cell cellTalle = row.getCell(TALLE_INDEX);
            if(cellTalle != null){talle = cellTalle.getStringCellValue();}

            articuloDao.insert(new Articulo(row.getCell(IDC_INDEX).getStringCellValue(),
                    row.getCell(MARCA_INDEX).getStringCellValue(),
                    row.getCell(MODELO_INDEX).getStringCellValue(),
                    row.getCell(DESCRIPCION_INDEX).getStringCellValue(),
                    (int)row.getCell(STOCK_INDEX).getNumericCellValue(),
                    talle));
            filaActual += 1;
        }


    }
}
