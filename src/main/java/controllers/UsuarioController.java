package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Periodo;
import dominio.manager.ClienteManager;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.usuario.Cliente;
import reportes.ReporteConsumoPorHogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import javax.jws.WebParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController {

    public ModelAndView show(Request req, Response res)
    {
        return new ModelAndView(null,"home/usuario.hbs");
    }

    public ModelAndView showConfirmacionRecomendacionHogar(Request req, Response res)
    {
        //ClienteManager.getInstance().ejecutarRecomendacionHogar(RequestUtil.getQueryUsername(req));
        return new ModelAndView(null,"usuario/recomendacion.hbs");
    }
    public ModelAndView realizarRecomendacion(Request req, Response res)
    {

        ClienteManager.getInstance().ejecutarRecomendacionHogar(RequestUtil.getSessionCurrentUser(req));
        /*Cliente cliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario(RequestUtil.getSessionCurrentUser(req));
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();*/

        return new ModelAndView(null,"usuario/resRecomendacionHogar.hbs");
    }
    public ModelAndView showConsumoPeriodo(Request req, Response res)
    {
        return new ModelAndView(null,"usuario/consumoPeriodo.hbs");
    }
    public ModelAndView consumoPeriodo(Request req, Response res)
    {
        Map<String, Double> model = new HashMap<>();

        LocalDateTime fechaInicio = LocalDateTime.parse(req.queryParams("fechaInicio"));
        LocalDateTime fechaFin = LocalDateTime.parse(req.queryParams("fechaFin"));

        double consumoHogar = ClienteManager.getInstance().consumoHogar(RequestUtil.getSessionCurrentUser(req),fechaInicio, fechaFin);
        model.put("consumoHogar", consumoHogar);

        return new ModelAndView(model,"usuario/resConsumoPeriodo.hbs");
    }

}
