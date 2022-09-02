package com.example.checkinventory;

import com.example.checkinventory.modelo.Codigo;
import com.example.checkinventory.modelo.ListaDeCodigos;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ListaDeCodigosTest {
    @Test

    public void ListaDeTallesFuncionaCorrectamente(){
        Codigo codigo1 = new Codigo("123456789", "A12345678", "10", "descripcion");
        Codigo codigo2 = new Codigo("123456789", "A12345678", "11", "descripcion");
        Codigo codigo3 = new Codigo("987654321", "A87654321", "12", "descripcion");
        Codigo codigo4 = new Codigo("123456789", "A12345678", "13", "descripcion");

        ListaDeCodigos listaDeCodigos = new ListaDeCodigos();

        listaDeCodigos.agregarCodigo(codigo1);
        listaDeCodigos.agregarCodigo(codigo2);
        listaDeCodigos.agregarCodigo(codigo3);
        listaDeCodigos.agregarCodigo(codigo4);

        ArrayList<String> talles = new ArrayList<>(Arrays.asList("10", "11", "12", "13"));

        assertTrue(listaDeCodigos.listaDeTalles().equals(talles));
    }
}
