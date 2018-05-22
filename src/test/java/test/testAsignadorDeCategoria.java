package test;

import Clases.Categoria.AsignadorDeCategoria;
import Clases.Categoria.Categoria;
import Clases.Usuario.Cliente;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testAsignadorDeCategoria {

    private AsignadorDeCategoria unAsignadorDeCategoria;
    private Cliente unClienteMock;
    private Categoria categoria1Mock;
    private Categoria categoria2Mock;

    @Before
    public void setUp() {
        unClienteMock = mock(Cliente.class);
        categoria1Mock = mock(Categoria.class);
        categoria2Mock = mock(Categoria.class);
        when(unClienteMock.consumoDEEnergeticoTotal()).thenReturn(200.1);
        when(categoria1Mock.cumpleCondicion(unClienteMock)).thenReturn(true);
        when(categoria2Mock.cumpleCondicion(unClienteMock)).thenReturn(false);
        unAsignadorDeCategoria = AsignadorDeCategoria.getInstance();
    }

    @Test
    public void definirCategoriaParaUnClienteMock() {
        List<Categoria> listaCategoriaMock = new ArrayList<>();
        listaCategoriaMock.add(categoria1Mock);
        listaCategoriaMock.add(categoria2Mock);
        assertEquals(categoria1Mock, unAsignadorDeCategoria.categoriaCliente(unClienteMock, listaCategoriaMock));
    }

}
