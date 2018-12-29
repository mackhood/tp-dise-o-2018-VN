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
	static Set<String> adminRoutes = new HashSet<String>();
	static Set<String> userRoutes = new HashSet<String>();
	
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
	
	public static void setAdminRoutes(Set<String> adminRoutes)
	{
		adminRoutes.add("/admin");
		adminRoutes.add("/admin/hogares");
		adminRoutes.add("/admin/hogares/:cant");
		adminRoutes.add("/admin/hogares/searchresults");
		adminRoutes.add("/admin/hogares/consumo/:id");
		adminRoutes.add("/admin/reportes");
		adminRoutes.add("/admin/reportes/ingresarDatos");
		adminRoutes.add("/admin/reportes/filter/hogares");
		adminRoutes.add("/admin/reportes/noResultsFound");
		adminRoutes.add("/admin/reportes/hogares/:id");
		adminRoutes.add("/admin/reportes/noFeature");
		adminRoutes.add("/admin/reportes/hogares/:id/results");
		adminRoutes.add("/admin/dispositivosAlta");
		adminRoutes.add("/admin/dispositivosAlta/:equipo/:detalle/:kwh");
		adminRoutes.add("/admin/dispositivosAlta/:equipo/:detalle/:kwh/confirmed");
	}
	
	public static void setUserRoutes(Set<String> userRoutes)
	{
		userRoutes.add("/usuario");
		userRoutes.add("/usuario/recomendacionHogar");
		userRoutes.add("/usuario/recomendacion/success");
		userRoutes.add("/usuario/consultaConsumoPeriodo");
		userRoutes.add("/usuario/consumoPeriodo");
		userRoutes.add("/usuario/verDispositivosAlta");
		userRoutes.add("/usuario/altaConfirm/:id/:equipo/:detalle");
		userRoutes.add("/usuario/altaConfirm/:id/:equipo/:detalle/confirmed");
		userRoutes.add("/usuario/dispositivo");
		userRoutes.add("/usuario/dispositivo/:id");
		userRoutes.add("/usuario/dispositivo/bajar/:id");
		userRoutes.add("/usuario/consumos");
		userRoutes.add("/usuario/dispositivo/confirmarBaja/:id");
	}
	
	public static Boolean isUserRoute(String route)
	{
		return userRoutes.contains(route);
	}
	
	public static Boolean isAdminRoute(String route)
	{
		return adminRoutes.contains(route);
	}
	
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
		Spark.staticFiles.location("/public");
		setPublicRoutes(publicRoutes);
		setUserRoutes(userRoutes);
		setAdminRoutes(adminRoutes);
		
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
		//Spark.get("/usuario/consumoUltimoPeriodo", usuarioController::showConsumoUltimoPeriodo, engine);
		Spark.get("/usuario/verDispositivosAlta", dispositivoController::listarDispositivosAlta,engine);
		Spark.get("/usuario/altaConfirm/:id/:equipo/:detalle",dispositivoController::verAlta, engine);
		Spark.post("/usuario/altaConfirm/:id/:equipo/:detalle/confirmed", dispositivoController::alta,engine);
		Spark.get("/usuario/dispositivo", dispositivoController::listarDispositivosDeCliente, engine);
		Spark.get("/usuario/dispositivo/:id", dispositivoController::consumoUltimoPeriodo, engine);
        Spark.get("/usuario/dispositivo/bajar/:id",dispositivoController::verBajar,engine);
        Spark.post("/usuario/dispositivo/confirmarBaja/:id", dispositivoController::bajar,engine);
        Spark.get("/usuario/consumos",usuarioController::todasLasMediciones,engine);
        
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
		Spark.get("/admin/dispositivosAlta/:equipo/:detalle/:kwh", adminController::confirmarAlta,engine);
		Spark.post("/admin/dispositivosAlta/:equipo/:detalle/:kwh/confirmed",adminController::altaConfirmada,engine);

	}

}