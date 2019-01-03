package utils;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Arrays;

import server.Router;

public class SessionHandler {

	public static Filter allowed() {

		return new Filter() {
			@Override
			public void handle(Request req, Response res) {
				boolean authenticated = req.session().attribute("currentUser") != null;
				String role = req.session().attribute("role");

				if (!authenticated && !Router.isPublic(req.pathInfo())) {
					Spark.halt(401, "No tiene permisos para acceder a la ruta especificada");
				}

				else if (authenticated && role.equals("admin")
						&& (!Arrays.asList(req.pathInfo().split("/")).contains("admin")) && !Router.isPublic(req.pathInfo())) {
					Spark.halt(401, "No tiene permisos para acceder a la ruta especificada");
				}

				else if (authenticated && role.equals("user")
						&& (!Arrays.asList(req.pathInfo().split("/")).contains("usuario")) && !Router.isPublic(req.pathInfo())) {
					Spark.halt(401, "No tiene permisos para acceder a la ruta especificada");
				}
			}
		};
	}

}