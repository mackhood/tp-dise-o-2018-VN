package test.Controllers;

import controllers.HomeController;
import controllers.UsuarioController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.Request;
import spark.Response;

public class testUsuarioController extends Mockito implements WithGlobalEntityManager {


    private UsuarioController controller;
    private Request request;
    private Response response;

    @Before
    public void setUp() {

        UsuarioController controller = new UsuarioController();
        Request request = mock(Request.class);
        Response response = mock(Response.class);

    }


    @Test
    public void testShow() {

        Assert.assertEquals("home/usuario.hbs",(controller.show(request,response)).getViewName());

    }


    @Test
    public void testShowConfirmacionRecomendacionHogar() {

        Assert.assertEquals("usuario/recomendacion.hbs",(controller.showConfirmacionRecomendacionHogar(request,response)).getViewName());

    }


    @Test
    public void testRealizarRecomendacion() {

        Assert.assertEquals("usuario/resRecomendacionHogar.hbs",(controller.realizarRecomendacion(request,response)).getViewName());

    }

    @Test
    public void testShowConsumoPeriodon() {

        Assert.assertEquals("usuario/consumoPeriodo.hbs",(controller.showConsumoPeriodo(request,response)).getViewName());

    }
    @Test
    public void testConsumoPeriodoo() {

        Assert.assertEquals("usuario/resConsumoPeriodo.hbs",(controller.consumoPeriodo(request,response)).getViewName());

    }


    @Test
    public void testshowConsumoUltimoPeriodo() {

        Assert.assertEquals("usuario/consumoUltimoPeriodo.hbs",(controller.showConsumoUltimoPeriodo(request,response)).getViewName());

    }




}
