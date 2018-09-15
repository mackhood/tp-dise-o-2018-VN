package test.simplex;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.TipoDispositivo;
import dominio.repositories.RepositorioDispositivo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testAlgo {
    List<DispositivoInteligente> inteligentes = inteligentes = RepositorioDispositivo.getInstance().getInteligentes();
    @Before
    public void setUp(){
        //inteligentes = RepositorioDispositivo.getInstance().getInteligentes();
    }

    @Test
    public void restriccion()
    {
        TipoDispositivo tipoDispositivo = TipoDispositivo.AIREACONDICIONADO;
        Assert.assertEquals(90,tipoDispositivo.getRestriccionMinima());
    }

}
