package controllers;

import dominio.usuario.VerificarAdmin;
import dominio.usuario.VerificarUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
	
	public static ModelAndView loginFailure(Request req, Response res) {
		
		return new ModelAndView(null,"/home/errorLogin.hbs");
	}
	
    public static ModelAndView login(Request req, Response res) {
        Map<String, String> model = new HashMap<>();
        if (VerificarUsuario.verificar(RequestUtil.getQueryUsername(req), RequestUtil.getQueryPassword(req)))
        {
            req.session().attribute("currentUser", req.queryParams("usuario"));
            return new ModelAndView(null, "/home/usuario.hbs");
        }
        else if (  VerificarAdmin.verificar(RequestUtil.getQueryUsername(req), RequestUtil.getQueryPassword(req)))
        {
            req.session().attribute("currentUser", req.queryParams("usuario"));
            return new ModelAndView(null, "/admin/adminBase.hbs");
        }
        else
            {
                res.redirect("/loginFailure");
                return null;
            }

    }

    public static ModelAndView logout(Request req, Response res) {
        req.session().removeAttribute("currentUser");
        res.redirect("/");
        return null;
    }
}
