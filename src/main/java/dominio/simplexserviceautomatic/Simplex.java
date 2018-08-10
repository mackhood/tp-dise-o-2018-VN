package dominio.simplexserviceautomatic;

import dominio.dispositivo.Dispositivo;
import dominio.repositories.Repositorios;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Simplex {
    double consumoMaximo = 612;
    double[] horasMaximasDispositivo;
    double resultadoFuncionEconomica;

    public double[] getHorasMaximasDispositivo() {
        return horasMaximasDispositivo;
    }

    public double getResultadoFuncionEconomica() {
        return resultadoFuncionEconomica;
    }

    public void execute(List<Dispositivo> dispositivosDelCliente)
    {
        SimplexSolver solver = new SimplexSolver();

        LinearObjectiveFunction funcion = new LinearObjectiveFunction(Repositorios.dispositivos.coeficientesVariables(dispositivosDelCliente), 0);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

        constraints.add(new LinearConstraint(Repositorios.dispositivos.coeficientesDeConsumoKwh(dispositivosDelCliente),Relationship.GEQ, consumoMaximo));
        for(int i=0; i< dispositivosDelCliente.size(); i++) {
            constraints.add(new LinearConstraint(Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(dispositivosDelCliente.get(i)), Relationship.GEQ,
                    Repositorios.dispositivosMinmax.restriccionMinima(dispositivosDelCliente.get(i))));
            constraints.add(new LinearConstraint(Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(dispositivosDelCliente.get(i)), Relationship.LEQ,
                    Repositorios.dispositivosMinmax.restriccionMaxima(dispositivosDelCliente.get(i))));
        }
        PointValuePair solution = solver.optimize(new MaxIter(100), funcion, new LinearConstraintSet(constraints), GoalType.MAXIMIZE ,new NonNegativeConstraint(true));
        horasMaximasDispositivo = solution.getPoint();
        resultadoFuncionEconomica = funcion.value(horasMaximasDispositivo);
        this.asignarHorasMaximaPorDispositivo(dispositivosDelCliente);
    }
    public void asignarHorasMaximaPorDispositivo(List<Dispositivo> dispositivosDelCliente)
    {
        for (int i=0; i< dispositivosDelCliente.size();i++)
        {
            dispositivosDelCliente.get(i).setHorasMaximaPorConsumo(this.horasMaximasDispositivo[i]);
        }
    }

}
