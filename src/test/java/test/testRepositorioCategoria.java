package test;

import Clases.Categoria;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioCategoria;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class testRepositorioCategoria {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        return;
    }

    @Test
    public void testDevuelveListaVacia() throws ProcessingDataFailedException {

        List<Categoria> categoriasVacia = new ArrayList<Categoria>();
        RepositorioCategoria repoCateogoriaSinDatos = Mockito.spy(RepositorioCategoria.class);

        when(repoCateogoriaSinDatos.obtenerCategorias())
                .thenReturn(categoriasVacia);

        assertThat("Prueba de obtencion de lista vacia", repoCateogoriaSinDatos.obtenerCategorias().size(), equalTo(0));
    }

    @Test
    public void testDadoUnJsonDeTestSeObtieneCorrectamenteLaCantidadDeRegistros() throws Exception {
        RepositorioCategoria repositorio = spy(RepositorioCategoria.class);
        when(repositorio.getJsonFile()).thenReturn(this.getJsonTestFile());

        int sizeExoected = repositorio.obtenerCategorias().size();

        assertEquals("Se obtienen todas las categorias en el json y se valida su cantidad", 4, sizeExoected);
    }

    public String getJsonTestFile() {
        return getClass().getClassLoader().getResource("testCategoria.json").getFile();
    }

}
