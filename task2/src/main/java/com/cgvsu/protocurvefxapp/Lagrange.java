package com.cgvsu.protocurvefxapp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Lagrange {

    public static Function<Double, Double> createBasicPolynomial(double[] xValues, int i) {
        Function<Double, Double> basicPolynomial = (x) -> {
            double divider = 1;
            double result = 1;
            for (int j = 0; j < xValues.length; j++) {
                if (j != i) {
                    result *= x - xValues[j];
                    divider *= xValues[i] - xValues[j];
                }
            }
            return result / divider;
        };
        return basicPolynomial;
    }

    public static Function<Double, Double> createLagrangePolynomial(double[] xValues, double[] yValues) {
        List<Function<Double, Double>> basicPolynomial = new ArrayList<>();
        for (int i = 0; i < xValues.length; i++) {
            basicPolynomial.add(createBasicPolynomial(xValues, i));
        }
        Function<Double, Double> lagrangePolinome = (x) -> {
            double result = 0;
            for (int i = 0; i < xValues.length; i++) {
                result += yValues[i] * basicPolynomial.get(i).apply(x);
            }
            return result;
        };
        return lagrangePolinome;
    }

}
