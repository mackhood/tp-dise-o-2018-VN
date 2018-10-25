package controllers;

import dominio.usuario.VerificarAdmin;
import dominio.usuario.VerificarUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

	
	public static ModelAndView show(Request req, Response res){
		return new ModelAndView(null, "home/login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {

		Map<String, String> model = new HashMap<>();
		if(VerificarUsuario.verificar(req.queryParams("usuario"),req.queryParams("password"))  )

		{


			req.session().attribute("currentUser", req.queryParams("usuario"));
			//req.cookie("currentUser");
			return new ModelAndView(null,"/home/usuario.hbs");

		}
			else {

			if(!VerificarAdmin.verificar(req.queryParams("admin"),req.queryParams("password")) )
			{


				req.session().attribute("currentUser", req.queryParams("admin"));
				//req.cookie("currentUser");
				return new ModelAndView(null,"/home/admin.hbs");
			}

			else {

				//Spark.halt(401,"Usuario o contrasenia incorrecto");
				//Spark.notFound("<html><body><h1>Custom 404 handling</h1></body></html>");
				res.redirect("/login");
				return new ModelAndView(null,"/home/login");

			}
		}



	}

	public static ModelAndView logout(Request req, Response res){
		req.session().removeAttribute("currentUser");
		res.redirect("/login");
		return null;
	}
	
}
