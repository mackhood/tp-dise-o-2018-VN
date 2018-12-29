package utils;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
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
						&& (!Router.isAdminRoute(req.pathInfo()) && !Router.isPublic(req.pathInfo()))) {
					Spark.halt(401, "No tiene permisos para acceder a la ruta especificada");
				}

				else if (authenticated && role.equals("user")
						&& (!Router.isUserRoute(req.pathInfo()) && !Router.isPublic(req.pathInfo()))) {
					Spark.halt(401, "No tiene permisos para acceder a la ruta especificada");
				}
			}
		};
	}

}