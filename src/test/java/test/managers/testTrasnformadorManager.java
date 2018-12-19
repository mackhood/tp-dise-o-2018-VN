package test.managers;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.transformador.Transformador;
import dominio.usuario.Administrador;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import persistence.AdministradorManager;
import persistence.TransformadorManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class testTrasnformadorManager {







    @Test
    public void testGetInstance() {

        TransformadorManager instance = TransformadorManager.getInstance();


        assertEquals(instance.getClass(),TransformadorManager.class);

    }


/*
    @Test
    public void testObtenerTrasformador () {

        TransformadorManager instance = TransformadorManager.getInstance();

        assertEquals(null,instance.obtenerTrasformador(7).getClass());


    }




    @Test
    public void testObtenerIdBD () {
        TransformadorManager instance = TransformadorManager.getInstance();


        assertEquals(null,instance.obtenerIdBD(7));


    }

*/




}
