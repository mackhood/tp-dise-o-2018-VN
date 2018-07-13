package Clases.Simplex;

import Clases.Dispositivo.Dispositivo;
import Clases.repositories.Repositorio;
import Clases.repositories.RepositorioDispMinMax;
import Clases.repositories.Repositorios;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Simplex {
    List<Dispositivo> dispositivos = new ArrayList<>();
    double consumoMaximo = 612;
    double[] horasMaximasDispositivo;
    double resultadoFuncionEconomica;

    public void agregarDispositivo(Dispositivo dispositivo)
    {
        dispositivos.add(dispositivo);
    }

    public double[] getHorasMaximasDispositivo() {
        return horasMaximasDispositivo;
    }

    public double getResultadoFuncionEconomica() {
        return resultadoFuncionEconomica;
    }

    public void execute()
    {
        SimplexSolver solver = new SimplexSolver();

        LinearObjectiveFunction funcion = new LinearObjectiveFunction(Repositorios.dispositivos.coeficientesVariables(dispositivos), 0);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

        constraints.add(new LinearConstraint(Repositorios.dispositivos.coeficientesDeConsumoKwh(dispositivos),Relationship.GEQ, consumoMaximo));
        for(int i=0; i< dispositivos.size(); i++) {
            constraints.add(new LinearConstraint(Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(dispositivos.get(i)), Relationship.GEQ,
                    Repositorios.dispositivosMinmax.restriccionMinima(dispositivos.get(i))));
            constraints.add(new LinearConstraint(Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(dispositivos.get(i)), Relationship.GEQ,
                    Repositorios.dispositivosMinmax.restriccionMinima(dispositivos.get(i))));
        }
        PointValuePair solution = solver.optimize(new MaxIter(100), funcion, new LinearConstraintSet(constraints), GoalType.MINIMIZE ,new NonNegativeConstraint(true));
        horasMaximasDispositivo = solution.getPoint();
        resultadoFuncionEconomica = funcion.value(horasMaximasDispositivo);
    }


}
