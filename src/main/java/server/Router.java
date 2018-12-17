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
		Spark.post("/usuario/realizarRecomendacion", usuarioController::realizarRecomendacion, engine);

		Spark.get("/usuario/consultaConsumoPeriodo", usuarioController :: showConsumoPeriodo, engine);
		Spark.get("/usuario/consumoPeriodo", usuarioController :: consumoPeriodo, engine);

		//Spark.get("/usuario/consumoUltimoPeriodo", usuarioController::showConsumoUltimoPeriodo, engine);

		Spark.get("/usuario/verDispositivosAlta", dispositivoController::listarDispositivosAlta,engine);
		Spark.get("/usuario/altaConfirm/:id",dispositivoController::verAlta, engine);
		Spark.post("/usuario/altaConfirm", dispositivoController::alta,engine);

		Spark.get("/usuario/dispositivo", dispositivoController::listar, engine);
		Spark.get("/usuario/dispositivo/:id", dispositivoController::consumoUltimoPeriodo, engine);
        Spark.post("/usuario/dispositivo/modificar", dispositivoController::modificar, engine);
		Spark.get("/usuario/dispositivo/bajar/:id",dispositivoController::verBajar, engine);
        Spark.post("/usuario/dispositivo/bajar",dispositivoController::bajar,engine);
        Spark.get("/admin/hogares", adminController::seleccionarCantidad,engine);
        Spark.post("/admin/hogares/:cant", adminController::verHogares,engine);
        Spark.get("/admin/hogares/searchresults",adminController::busquedaHogar,engine);
		Spark.get("/admin/hogares/consumo/:id",adminController::verConsumos,engine);
		Spark.get("/admin/reportes", adminController::verReportes,engine);
		Spark.post("/admin/reportes/ingresarDatos", adminController::ingresarDatos,engine);
		Spark.post("/admin/reportes/filter/hogares", adminController::seleccionHogar,engine);
		Spark.get("/admin/reportes/noResultsFound", adminController::noResultsFound,engine);
		Spark.get("/admin/reportes/hogares/:id", adminController::seleccionIntervalo,engine);
		Spark.get("/admin/reportes/noFeature", adminController::noFeatureHere, engine);
		Spark.post("/admin/reportes/hogares/:id/results", adminController::resultadosReporte,engine);
		Spark.get("/admin/dispositivosAlta",adminController::verAltaDispositivos,engine);
		Spark.get("/admin/dispositivosAlta/:equipo/:detalle", adminController::confirmarAlta,engine);
		Spark.post("/admin/dispositivosAlta/:equipo/:detalle/confirmed",adminController::altaConfirmada,engine);
		Spark.get("/principal",adminController::altaConfirmada,engine);






	}

}