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
		
		//ProyectosController proyectosController = new ProyectosController();
		
		Spark.get("/", HomeController::home, engine);
		Spark.get("/login", LoginController::show, engine);
		Spark.post("/login", LoginController::login, engine);
		Spark.post("/logout", LoginController::logout, engine);
		Spark.get("/dispositivo", DispositivoController::listar, engine);
		Spark.get("/dispositivo/:id", DispositivoController::modificar, engine);
		//Spark.put("/agregarDispositivo", DispositivoController::crear, engine);
		//Spark.get("/dispositivo", DispositivoController::show, engine);
		//Spark.get("/proyectos", proyectosController::listar, engine);
		//Spark.get("/proyectos/new", proyectosController::nuevo, engine);
		//Spark.get("/proyectos/:id", proyectosController::mostrar, engine);
		//Spark.post("/proyectos", proyectosController::crear);





	}

}