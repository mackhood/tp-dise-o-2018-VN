package dominio.simplexservice;

import dominio.dispositivo.Dispositivo;
import dominio.repositories.RepositorioDispositivo;
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
    List<Dispositivo> dispositivosDelCliente = new ArrayList<>();
    VectorSimplex vectorSimplex;
    SimplexSolver simplexSolver;
    PointValuePair solucion;


    public SimplexBuilder(Cliente unCliente) {

        this.dispositivosDelCliente = unCliente.getTodosLosDispositivos();
        vectorSimplex = new VectorSimplex(dispositivosDelCliente);
        System.out.println("HOLA4");

    }


    public PointValuePair solverBuild(){
        //vectorSimplex = new VectorSimplex(dispositivosDelCliente);
        SimplexSolver simplexSolver = new SimplexSolver();
        LinearObjectiveFunction funcion = this.funcionEconomicaBuild();
        System.out.println("HOLA3");


        return solucion = simplexSolver.optimize(new MaxIter(100), funcion, new LinearConstraintSet(this.restriccionesDeLosDispositivosBuild()), GoalType.MAXIMIZE, new NonNegativeConstraint(true));
    }
    public LinearObjectiveFunction funcionEconomicaBuild() {
        LinearObjectiveFunction funcion = new LinearObjectiveFunction(vectorSimplex.devolverCoeficientesDeFuncionEconomicaObjetivo(), 0);
        System.out.println("HOLA5");
        return funcion;
    }

    public Collection<LinearConstraint> restriccionesDeLosDispositivosBuild() {

        Collection<LinearConstraint> constraints = new ArrayList<>();
        //El primer parametro del constructor de la funcion debe ser un vector de tamanio de los dispositivos del cliente de todos de coeficiente 1 (Siguiendo el ejemplo de la pagina 1 del TP)
        constraints.add(linearConstrainFactory.createLinearConstrain(vectorSimplex.devolverCoeficientesDeConsumoKwhR1(), Relationship.LEQ, 612.0));
        System.out.println("HOLA6");
        System.out.println(dispositivosDelCliente.size());

        for (int i = 0; i < dispositivosDelCliente.size(); i++)
        {
            System.out.println(dispositivosDelCliente.get(i).getRestriccionMinima());

            constraints.add(linearConstrainFactory.createLinearConstrain(vectorSimplex.coefsResctriccionDeUnDispositivo(i), Relationship.GEQ, dispositivosDelCliente.get(i).getRestriccionMinima()));
            System.out.println("HOLA7");

            constraints.add(linearConstrainFactory.createLinearConstrain(vectorSimplex.coefsResctriccionDeUnDispositivo(i), Relationship.LEQ, dispositivosDelCliente.get(i).getRestriccionMaxima()));
            System.out.println("HOLA8");

        }
        return constraints;
    }
    public double[] getHorasMaximasDeConsumoPorDispositivo()
    {
        System.out.println("HOLA1");
        return solverBuild().getPoint();
    }
    public double getResultadoDeLaFuncionEconomica()
    {
        LinearObjectiveFunction funcion = this.funcionEconomicaBuild();
        System.out.println("HOLA2");
        return funcion.value(this.getHorasMaximasDeConsumoPorDispositivo());
    }
}
