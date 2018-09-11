package dominio.simplexservice;

import dominio.dispositivo.Dispositivo;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.List;

public class Recomendacion {
    double resultadoDeLaFuncionEconomica;
    double[] horasMaximaDeConsumoPorDispositivo;
    public Recomendacion(List<Dispositivo> dispositivosDelCliente)
    {
        SimplexBuilder simplexBuilder = new SimplexBuilder(dispositivosDelCliente);
        SimplexSolver simplexSolver = new SimplexSolver();
        LinearObjectiveFunction funcion = simplexBuilder.funcionEconomicaBuild();

        PointValuePair solucion = simplexSolver.optimize(new MaxIter(100), funcion, new LinearConstraintSet(simplexBuilder.restriccionesDeLosDispositivosBuild()),
                GoalType.MAXIMIZE, new NonNegativeConstraint(true));

        this.horasMaximaDeConsumoPorDispositivo = solucion.getPoint();
        this.resultadoDeLaFuncionEconomica = funcion.value(horasMaximaDeConsumoPorDispositivo);

    }

    public double getResultadoDeLaFuncionEconomica() {
        return resultadoDeLaFuncionEconomica;
    }

    public double[] getHorasMaximaDeConsumoPorDispositivo() {
        return horasMaximaDeConsumoPorDispositivo;
    }
}
