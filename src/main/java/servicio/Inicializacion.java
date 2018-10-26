package servicio;

import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;

public class Inicializacion {

    private static Inicializacion instance = new Inicializacion();

    public Inicializacion() {

    }

    // Cambios para poder usar mock y spy sin necesidad de llamar al verdadero repo
    // O usar un archivo json de prueba.

    public static Inicializacion getInstance() {
        return instance;
    }
    public static void init(){
        CargarAdministradores.getInstance().persistirAdministradores();
        //CargarTransformadores.getInstance().persistirTransformadores();
        CargarTransformadores.getInstance().persistirNuevoTransformador();
        DispositivosManager.getInstance().persistirDispositivosDelRepositorio();
        ClienteManager.getInstance().persistirClienteDePrueba();
    }

}
