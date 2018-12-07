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
		Spark.get("/login", LoginController::login, engine);
		Spark.get("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/principal",LoginController::login,engine);
		Spark.post("/login", LoginController::logout,engine);
		Spark.get("/login", LoginController::logout, engine);
		Spark.get("/usuario", usuarioController::show, engine);
		Spark.get("/usuario/verDispositivosAlta", dispositivoController::listarDispositivosAlta,engine);
		Spark.get("/usuario/altaConfirm/:id",dispositivoController::verAlta, engine);
		Spark.post("/usuario/altaConfirm", dispositivoController::alta,engine);

		Spark.get("/usuario/dispositivo", dispositivoController::listar, engine);
	//	Spark.get("/usuario/dispositivo/:id", dispositivoController::verModificar, engine);
    //    Spark.post("/usuario/dispositivo/modificar", dispositivoController::modificar, engine);
	//	Spark.get("/usuario/dispositivo/bajar/:id",dispositivoController::verBajar, engine);
     //   Spark.post("/usuario/dispositivo/bajar",dispositivoController::bajar,engine);
		Spark.get("/admin/verHogares", adminController::listarHogares,engine);
		Spark.get("/admin/verDispositivosAltaAdmin", dispositivoController::alta,engine);
		Spark.post("/admin/agregarDispositivoInteligente", dispositivoController::agregarDispositivoInteligenteAdmin, engine);
		Spark.post("/admin/agregarDispositivoEstandar", dispositivoController::agregarDispositivoInteligenteAdmin, engine);
		Spark.get("/admin/verHogares", adminController::listarHogares,engine);







	}

}