package controllers;

import dominio.usuario.VerificarUsuario;
import persistence.ClienteManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
	
	public static ModelAndView loginFailure(Request req, Response res) {
		
		return new ModelAndView(null,"/home/errorLogin.hbs");
	}
	
	public static ModelAndView adminHome(Request req, Response res)
	{
		return new ModelAndView(null,"/admin/adminBase.hbs");
	}
	
	public static ModelAndView userHome(Request req, Response res)
	{
		return new ModelAndView(null,"/home/usuario.hbs");
	}
	
    public static Void login(Request req, Response res) {
    	
        if (VerificarUsuario.verificar(RequestUtil.getQueryUsername(req), RequestUtil.getQueryPassword(req)))
        {	
        	if(ClienteManager.getInstance().esCliente(RequestUtil.getQueryUsername(req)))
        	{	
        		Session session = req.session(true);
        		session.attribute("currentUser", req.queryParams("usuario"));
        		session.attribute("role", "user");
        		res.redirect("/usuario");
        	}
        	
        	else
        	{	
        		Session session = req.session(true);
        		session.attribute("currentUser", req.queryParams("usuario"));
        		session.attribute("role", "admin");
            	res.redirect("/admin");
        	}
        }
        
        else
            {
                res.redirect("/loginFailure");
            }
        
        return null;
    }

    public static Void logout(Request req, Response res) {
        Session session = req.session(true);
        session.invalidate();
        req.session().removeAttribute("currentUser");
        req.session().removeAttribute("role");
        res.redirect("/");
        return null;
    }
}
