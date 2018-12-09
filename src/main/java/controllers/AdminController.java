package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import dominio.repositories.RepositorioDispositivo;
import dominio.usuario.Cliente;
import reportes.ReporteConsumoPorHogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.apache.commons.lang3.tuple.Pair;

public class AdminController {

	public ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "home/adminBase.hbs");
	}

	public ModelAndView verHogares(Request req, Response res) {
		Map<String, List<Cliente>> model = new HashMap<>();

		List<Cliente> hogares = ClienteManager.getInstance().getClientesDeLaBD();
		model.put("hogares", hogares);
		return new ModelAndView(model, "/admin/hogares.hbs");
	}

	public ModelAndView verConsumos(Request req, Response res) {
		Map<String,Object> model = new HashMap<>();

		String idCliente = req.params("id");
		List<Intervalo> consumos = ClienteManager.getInstance().getIntervalosDeUso(Long.parseLong(idCliente));
		List<DispositivoInteligente> dispositivosUsados = ClienteManager.getInstance().getDispositivoConsumo(Long.parseLong(idCliente));
		model.put("consumos", consumos);
		model.put("dispositivos",dispositivosUsados);
		List<Double> valorConsumos = ClienteManager.getInstance().auxiliarAdminConsumosWeb(consumos, dispositivosUsados);
		model.put("valores",valorConsumos);
		return new ModelAndView(model, "/admin/consumo.hbs");
	}

	public ModelAndView verReportes(Request req, Response res) {
		Map<String,Object> model = new HashMap<>();
		
		return new ModelAndView(model, "/admin/reportes.hbs");
	}
	
	// PARA LOS REQUERIMIENTOS NO PEDIDOS EN EL ENUNCIADO
	public ModelAndView noFeatureHere(Request req, Response res) {
		
		return new ModelAndView(null,"/admin/noFeature.hbs");
	}
	
	public ModelAndView ingresarDatos(Request req, Response res) {
		Map<String, List<Cliente>> model = new HashMap<>();
		
		String reporte = req.queryParams("reporte");
		
		if (reporte.equals("hogar")) {
		
			List<Cliente> hogares = ClienteManager.getInstance().getClientesDeLaBD();
			model.put("hogares", hogares);
			return new ModelAndView(model, "/admin/reporteDatos.hbs");
		}
		
		else {
			res.redirect("/admin/reportes/noFeature");
			return null;
		}
	}

	public ModelAndView verReporteSeleccionado(Request req, Response res) {
		Map<String,Double> model = new HashMap<>();
		
		String inicio = req.queryParams("fechaInicio");
		String fin = req.queryParams("fechaFin");
		String id = req.queryParams("id");
		ReporteConsumoPorHogar r = new ReporteConsumoPorHogar();
		
		double resultado = r.consumoDeHogarEnPeriodo(Long.parseLong(id),new Periodo(LocalDateTime.parse(inicio),LocalDateTime.parse(fin),null));
		model.put("resultado",resultado);
		return new ModelAndView(model, "/admin/reporteDatos.hbs");
	}

	public ModelAndView verAltaDispositivos(Request req, Response res) {
		Map<String, List<DispositivoInteligente>> model = new HashMap<>();
		
		List<DispositivoInteligente> dispositivos = RepositorioDispositivo.getInstance().getInteligentes();
		model.put("dispositivos", dispositivos);

		return new ModelAndView(model, "/admin/dispositivosAlta.hbs");
	}

	public ModelAndView confirmarAlta(Request req, Response res) {
		Map<String,String> model = new HashMap<>(); 
		
		String equipo = req.params("equipo");
		String detalle = req.params("detalle");
		
		model.put("equipo",equipo);
		model.put("detalle", detalle);
		return new ModelAndView(model, "/admin/confirmarAlta.hbs");
	}

	public ModelAndView altaConfirmada(Request req, Response res) {
		
		String equipo = req.params("equipo");
		String detalle = req.params("detalle");
		
		DispositivoInteligente di = new DispositivoInteligente.DispositivoInteligenteBuilder(equipo).equipoConcreto(detalle).build();
		DispositivosManager.getInstance().persistirDispositivoInteligente(di);
		
		res.redirect("/admin/dispositivosAlta");
		return null;

	}
}
