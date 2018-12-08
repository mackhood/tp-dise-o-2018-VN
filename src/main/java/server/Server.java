package server;

import servicio.ServicioDeInicializacion;
import servicio.ServicioDeInicializacion;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		//ServicioDeInicializacion.init();
		Spark.port(getSparkAssignedPort());
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

	static int getSparkAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("9001") != null) {
			return Integer.parseInt(processBuilder.environment().get("9001"));
		}
		return 9001; //return default port if saprk-port isn't set (i.e. on localhost)
	}




}
