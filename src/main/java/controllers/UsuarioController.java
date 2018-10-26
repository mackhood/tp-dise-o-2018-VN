package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.awt.image.RescaleOp;

public class UsuarioController {

    public ModelAndView show(Request req, Response res)
    {
        return new ModelAndView(null,"home/usuario.hbs");
    }
}
