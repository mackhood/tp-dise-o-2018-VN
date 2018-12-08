package server;

import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.BooleanHelper;
import utils.HandlebarsTemplateEngineBuilder;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
		Spark.staticFiles.location("/");
		
		DispositivoController dispositivoController = new DispositivoController();
		UsuarioController usuarioController = new UsuarioController();
		AdminController adminController = new AdminController();

		Spark.get("/", HomeController::showLoginForm, engine);
		Spark.post("/principal",LoginController::login,engine);

		Spark.get("/login", LoginController::login, engine);
		Spark.get("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/loginFailure", LoginController::loginFailure, engine);


		Spark.post("/login", LoginController::logout,engine);
		Spark.get("/login", LoginController::logout, engine);


		Spark.get("/usuario", usuarioController::show, engine);

		Spark.get("/usuario/recomendacionHogar", usuarioController::showConfirmacionRecomendacionHogar, engine);
		Spark.get("/usuario/realizarRecomendacion", usuarioController::realizarRecomendacion, engine);

		Spark.get("/usuario/consultaConsumoPeriodo", usuarioController :: showConsumoPeriodo, engine);
		Spark.post("/usuario/consumoPeriodo", usuarioController :: consumoPeriodo, engine);


		Spark.get("/usuario/verDispositivosAlta", dispositivoController::listarDispositivosAlta,engine);
		Spark.get("/usuario/altaConfirm/:id",dispositivoController::verAlta, engine);
		Spark.post("/usuario/altaConfirm", dispositivoController::alta,engine);

		Spark.get("/usuario/dispositivo", dispositivoController::listar, engine);
		Spark.get("/usuario/dispositivo/:id", dispositivoController::verModificar, engine);
        Spark.post("/usuario/dispositivo/modificar", dispositivoController::modificar, engine);
		Spark.get("/usuario/dispositivo/bajar/:id",dispositivoController::verBajar, engine);
        Spark.post("/usuario/dispositivo/bajar",dispositivoController::bajar,engine);
		Spark.get("/admin/hogares", adminController::verHogares,engine);

		Spark.get("/admin/hogares/consumo/:id",adminController::verConsumos,engine);
		Spark.get("/admin/reportes", adminController::verReportes,engine);
		Spark.post("/admin/reportes/ingresarDatos", adminController::ingresarDatos,engine);
		Spark.post("/admin/reportes/reporteHogares/:id", adminController::verReporteSeleccionado,engine );

		//Spark.get("/usuario/dispositivo/alta", dispositivoController::verAlta,engine);
        //Spark.put("/agregarDispositivo", DispositivoController::crear, engine);
		//Spark.get("/dispositivo", DispositivoController::show, engine);
		//Spark.get("/proyectos", proyectosController::listar, engine);
		//Spark.get("/proyectos/new", proyectosController::nuevo, engine);
		//Spark.get("/proyectos/:id", proyectosController::mostrar, engine);
		//Spark.post("/proyectos", proyectosController::crear);





	}

}