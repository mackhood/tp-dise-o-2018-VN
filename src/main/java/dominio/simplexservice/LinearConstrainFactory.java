package dominio.simplexservice;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

public class LinearConstrainFactory {
    public LinearConstrainFactory() {
    }

    LinearConstraint createLinearConstrain(double[] coefficients, Relationship relationship, int value) {
        return new LinearConstraint(coefficients,
                relationship, value);
    }
}