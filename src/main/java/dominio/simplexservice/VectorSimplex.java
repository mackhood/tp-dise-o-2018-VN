package dominio.simplexservice;

import dominio.dispositivo.Dispositivo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VectorSimplex {

    private List<Dispositivo> dispositivosDelCliente = new ArrayList<>();
    private double[] coefsRestriccion;

    public VectorSimplex(List<Dispositivo> dispositivosDelCliente) {
        this.dispositivosDelCliente = dispositivosDelCliente;
        coefsRestriccion = new double[dispositivosDelCliente.size()];
    }

    private void agregarCerosAlArray() {
        for (int i = 0; i < coefsRestriccion.length; i++) {
            coefsRestriccion[i] = 0;
        }
    }

    private void modificarElemArray(int index) {
        coefsRestriccion[index] = 1;
    }

    public double[] devolverCoeficientesDeFuncionEconomicaObjetivo() {

        //Tomando como ejemplo del TP2, asumimos que los coeficientes de la funcion economica y objetivo son todos 1
        // ya que aparece como MAX Z = X1 + X2 + X3 + ... + X8
        double[] arrayCoef = new double[coefsRestriccion.length];
        for (int i = 0; i < coefsRestriccion.length; i++)
            arrayCoef[i] = 1;

        return arrayCoef;
    }

    public double[] devolverCoeficientesDeConsumoKwhR1() {

        //Dependiendo de los dispositivos del cliente, en la primera restriccion cada variable tiene un coeficiente distinto
        //este vector de double lo necesita la funcion economica objetivo para su creacion
        //de tal manera que el vector de coeficientes corresponda con el mismo orden que tenga la lista de dispositivos del cliente
        //osea que el coeficiente del dispositivosDelCliente[0] corresponde con arrayCoef[0] y asi con cada uno de ellos.

        List<Double> listaCoeficientes = dispositivosDelCliente.stream().map(disp -> disp.getConsumoEstimadoPorHora())
                .collect(Collectors.toList());
        double[] arrayCoef = new double[listaCoeficientes.size()];
        for (int i = 0; i < listaCoeficientes.size(); i++)
            arrayCoef[i] = listaCoeficientes.get(i);

        return arrayCoef;
    }

    public double[] coefsResctriccionDeUnDispositivo(int i) {
        //tiene que devolver un vector del mismo tamanio de los dispositivos del cliente pero solo la posicion del dispositivo deseado debe tener coeficiente 1, y 0 todos los demas.
        //Si tenemos en cuenta el ejemplo del enunciado de R2: 1*X1 + 0*X2 + 0*X3 + ... + 0*X8>= 90  aca tendriamos que devolver un vector [1,0,0,0,0,0,0,0,0]
        this.agregarCerosAlArray();
        this.modificarElemArray(i);
        return coefsRestriccion;
    }
}
