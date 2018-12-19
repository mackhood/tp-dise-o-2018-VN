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
        
        Intervalo ultimoIntervaloDeUso = ClienteManager.getInstance().ultimoIntervalo(ClienteManager.getInstance()
        		.getIdDelClientePorUsuario(RequestUtil.getSessionCurrentUser(req)));
        model.put("intervalo",ultimoIntervaloDeUso);
        List<DispositivoInteligente> dispositivos = DispositivosManager.getInstance()
        		.getDispositivosDeCliente(ClienteManager.getInstance().getIdDelClientePorUsuario(RequestUtil.getSessionCurrentUser(req)));
        model.put("dispositivos",dispositivos);
        
        double consumoUltimo = DispositivosManager.getInstance().dispUltimoConsumo().consumoParaIntervalo(ultimoIntervaloDeUso);
        model.put("consumo",consumoUltimo);
        return new ModelAndView(model,"/usuario/dispositivo.hbs");
    }
    public ModelAndView listarDispositivosAlta(Request req, Response res)
    {
        Map<String, List<DispositivoInteligente>> model = new HashMap<>();

        List<DispositivoInteligente> dispositivos = DispositivosManager.getInstance().getDispositivosParaAlta();

        model.put("dispositivos",dispositivos);

        return new ModelAndView(model,"/usuario/verDispositivosAlta.hbs");
    }
    public ModelAndView verAlta(Request req, Response res)
    {
        Map<String, String> model = new HashMap<>();
        String equipo = req.params("equipo");
        String detalle = req.params("detalle");
        String id = req.params("id");

        model.put("equipo",equipo);
		model.put("detalle", detalle);
		model.put("id",id);
		
        return new ModelAndView(model,"usuario/altaConfirm.hbs");
    }
    public ModelAndView alta(Request req, Response res)
    {	
    	String id = req.params("id");

        DispositivosManager.getInstance().agregarDispositivoACliente(ClienteManager.getInstance().getIdDelClientePorUsuario(RequestUtil.getSessionCurrentUser(req)), Long.parseLong(id));
        res.redirect("/usuario");
        return null;
    }

    public ModelAndView consumoUltimoPeriodo(Request req, Response res){

        Map<String, Double> model = new HashMap<>();
        String id = req.params("id");
        Long idd = Long.valueOf(id).longValue();
        model.put("consumoUltimoPeriodo",DispositivosManager.getInstance().getConsumoUltimoPeriodo((idd)));

        return new ModelAndView(model,"/usuario/consumoUltimoPeriodo.hbs");
    }

    public ModelAndView verBajar(Request req, Response res)
    {
        Map<String, DispositivoInteligente> model = new HashMap<>();
        String id = req.params("id");

        DispositivoInteligente disp = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));
        model.put("dispositivo", disp);
        return new ModelAndView(model,"usuario/bajar.hbs");
    }
    public ModelAndView bajar(Request req, Response res)
    {	
    	
    	String id = req.params("id");
        DispositivoInteligente disp = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));

        DispositivosManager.getInstance().borrarDispositivoInteligene(disp);

        res.redirect("/usuario");
        return new ModelAndView(null,"usuario/bajar.hbs");
    }

}
