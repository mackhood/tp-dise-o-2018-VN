package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuarioController {

    public ModelAndView show(Request req, Response res) {
        return new ModelAndView(null, "home/usuario.hbs");
    }
}
