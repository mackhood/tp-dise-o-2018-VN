package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	public static ModelAndView showLoginForm(Request req, Response res) {
		return new ModelAndView(null, "home/login.hbs");
	}

}
