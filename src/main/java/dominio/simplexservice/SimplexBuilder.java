package dominio.simplexservice;

import dominio.dispositivo.Dispositivo;
import dominio.usuario.Cliente;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimplexBuilder {

    private final LinearConstrainFactory linearConstrainFactory = new LinearConstrainFactory();
    private List<Dispositivo> dispositivosDelCliente = new ArrayList<>();
    private VectorSimplex vectorSimplex;
    private SimplexSolver simplexSolver;
    private PointValuePair solucion;


    public SimplexBuilder(Cliente unCliente) {

        this.dispositivosDelCliente = unCliente.getTodosLosDispositivos();
        vectorSimplex = new VectorSimplex(dispositivosDelCliente);

    }


    private PointValuePair solverBuild() {
        SimplexSolver simplexSolver = new SimplexSolver();
        LinearObjectiveFunction funcion = this.funcionEconomicaBuild();

        return solucion = simplexSolver.optimize(new MaxIter(100), funcion, new LinearConstraintSet(this.restriccionesDeLosDispositivosBuild()), GoalType.MAXIMIZE, new NonNegativeConstraint(true));
    }

    private LinearObjectiveFunction funcionEconomicaBuild() {
        LinearObjectiveFunction funcion = new LinearObjectiveFunction(vectorSimplex.devolverCoeficientesDeFuncionEconomicaObjetivo(), 0);
        return funcion;
    }

    private Collection<LinearConstraint> restriccionesDeLosDispositivosBuild() {

        Collection<LinearConstraint> constraints = new ArrayList<>();
        //El primer parametro del constructor de la funcion debe ser un vector de tamanio de los dispositivos del cliente de todos de coeficiente 1 (Siguiendo el ejemplo de la pagina 1 del TP)
        constraints.add(linearConstrainFactory.createLinearConstrain(vectorSimplex.devolverCoeficientesDeConsumoKwhR1(), Relationship.LEQ, 612.0));

        for (int i = 0; i < dispositivosDelCliente.size(); i++) {
            constraints.add(linearConstrainFactory.createLinearConstrain(vectorSimplex.coefsResctriccionDeUnDispositivo(i), Relationship.GEQ, dispositivosDelCliente.get(i).getRestriccionMinima()));

            constraints.add(linearConstrainFactory.createLinearConstrain(vectorSimplex.coefsResctriccionDeUnDispositivo(i), Relationship.LEQ, dispositivosDelCliente.get(i).getRestriccionMaxima()));

        }
        return constraints;
    }

    public double[] getHorasMaximasDeConsumoPorDispositivo() {
        return this.solverBuild().getPoint();
    }

    public double getResultadoDeLaFuncionEconomica() {
        LinearObjectiveFunction funcion = this.funcionEconomicaBuild();
        return funcion.value(this.getHorasMaximasDeConsumoPorDispositivo());
    }
}
