package com.example.checkinventory;

import com.example.checkinventory.modelo.Codigo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CodigoTest {
    @Test
    public void comparacionDeCodigosEsCorrecta() {

        Codigo codigo1 = new Codigo("123456789", "A12345678", "10", "descripcion");
        Codigo codigo2 = new Codigo("123456789", "A12345678", "10", "descripcion");
        Codigo codigo3 = new Codigo("987654321", "A87654321", "10", "descripcion");
        Codigo codigo4 = new Codigo("123456789", "A12345678", "22", "descripcion");

        assertTrue( codigo1.esIgual(codigo2));
        assertFalse( codigo1.esIgual(codigo3));
        assertFalse( codigo1.esIgual(codigo4));
    }
}