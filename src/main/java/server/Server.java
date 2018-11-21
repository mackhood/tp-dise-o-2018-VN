package server;

import servicio.ServicioDeInicializacion;
import servicio.ServicioDeInicializacion;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		ServicioDeInicializacion.init();
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
