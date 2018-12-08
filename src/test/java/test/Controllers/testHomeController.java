package test.Controllers;
import controllers.AdminController;
import controllers.HomeController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class testHomeController  extends Mockito implements WithGlobalEntityManager {

    private HomeController controller;
    private Request request;
    private Response response;

    @Before
    public void setUp() {

        HomeController controller = new HomeController();
        Request request = mock(Request.class);
        Response response = mock(Response.class);

    }


    @Test
    public void testShowLoginForm() {

        Assert.assertEquals("home/login.hbs",(controller.showLoginForm(request,response)).getViewName());

    }



}
