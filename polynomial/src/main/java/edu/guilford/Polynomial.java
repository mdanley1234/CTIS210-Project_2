package edu.guilford;

import java.util.ArrayList;

public class Polynomial implements Comparable<Polynomial> {
    private ArrayList<Double> coefficients;
    private int degree;
    
    // Constructors
    // public Polynomial() {
    //     coefficients = new ArrayList<Double>();
    //     degree = -1;
    // }

    // New Random Constructor (Degree: [3,10] | Coefficients [-5,5])
    public Polynomial() {
        this.degree = 3 + (int)(Math.random() * 8); // Random degree between 3 and 10
        this.coefficients = new ArrayList<Double>();
        for (int i = 0; i <= degree; i++) {
            this.coefficients.add(-5 + Math.random() * 10); // Random coefficient between -5 and 5
        }
    }

    public Polynomial(ArrayList<Double> coefficients) {
        this.coefficients = parseCoefficients(coefficients);
        degree = this.coefficients.size() - 1;
    }

    public Polynomial(int degree) {
        this.degree = degree;
        coefficients = new ArrayList<Double>();
        for (int i = 0; i <= degree; i++) {
            coefficients.add(-15 + Math.random() * 30);
        }
    }

    // Returns coefficients of the polynomial removed of leading zeros
    private ArrayList<Double> parseCoefficients(ArrayList<Double> data) {
        // Check for empty data
        if (data.isEmpty()) {
            return data;
        }

        // Return if data is valid (no leading zeros)
        if (data.get(data.size() - 1) != 0) {
            return data;
        }

        // Remove leading zero and recursively call parseCoefficients
        data.remove(data.size() - 1);
        return parseCoefficients(data);
    }

    // Getters
    public ArrayList<Double> getCoefficients() {
        return coefficients;
    }

    public int getDegree() {
        return degree;
    }

    public double getCoefficient(int index) {
        if (index < 0 || index > degree) {
            throw new IllegalArgumentException("Invalid index");
        }
        return coefficients.get(index);
    }

    // Setters
    public void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients = parseCoefficients(coefficients);
        degree = this.coefficients.size() - 1;
    }

    public void setCoefficient(int k, double coefficient) {
        // Check for invalid index
        if (k < 0) {
            throw new IllegalArgumentException("Invalid index");
        }

        // Expand polynomial if necessary (index > degree)
        while (k > coefficients.size() - 1) {
            coefficients.add(0.0);
        }

        // Set coefficient
        coefficients.set(k, coefficient);

        // Remove leading zeros
        coefficients = parseCoefficients(coefficients);
        degree = coefficients.size() - 1;
    }

    // Evaluate polynomial at x
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i <= degree; i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }

    // Add polynomials
    public Polynomial add(Polynomial p) {
        // Create new polynomial
        Polynomial result = new Polynomial();

        // Set degree of result
        result.degree = Math.max(degree, p.getDegree());

        // Add coefficients
        for (int i = 0; i <= result.degree; i++) {
            double sum = 0;
            if (i <= degree) {
                sum += coefficients.get(i);
            }
            if (i <= p.getDegree()) {
                sum += p.getCoefficient(i);
            }
            result.coefficients.add(sum);
        }

        // Remove leading zeros
        result.coefficients = parseCoefficients(result.coefficients);
        result.degree = result.coefficients.size() - 1;

        return result;
    }

    // Subtract polynomials
    public Polynomial subtract(Polynomial p) {
        // Create new polynomial
        Polynomial result = new Polynomial();

        // Set degree of result
        result.degree = Math.max(degree, p.getDegree());

        // Subtract coefficients
        for (int i = 0; i <= result.degree; i++) {
            double difference = 0;
            if (i <= degree) {
                difference += coefficients.get(i);
            }
            if (i <= p.getDegree()) {
                difference -= p.getCoefficient(i);
            }
            result.coefficients.add(difference);
        }

        // Remove leading zeros
        result.coefficients = parseCoefficients(result.coefficients);
        result.degree = result.coefficients.size() - 1;

        return result;
    }

    // Multiply polynomials
    public Polynomial multiply(Polynomial p) {
        // Create new polynomial
        Polynomial result = new Polynomial();

        // Set degree of result
        result.degree = degree + p.getDegree();

        // Multiply coefficients
        for (int i = 0; i <= result.degree; i++) {
            double product = 0;
            for (int j = 0; j <= i; j++) {
                if (j <= degree && i - j <= p.getDegree()) {
                    product += coefficients.get(j) * p.getCoefficient(i - j);
                }
            }
            result.coefficients.add(product);
        }

        // Remove leading zeros
        result.coefficients = parseCoefficients(result.coefficients);
        result.degree = result.coefficients.size() - 1;

        return result;
    }

    // Clear polynomial
    public void clear() {
        coefficients.clear();
        degree = -1;
    }

    // Print polynomial
    @Override
    public String toString() {
        if (degree == -1) {
            return "0";
        }

        String result = "";
        for (int i = 0; i <= degree; i++) {
            if (coefficients.get(i) != 0) {
                if (i != 0) {
                    result += " + ";
                }
                result += String.format("%.2f", coefficients.get(i));
                if (i != 0) {
                    result += "x^" + i;
                }
            }
        }
        return result;
    }

    @Override
    public int compareTo(Polynomial otherPolynomial) {
        // Check for degree comparison
        if (degree > otherPolynomial.getDegree()) {
            return 1;
        }
        else if (degree < otherPolynomial.getDegree()) {
            return -1;
        }

        // If degree equal, locate first differeing coefficient
        int checkDegree = degree;
        while (coefficients.get(checkDegree) == otherPolynomial.getCoefficient(checkDegree)) {
            checkDegree--;
            if (checkDegree == -1) {
                return 0; // If no coefficients differ
            }
        }

        // Compare coefficients at checkDegree point
        if (coefficients.get(checkDegree) > otherPolynomial.getCoefficient(checkDegree)) {
            return 1;
        }
        else {
            return -1;
        }

    }
}