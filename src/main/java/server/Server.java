package server;

import servicio.Inicializacion;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Inicializacion.getInstance().init();
		Spark.port(9005);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
