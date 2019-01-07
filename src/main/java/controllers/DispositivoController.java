package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import persistence.*;
import dominio.usuario.Cliente;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DispositivoController extends AbstractPersistenceTest implements WithGlobalEntityManager {

	public ModelAndView listarDispositivosDeCliente(Request req, Response res)
    {
        Map<String,Object> model = new HashMap<>();
		long id = ClienteManager.getInstance()
        		.getIdDelClientePorUsuario(RequestUtil.getSessionCurrentUser(req));
        
	        List<DispositivoInteligente> dispositivos = DispositivosManager.getInstance().getDispositivosDeCliente(id);

	        model.put("dispositivos",dispositivos);
	        
	        if (dispositivos.stream().anyMatch(d -> DispositivosManager.getInstance().tieneIntervalos(d.getId())))
		    {
		        Intervalo ultimoIntervaloDeUso = ClienteManager.getInstance().ultimoIntervalo(id);
		        model.put("intervalo",ultimoIntervaloDeUso);
		        
		        double consumoUltimo = DispositivosManager.getInstance().dispUltimoConsumo(id).consumoParaIntervalo(ultimoIntervaloDeUso);
		        model.put("consumo",consumoUltimo);
		    }
	        
        return new ModelAndView(model,"/usuario/dispositivo.hbs");
    }

	public ModelAndView consumoUltimoPeriodo(Request req, Response res) {

		Map<String, Double> model = new HashMap<>();
		String id = req.params("id");
		Long idd = Long.valueOf(id).longValue();
		model.put("consumoUltimoPeriodo", DispositivosManager.getInstance().getConsumoUltimoPeriodo((idd)));

		return new ModelAndView(model, "/usuario/consumoUltimoPeriodo.hbs");
	}

	public ModelAndView verBajar(Request req, Response res) {
		Map<String, DispositivoInteligente> model = new HashMap<>();
		String id = req.params("id");

		DispositivoInteligente disp = DispositivosManager.getInstance()
				.getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));
		model.put("dispositivo", disp);
		return new ModelAndView(model, "usuario/bajar.hbs");
	}

	public ModelAndView bajar(Request req, Response res) {

		String id = req.params("id");
		DispositivoInteligente disp = DispositivosManager.getInstance()
				.getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));

		DispositivosManager.getInstance().borrarDispositivoInteligene(disp);

		res.redirect("/usuario");
		return new ModelAndView(null, "usuario/bajar.hbs");
	}

}
