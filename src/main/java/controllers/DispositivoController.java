package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.manager.ClienteManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispositivoController extends AbstractPersistenceTest implements WithGlobalEntityManager {

    public static ModelAndView listar(Request req, Response res)
    {
        Map<String, List<DispositivoInteligente>> model = new HashMap<>();

        List<DispositivoInteligente> dispositivos = ClienteManager.getInstance().buscarClientePorUsuario(RequestUtil.getSessionCurrentUser(req)).getDispositivosInteligentes();

        model.put("dispositivos",dispositivos);

        return new ModelAndView(model,"/usuario/dispositivo.hbs");
    }
    public ModelAndView verModificar(Request req, Response res){
        Map<String, DispositivoInteligente> model = new HashMap<>();
        String id = req.params("id");

        DispositivoInteligente disp = DispositivosManager.getInstance().traerCiertoDispositivo(Long.parseLong(id));
        model.put("dispositivo", disp);
        return new ModelAndView(model, "usuario/modificar.hbs");
    }
    public ModelAndView modificar(Request req, Response res)
    {
        String id = req.queryParams("id");
        String nombre = req.queryParams("modifNombre");
        String equipoConcreto = req.queryParams("modifEquipoConcreto");
        String consumoEstimadoPorHora = req.queryParams("modifConsumoEstimadoPorHora");
        DispositivoInteligente disp = DispositivosManager.getInstance().traerCiertoDispositivo(Long.parseLong(id));

        withTransaction(()->{
            disp.setNombre(nombre);
            disp.setConsumoEstimadoPorHora(Double.parseDouble(consumoEstimadoPorHora));
            disp.setEquipoConcreto(equipoConcreto);
            entityManager().persist(disp);
        });

        //res.redirect("/");
        return new ModelAndView(null, "usuario/modificar.hbs");
        //return null;
    }

    public Void bajar(Request req, Response res)
    {
        String id = req.queryParams("id");
        DispositivoInteligente disp = DispositivosManager.getInstance().traerCiertoDispositivo(Long.parseLong(id));

        withTransaction(()->{
            entityManager().remove(disp);
        });
        res.redirect("/");
        return null;
    }



    /*
    public static ModelAndView crear(Request req, Response res)
    {

    }*/
}
