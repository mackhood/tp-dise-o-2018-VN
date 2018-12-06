package test.otros;

import dominio.repositories.RepositorioCategoria;
import org.junit.Assert;
import org.junit.Ignore;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class testRepositorioClientes {

    @Ignore
    public void testDadoUnJsonDeTestSeObtieneCorrectamenteLaCantidadDeRegistros() throws Exception {
        RepositorioCategoria repositorio = Mockito.mock(RepositorioCategoria.class);
        String archivo = "testClientes.json";
        when(repositorio.getJsonFile(archivo)).thenReturn(this.getJsonTestFile());
        when(repositorio.obtenerCategorias()).thenCallRealMethod();

        int sizeExoected = repositorio.obtenerCategorias().size();

        Assert.assertEquals("Se obtienen todas las categorias en el json y se valida su cantidad", 1, sizeExoected);
    }

    public String getJsonTestFile() {
        return getClass().getClassLoader().getResource("testClientes.json").getFile();
    }

}