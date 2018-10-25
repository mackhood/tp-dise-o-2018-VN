package controllers;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoInteligente;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosPersistirManager;
import dominio.usuario.RepositorioCliente;
import server.RequestUtil;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispositivoController {

    public static ModelAndView listar(Request req, Response res)
    {
        Map<String, List<DispositivoInteligente>> model = new HashMap<>();

        List<DispositivoInteligente> dispositivos = ClienteManager.getInstance().buscarClientePorUsuario(RequestUtil.getSessionCurrentUser(req)).getDispositivosInteligentes();

        model.put("dispositivos",dispositivos);

        return new ModelAndView(model,"/usuario/dispositivo.hbs");
    }
    public static ModelAndView modificar(Request req, Response res)
    {
        req.queryParams("dispositivo");
        return null;
    }

    /*
    public static ModelAndView crear(Request req, Response res)
    {

    }*/
}
