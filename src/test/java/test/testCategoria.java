package test;

import dominio.categoria.Categoria;
import dominio.usuario.Cliente;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testCategoria {
    private Categoria categoriaTest;
    private Cliente unClienteMockPertenceCategoria;
    private Cliente unClienteMockNoPerteneceCategoria;

    @Before
    public void setUp() {

        categoriaTest = new Categoria("R1", 100, 150, 0.50, 1.0);
        unClienteMockPertenceCategoria = mock(Cliente.class);
        unClienteMockNoPerteneceCategoria = mock(Cliente.class);
        when(unClienteMockPertenceCategoria.consumoEnergeticoTotal()).thenReturn(125.0);
        when(unClienteMockNoPerteneceCategoria.consumoEnergeticoTotal()).thenReturn(200.0);
    }

    @Test
    public void testPerteneceAlaCategoriaUnCliente() {
        assertTrue(categoriaTest.cumpleCondicion(unClienteMockPertenceCategoria));
    }

    @Test
    public void testNoPerteneceAlaCategoriaUnCliente() {
        assertFalse(categoriaTest.cumpleCondicion(unClienteMockNoPerteneceCategoria));
    }


}
