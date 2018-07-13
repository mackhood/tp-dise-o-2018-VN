package Clases.Simplex;

import Clases.Dispositivo.Dispositivo;
import Clases.repositories.RepositoriosTP2;
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

    public void todosCerosArray(double[] coefsRestriccion)
    {
        for (int i=0; i< coefsRestriccion.length;i++)
        {
            coefsRestriccion[i] = 0;
        }
    }
    public void modElemDelArray(double[] coefsRestriccion, int index)
    {
        coefsRestriccion[index] = 1;
    }
    public double[] getCoeficientesVariables(List<Dispositivo> dispositivosCliente)
    {
        return new double[dispositivosCliente.size()];
    }
    public void execute(List<Dispositivo> dispositivosDelCliente)
    {
        SimplexSolver solver = new SimplexSolver();
        double[] coefsRestriccion = new double[dispositivosDelCliente.size()];

        LinearObjectiveFunction funcion = new LinearObjectiveFunction(this.getCoeficientesVariables(dispositivosDelCliente), 0);
        Collection<LinearConstraint> constraints = new ArrayList<>();

        constraints.add(new LinearConstraint(RepositoriosTP2.dispositivosConsumoKWh.coeficientesDeConsumoKwh(dispositivosDelCliente),Relationship.LEQ, consumoMaximo));

        for(int i=0; i< dispositivosDelCliente.size(); i++) {
            this.todosCerosArray(coefsRestriccion);
            this.modElemDelArray(coefsRestriccion,i);
            constraints.add(new LinearConstraint(coefsRestriccion, Relationship.GEQ,
                    RepositoriosTP2.dispositivosMinMax.restriccionMinima(dispositivosDelCliente.get(i))));
            constraints.add(new LinearConstraint(coefsRestriccion, Relationship.LEQ,
                    RepositoriosTP2.dispositivosMinMax.restriccionMaxima(dispositivosDelCliente.get(i))));

        }

        PointValuePair solution = solver.optimize(new MaxIter(200), funcion, new LinearConstraintSet(constraints), GoalType.MAXIMIZE ,new NonNegativeConstraint(true));
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
