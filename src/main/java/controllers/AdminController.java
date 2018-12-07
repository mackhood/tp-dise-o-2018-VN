package controllers;

import dominio.Consumo;
import dominio.dispositivo.Intervalo;
import dominio.manager.ClienteManager;
import dominio.usuario.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    	Map<String, List<Consumo>> model = new HashMap<>();
    	
    	String idCliente = req.params("id");
    	List<Consumo> consumos = ClienteManager.getInstance().getConsumosDeCliente(Long.parseLong(idCliente));
    	model.put("consumos", consumos);
    	return new ModelAndView(model,"/admin/consumo.hbs");
    }
    
    public ModelAndView verReportes(Request req, Response res) {
    	
    	return new ModelAndView(null,"/admin/reportes.hbs");
    }
    
    public ModelAndView ingresarDatos(Request req, Response res) {
    	Map<String, List<Cliente>> model = new HashMap<>();

        List<Cliente> hogares = ClienteManager.getInstance().getClientesDeLaBD();
        
    	String value = req.params("reporte");
    	
    	model.put("hogares",hogares);
    	return new ModelAndView(model,"/admin/reporteDatos.hbs");
    }
    
    public ModelAndView verReporteSeleccionado(Request req, Response res) {
    	Map<String, List<Intervalo>> model = new HashMap<>();
    	
    	String id = req.params("id");
    	List<Intervalo> li = ClienteManager.getInstance().getIntervalosDeUso(Long.parseLong(id));
    	model.put("intervalos",li);
    	return new ModelAndView(model,"/admin/reporteHogarPeriodo.hbs");
    }
}





