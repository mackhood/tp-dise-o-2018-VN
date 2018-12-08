package test.Controllers;
import controllers.AdminController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class testAdminController extends Mockito implements WithGlobalEntityManager {
    private AdminController controller;
    private Request request;
    private Response response;
    @Before
    public void setUp() {

        AdminController controller = new AdminController();
          Request request = mock(Request.class);
        Response response = mock(Response.class);

    }




    @Test
    public void testVerHogares() {

     Assert.assertEquals("/admin/hogares.hbs",(controller.verHogares(request,response)).getViewName());

   }


    @Test
    public void testShow() {

        Assert.assertEquals("home/adminBase.hbs",(controller.show(request,response)).getViewName());

    }


    @Test
    public void testverConsumos() {

        Assert.assertEquals("/admin/consumo.hbs",(controller.verConsumos(request,response)).getViewName());

    }

    @Test
    public void testVerReportes() {

        Assert.assertEquals("/admin/reportes.hbs",(controller.verReportes(request,response)).getViewName());

    }


    @Test
    public void testIngresarDatos() {

        Assert.assertEquals("/admin/reporteDatos.hbs",(controller.ingresarDatos(request,response)).getViewName());

    }


    @Test
    public void testVerReporteSeleccionado() {

        Assert.assertEquals("/admin/reporteDatos.hbs",(controller.verReporteSeleccionado(request,response)).getViewName());

    }



    @Test
    public void testVerAltaDispositivos() {

        Assert.assertEquals("/admin/dispositivosAlta.hbs",(controller.verAltaDispositivos(request,response)).getViewName());

    }




    @Test
    public void testConfirmarAlta() {

        Assert.assertEquals("/admin/confirmarAlta.hbs",(controller.confirmarAlta(request,response)).getViewName());

    }
    @Test
    public void testAltaConfirmada() {

        Assert.assertEquals("/admin/dispositivosAlta",(controller.altaConfirmada(request,response)).getViewName());

    }


    



















}
