package server;

import java.util.HashSet;
import java.util.Set;

import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.BooleanHelper;
import utils.HandlebarsTemplateEngineBuilder;
import utils.SessionHandler;

public class Router {
	
	static Set<String> publicRoutes = new HashSet<String>();
	
	public static Boolean isPublic(String route)
	{
		return publicRoutes.contains(route);
	}
	
	private static void setPublicRoutes(Set<String> publicRoutes)
	{	
		publicRoutes.add("/");
		publicRoutes.add("/login");
		publicRoutes.add("/loginFailure");
		publicRoutes.add("/logout");
	}

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);
		
		Spark.before(SessionHandler.allowed());
		
		DispositivoController dispositivoController = new DispositivoController();
		UsuarioController usuarioController = new UsuarioController();
		AdminController adminController = new AdminController();

		Spark.get("/", HomeController::showLoginForm, engine);
		Spark.post("/login", LoginController::login);
		Spark.get("/loginFailure", LoginController::loginFailure, engine);
		Spark.post("/loginFailure", LoginController::loginFailure, engine);
		Spark.get("/logout", LoginController::logout);
		
		Spark.get("/admin", LoginController::adminHome,engine);
		Spark.get("/usuario", LoginController::userHome, engine);
		
		Spark.get("/usuario/recomendacionHogar", usuarioController::showConfirmacionRecomendacionHogar, engine);
		Spark.post("/usuario/recomendacion/success", usuarioController::realizarRecomendacion, engine);
		Spark.get("/usuario/consultaConsumoPeriodo", usuarioController :: showConsumoPeriodo, engine);
		Spark.post("/usuario/consumoPeriodo", usuarioController::consumoPeriodo, engine);
		Spark.get("/usuario/dispositivo", dispositivoController::listarDispositivosDeCliente, engine);
		Spark.get("/usuario/dispositivo/:id", dispositivoController::consumoUltimoPeriodo, engine);
        Spark.get("/usuario/dispositivo/bajar/:id",dispositivoController::verBajar,engine);
        Spark.post("/usuario/dispositivo/confirmarBaja/:id", dispositivoController::bajar,engine);
        Spark.get("/usuario/consumos",usuarioController::todasLasMediciones,engine);
        
        Spark.get("/admin/hogares", adminController::seleccionarCantidad,engine);
        Spark.post("/admin/hogares", adminController::verHogares,engine);
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
		Spark.get("/admin/dispositivosAlta/:equipo/:detalle/:kwh", adminController::confirmarAlta,engine);
		Spark.post("/admin/dispositivosAlta/:equipo/:detalle/:kwh/confirmed",adminController::altaConfirmada,engine);

	}

}