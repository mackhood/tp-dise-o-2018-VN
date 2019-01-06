package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.usuario.Cliente;
import persistence.ClienteManager;
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
        return new ModelAndView(null,"usuario/recomendacion.hbs");
    }
    
    public ModelAndView realizarRecomendacion(Request req, Response res)
    {

        ClienteManager.getInstance().ejecutarRecomendacionHogar(RequestUtil.getSessionCurrentUser(req));
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

        return new ModelAndView(model,"usuario/consumoPeriodo.hbs");
    }
    
    public ModelAndView todasLasMediciones(Request req, Response res)
    {
    	Map<String,Object> model = new HashMap<>();
    	
    	long id = ClienteManager.getInstance().getIdDelClientePorUsuario(RequestUtil.getSessionCurrentUser(req));
		
		List<Intervalo> consumos = ClienteManager.getInstance().getIntervalosDeUso(id);
		List<DispositivoInteligente> dispositivosUsados = ClienteManager.getInstance().getDispositivoConsumo(id);
		model.put("consumos", consumos);
		model.put("dispositivos",dispositivosUsados);
		List<Double> valorConsumos = ClienteManager.getInstance().valorConsumosDeCliente(id);
		model.put("valores",valorConsumos);
		
		if(!consumos.isEmpty())
		{
			return new ModelAndView(model, "/usuario/consumos.hbs");
		}
		
		else
		{
			res.redirect("/usuario/noconsumos");
			return null;
		}
	}
    
    public ModelAndView userSinConsumos(Request req,Response res)
    {
    	return new ModelAndView(null,"/usuario/sinConsumos.hbs");
    }
}
