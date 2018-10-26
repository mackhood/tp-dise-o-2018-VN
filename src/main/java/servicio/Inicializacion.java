package servicio;

import dominio.manager.ClienteManager;

public class Inicializacion {

    private static Inicializacion instance = new Inicializacion();

    public Inicializacion() {

    }

    // Cambios para poder usar mock y spy sin necesidad de llamar al verdadero repo
    // O usar un archivo json de prueba.

    public static Inicializacion getInstance() {
        return instance;
    }
    public void init(){
        CargarAdministradores.getInstance().persistirAdministradores();
        //CargarTransformadores.getInstance().persistirTransformadores();
        CargarTransformadores.getInstance().persistirNuevoTransformador();
        ClienteManager.getInstance().persistirClienteDePrueba();
    }

}
