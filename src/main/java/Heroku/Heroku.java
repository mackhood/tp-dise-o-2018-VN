package Heroku;
import static spark.Spark.*;
public class Heroku {

        public static void main(String[] args) {
            port(getHerokuAssignedPort());
            get("home/home.hbs", (req, res) -> "Hello Heroku World");
        }

        static int getHerokuAssignedPort() {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (processBuilder.environment().get("9000") != null) {
                return Integer.parseInt(processBuilder.environment().get("9000"));
            }
            return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
        }

    }





















