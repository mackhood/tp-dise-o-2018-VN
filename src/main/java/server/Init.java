package server;

import spark.Spark;
import spark.debug.DebugScreen;
import spark.servlet.SparkApplication;
import server.Router;

public class Init implements SparkApplication {

	public void init() 
	{
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
