package servicio;

import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;

public class ServicioDeInicializacion {

    private static ServicioDeInicializacion instance = new ServicioDeInicializacion();

    public ServicioDeInicializacion() {

    }

    // Cambios para poder usar mock y spy sin necesidad de llamar al verdadero repo
    // O usar un archivo json de prueba.

    public static ServicioDeInicializacion getInstance() {
        return instance;
    }
    public static void init(){
        ServicioDeCargaDeAdministradores.getInstance().persistirAdministradores();
        ServicioDeCargaDeCargaDeTransformadores.getInstance().persistirTransformadores();
        ServicioDeCargaDeCargaDeTransformadores.getInstance().persistirNuevoTransformador();
        DispositivosManager.getInstance().persistirDispositivosDelRepositorio();
        ClienteManager.getInstance().persistirClienteDePrueba();
    }

}
