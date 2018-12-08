package test.otros;

import dominio.repositories.RepositorioCategoria;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class testRepositorioClientes {

    @Test
    public void testDadoUnJsonDeTestSeObtieneCorrectamenteLaCantidadDeRegistros() throws Exception {
        RepositorioCategoria repositorio = Mockito.mock(RepositorioCategoria.class);
        when(repositorio.getJsonFile()).thenReturn(this.getJsonTestFile());
        when(repositorio.obtenerCategorias()).thenCallRealMethod();

        int sizeExoected = repositorio.obtenerCategorias().size();

        Assert.assertEquals("Se obtienen todas las categorias en el json y se valida su cantidad", 1, sizeExoected);
    }

    public String getJsonTestFile() {
        return getClass().getClassLoader().getResource("testClientes.json").getFile();
    }

}