package controllers;

import dominio.dispositivo.DispositivoInteligente;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
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

public class DispositivoController extends AbstractPersistenceTest implements WithGlobalEntityManager {


    public ModelAndView listar(Request req, Response res) {
        Map<String, List<DispositivoInteligente>> model = new HashMap<>();

        List<DispositivoInteligente> dispositivos = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario(RequestUtil.getSessionCurrentUser(req)).getDispositivosInteligentes();

        //List<DispositivoInteligente> dispositivos = ClienteManager.getInstance().getDispositivosInteligentesDelClienteDeLaBD(RequestUtil.getSessionCurrentUser(req));
        model.put("dispositivos", dispositivos);

        return new ModelAndView(model, "/usuario/dispositivo.hbs");
    }

    public ModelAndView listarDispositivosAlta(Request req, Response res) {
        Map<String, List<DispositivoInteligente>> model = new HashMap<>();

        List<DispositivoInteligente> dispositivos = DispositivosManager.getInstance().getDispositivosInteligentesDeLaBD();

        model.put("dispositivos", dispositivos);

        return new ModelAndView(model, "/usuario/verDispositivosAlta.hbs");
    }

    public ModelAndView verAlta(Request req, Response res) {
        Map<String, DispositivoInteligente> model = new HashMap<>();
        String idDispositivo = req.params("id");

        DispositivoInteligente dispositivoInteligente = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(idDispositivo));
        model.put("dispositivoInteligente", dispositivoInteligente);
        req.session().attribute("idDispositivo", idDispositivo);

        return new ModelAndView(model, "usuario/altaConfirm.hbs");
    }

    public ModelAndView alta(Request req, Response res) {
        DispositivoInteligente dispositivoInteligente = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(req.session().attribute("idDispositivo")));
        req.session().removeAttribute("idDispositivo");

        Cliente cliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario(RequestUtil.getSessionCurrentUser(req));

        withTransaction(() -> {
            cliente.agregarDispositivoInteligente(dispositivoInteligente);
            entityManager().persist(cliente);
            entityManager().getTransaction().commit();
        });

        res.redirect("/usuario");
        return new ModelAndView(null, "/usuario/altaConfirm.hbs");
    }

    public ModelAndView verModificar(Request req, Response res) {
        Map<String, DispositivoInteligente> model = new HashMap<>();
        String id = req.params("id");

        DispositivoInteligente disp = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));
        model.put("dispositivo", disp);
        req.session().attribute("idDispositivo", id);
        return new ModelAndView(model, "usuario/modificar.hbs");
    }


    public ModelAndView agregarDispositivoInteligenteAdmin(Request req, Response res) {


        String nombre = req.queryParams("nombre");
        String equipoConcreto = req.queryParams("equipoConcreto");
        String consumoEstimadoPorHora = req.queryParams("consumoEstimadoPorHora");
        String id = req.session().attribute("idDispositivo");
        DispositivoInteligente disp = new DispositivoInteligente();

        disp.setNombre(nombre);
        disp.setEquipoConcreto(equipoConcreto);
        disp.setConsumoEstimadoPorHora(Double.parseDouble(consumoEstimadoPorHora));
        disp.setId(Long.valueOf(id));
        DispositivosManager.getInstance().persistirDispositivoInteligente(disp);


        return new ModelAndView(null, "admin/agregarDispositivoInteligente.hbs");
    }


    public ModelAndView modificar(Request req, Response res) {

        String nombre = req.queryParams("nombre");
        String equipoConcreto = req.queryParams("equipoConcreto");
        String consumoEstimadoPorHora = req.queryParams("consumoEstimadoPorHora");
        String id = req.session().attribute("idDispositivo");
        req.session().removeAttribute("idDispositivo");
        DispositivoInteligente disp = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));


        withTransaction(() -> {
            disp.setNombre(nombre);
            disp.setEquipoConcreto(equipoConcreto);
            disp.setConsumoEstimadoPorHora(Double.parseDouble(consumoEstimadoPorHora));
            entityManager().persist(disp);
            entityManager().getTransaction().commit();
        });

        //res.redirect("/usuario");
        return new ModelAndView(null, "usuario/modificar.hbs");
    }

    public ModelAndView verBajar(Request req, Response res) {
        Map<String, DispositivoInteligente> model = new HashMap<>();
        String id = req.params("id");

        DispositivoInteligente disp = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));
        model.put("dispositivo", disp);
        req.session().attribute("idDispositivo", id);
        return new ModelAndView(model, "usuario/bajar.hbs");
    }

    public ModelAndView bajar(Request req, Response res) {
        String id = req.session().attribute("idDispositivo");
        req.session().removeAttribute("idDispositivo");
        DispositivoInteligente disp = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(Long.parseLong(id));

        //Long idCliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario(req.session().attribute("currentUser")).getId();

        withTransaction(() -> {

            //entityManager().createQuery("delete from Cliente_dispositivointeligente c where cliente_idCliente='"+idCliente+"' and dispositivosInteligentes_idDispositivo='"+id+"'");
            entityManager().remove(disp);
            entityManager().getTransaction().commit();
        });
        res.redirect("/usuario");
        return new ModelAndView(null, "usuario/bajar.hbs");
    }

}
