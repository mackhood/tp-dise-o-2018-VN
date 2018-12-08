package test.Controllers;

import controllers.LoginController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class testLoginController {


    private LoginController controller;
    private Request request;
    private Response response;
    private RequestUtil requestUtil;
    @Before
    public void setUp() {

        LoginController controller = new LoginController();
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        RequestUtil requestUtil = mock(RequestUtil.class);

    }

    @Test
    public void testLoginFailure() {

        Assert.assertEquals("/home/errorLogin.hbs",(controller.loginFailure(request,response)).getViewName());

    }



    @Test
    public void testLoginUsuario() {

        when(requestUtil.getQueryUsername(request)).thenReturn("galvanariel");
        when(requestUtil.getQueryPassword(request)).thenReturn("password");


        Assert.assertEquals("/home/usuario.hbs",(controller.login(request,response)).getViewName());

    }




    @Test
    public void testLoginAdmin() {

        when(requestUtil.getQueryUsername(request)).thenReturn("gerjor");
        when(requestUtil.getQueryPassword(request)).thenReturn("1234");


        Assert.assertEquals("/admin/adminBase.hbs",(controller.login(request,response)).getViewName());

    }

    @Test
    public void testLoginFailed() {

        when(requestUtil.getQueryUsername(request)).thenReturn("xxx");
        when(requestUtil.getQueryPassword(request)).thenReturn("1111");


        Assert.assertEquals("/loginFailure",(controller.login(request,response)).getViewName());

    }



    @Test
    public void testLogOut() {

        Assert.assertEquals("/",(controller.logout(request,response)).getViewName());

    }









}
