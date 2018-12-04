package controllers;

import dominio.manager.ClienteManager;
import dominio.usuario.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ArrayUtils.toArray;

public class AdminController {





    public ModelAndView show(Request req, Response res)
    {
        return new ModelAndView(null,"home/admin.hbs");


    }

    public ModelAndView listarHogares(Request req, Response res)
    {
        Map<String, List<Cliente>> model = new HashMap<>();

        List<Cliente> hogares = ClienteManager.getInstance().getClientesDeLaBD();
      //  List<Double> consumos = hogares.toList().forEach(cliente -> cliente.consumoEnergeticoTotal() );
        model.put("hogares",hogares);
      //  model.put("consumos",consumos);
        return new ModelAndView(model,"/admin/verHogares.hbs");
    }





}





