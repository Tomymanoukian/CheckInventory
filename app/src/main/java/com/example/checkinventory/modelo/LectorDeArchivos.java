package com.example.checkinventory.modelo;


import com.example.checkinventory.excepciones.FilasNoOrdenadasException;
import com.example.checkinventory.excepciones.NoExisteColumnaException;

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

    private  Sheet sheet;

    private final int IDC_INDEX;
    private final int MARCA_INDEX;
    private final int MODELO_INDEX;
    private final int DESCRIPCION_INDEX;
    private final int STOCK_INDEX;
    private final int TALLE_INDEX;


    public LectorDeArchivos(File archivo)
            throws IOException, InvalidFormatException, NoExisteColumnaException, FilasNoOrdenadasException {

        Workbook wb = WorkbookFactory.create(archivo);
        sheet = wb.getSheetAt(0);


        IDC_INDEX = indiceDeColumna(sheet, IDC);
        MARCA_INDEX = indiceDeColumna(sheet, MARCA);
        MODELO_INDEX = indiceDeColumna(sheet, MODELO);
        DESCRIPCION_INDEX = indiceDeColumna(sheet, DESCRIPCION);
        STOCK_INDEX = indiceDeColumna(sheet, STOCK);
        TALLE_INDEX = indiceDeColumna(sheet, TALLE);

        if(!filasOrdenadas()){
            throw new FilasNoOrdenadasException();
        }


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

    public boolean filasOrdenadas(){

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
    }

    public Codigo buscarArticulo(long idc){

        String idcstr;

        for(Row row: sheet){
            //if(Long.parseLong(row.getCell(IDC_INDEX).getStringCellValue()) == idc)
            //    return new Codigo(row.getCell(IDC_INDEX).getStringCellValue(), row.getCell(MODELO_INDEX).getStringCellValue(), row.getCell(TALLE_INDEX).getStringCellValue(), row.getCell(DESCRIPCION_INDEX).getStringCellValue());
            //Cell cell =  row.getCell(MODELO_INDEX);
            idcstr = sheet.getRow(3).getCell(IDC_INDEX).getStringCellValue();

        }

        return null;
    }

    public List<Codigo> buscarArticulo(String modelo){

        List<Codigo> codigos = new ArrayList<>();
        modelo = modelo.toLowerCase();
        int index = busquedaBinaria(modelo);
        Row row = sheet.getRow(index);

        if(index == -1)
            return null;

        while(row.getCell(MODELO_INDEX).getStringCellValue().toLowerCase().equals(modelo)) {
            codigos.add(new Codigo(row.getCell(IDC_INDEX).getStringCellValue(), row.getCell(MODELO_INDEX).getStringCellValue(), row.getCell(TALLE_INDEX).getStringCellValue(), row.getCell(DESCRIPCION_INDEX).getStringCellValue()));
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
    
//BORRAR ->
    public static void leerArchivo(File archivo) throws IOException, InvalidFormatException {

        Workbook wb = WorkbookFactory.create(archivo);

        //DataFormatter formatter = new DataFormatter();
        Sheet sheet1 = wb.getSheetAt(0);

        Row row1 = sheet1.getRow(2);
        Cell cell1 = row1.getCell(3);

        System.out.println("--------------------------------------------------------------------------------------");

        System.out.println(cell1.getStringCellValue());

        System.out.println("--------------------------------------------------------------------------------------");


        /*for (Row row : sheet1) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");
                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)

                /*String text = formatter.formatCellValue(cell);
                System.out.println(text);*/


                // Alternatively, get the value and format it yourself
                /*switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellFormula());
                        break;
                    default:
                        System.out.println();
                }
            }
        }*/
    }

}

