package utils;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import server.Router;

public class SessionHandler {
	
	public static Filter allowed(){

		    return new Filter() {
		    	@Override
		    	public void handle(Request req, Response res){
		    	boolean authenticated = req.session().attribute("currentUser") != null;
		    
			    if (!authenticated && !Router.isPublic(req.pathInfo())) {
			    	Spark.halt(401,"No tiene permisos para acceder a la ruta especificada");
			    }
	    	}
	    };
	}


}