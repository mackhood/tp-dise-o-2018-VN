package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.Periodo;
import dominio.dispositivo.TipoDispositivo;
import dominio.usuario.Cliente;
import persistence.ClienteManager;
import persistence.DispositivosManager;
import reportes.ReporteConsumoPorHogar;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController {

	public ModelAndView show(Request req, Response res) {
		return new ModelAndView(null, "home/adminBase.hbs");
	}

	public ModelAndView seleccionarCantidad(Request req, Response res) {
		return new ModelAndView(null, "/admin/hogaresBase.hbs");
	}

	public ModelAndView verHogares(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		int n = Integer.parseInt(req.queryParams("cant"));

		List<Cliente> hogares = ClienteManager.getInstance().obtenerPrimerosNClientes(n);
		model.put("hogares", hogares);
		model.put("cant", n);
		return new ModelAndView(model, "/admin/hogares.hbs");
	}

	public ModelAndView busquedaHogar(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		String apellido = req.queryParams("apellido");
		List<Cliente> hogares = ClienteManager.getInstance().filtrarClientesPorApellido(apellido);

		model.put("hogares", hogares);

		return new ModelAndView(model, "/admin/busquedaHogar.hbs");
	}

	public ModelAndView verConsumos(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		String idCliente = req.params("id");
		List<Intervalo> consumos = ClienteManager.getInstance().getIntervalosDeUso(Long.parseLong(idCliente));

		List<DispositivoInteligente> dispositivosUsados = ClienteManager.getInstance()
				.getDispositivoConsumo(Long.parseLong(idCliente));
		model.put("consumos", consumos);
		model.put("dispositivos", dispositivosUsados);
		List<Double> valorConsumos = ClienteManager.getInstance().valorConsumosDeCliente(Long.parseLong(idCliente));
		model.put("valores", valorConsumos);
		
		if(!consumos.isEmpty())
		{
			return new ModelAndView(model, "/admin/consumo.hbs");
		}
		
		else
		{
			res.redirect("/admin/noconsumos");
			return null;
		}
	}
	
	public ModelAndView hogarSinConsumos(Request req,Response res)
	{
		return new ModelAndView(null,"/admin/usuarioSinConsumos.hbs");
	}

	public ModelAndView verReportes(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		return new ModelAndView(model, "/admin/reportes.hbs");
	}

	// PARA LOS REQUERIMIENTOS NO PEDIDOS EN EL ENUNCIADO
	public ModelAndView noFeatureHere(Request req, Response res) {

		return new ModelAndView(null, "/admin/noFeature.hbs");
	}

	public ModelAndView noResultsFound(Request req, Response res) {
		return new ModelAndView(null, "/admin/noResFound.hbs");
	}

	public ModelAndView ingresarDatos(Request req, Response res) {

		String reporte = req.queryParams("reporte");

		if (reporte.equals("hogar")) {

			return new ModelAndView(null, "/admin/reporteDatos.hbs");
		}

		else {
			res.redirect("/admin/reportes/noFeature");
			return null;
		}
	}

	public ModelAndView seleccionHogar(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		String nombre = req.queryParams("nombre");
		String apellido = req.queryParams("apellido");
		String calle = req.queryParams("calle");

		if (!nombre.equals("") || !apellido.equals("") || !calle.equals("")) {
			List<Cliente> clientes = ClienteManager.getInstance().filtradoClientes(nombre, apellido, calle);

			if (clientes.isEmpty()) {
				res.redirect("/admin/reportes/noResultsFound");
				return null;
			}

			else {
				model.put("clientes", clientes);
				return new ModelAndView(model, "admin/listadoBusqueda.hbs");
			}
		}

		else {
			return new ModelAndView(null, "admin/reporteDatos.hbs");
		}
	}

	public ModelAndView seleccionIntervalo(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		String id = req.params("id");
		model.put("id", id);
		return new ModelAndView(model, "admin/resultadosReporte.hbs");
	}

	public ModelAndView resultadosReporte(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		String inicio = req.queryParams("fechaInicio");
		String fin = req.queryParams("fechaFin");
		String id = req.params("id");
		ReporteConsumoPorHogar r = new ReporteConsumoPorHogar();

		double resultado = r.consumoDeHogarEnPeriodo(Long.parseLong(id),
				new Periodo(LocalDateTime.parse(inicio), LocalDateTime.parse(fin), null));
		model.put("resultado", resultado);
		return new ModelAndView(model, "/admin/resultadosReporte.hbs");
	}

	public ModelAndView verAltaDispositivos(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		
		List <String> tipos = new ArrayList<>();
		
		DispositivosManager.getInstance().getTipos().stream().forEach(t-> tipos.add(t.getNombre()));
		
		model.put("tipoDisp", tipos);

		return new ModelAndView(model, "/admin/dispositivosAlta.hbs");
	}
	
	public ModelAndView cargarTipo(Request req, Response res)
	{
		return new ModelAndView(null, "/admin/cargarTipo.hbs");
	}
	
	public ModelAndView confirmarTipo(Request req, Response res)
	{
		String nombre = req.queryParams("nombre");
		String min = req.queryParams("minOptimo");
		String max = req.queryParams("maxOptimo");
		
		TipoDispositivo nuevoTipo = new TipoDispositivo(nombre,Double.parseDouble(min),Double.parseDouble(max));
		DispositivosManager.getInstance().persistirTipoDispositivo(nuevoTipo);
		
		res.redirect("/admin/dispositivosAlta");
		return null;
	}

	public ModelAndView confirmarAlta(Request req, Response res) {
		Map<String, String> model = new HashMap<>();

		String equipo = req.queryParams("tipo");
		String detalle = req.queryParams("detalle");
		String kwh = req.queryParams("kwh");
		model.put("kwh", kwh);
		model.put("equipo", equipo);
		model.put("detalle", detalle);
		return new ModelAndView(model, "/admin/confirmarAlta.hbs");
	}

	public ModelAndView altaConfirmada(Request req, Response res) {

		String equipo = req.params("equipo");
		String detalle = req.params("detalle");
		String kwh = req.params("kwh");

		DispositivoInteligente di = new DispositivoInteligente.DispositivoInteligenteBuilder(equipo)
				.equipoConcreto(detalle).consumoEstimadoPorHora(Double.parseDouble(kwh)).build();
		di.setTipo(DispositivosManager.getInstance().getTipoPorNombre(equipo));
		DispositivosManager.getInstance().persistirDispositivoInteligente(di);

		res.redirect("/admin/dispositivosAlta");
		return null;

	}
}
