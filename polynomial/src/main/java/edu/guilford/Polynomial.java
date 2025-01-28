package edu.guilford;

import java.util.ArrayList;

/**
 * The Polynomial class represents a mathematical polynomial with double coefficients.
 * It provides methods to evaluate the polynomial, add, subtract, multiply, and compare polynomials.
 * Additionally, it includes methods to manipulate and display the polynomial's coefficients.
 */
public class Polynomial implements Comparable<Polynomial> {
    private ArrayList<Double> coefficients;
    private int degree;

    /**
     * Default constructor that initializes a polynomial with random degree (between 3 and 10) 
     * and random coefficients (between -5 and 5).
     */
    public Polynomial() {
        this.degree = 3 + (int)(Math.random() * 8); // Random degree between 3 and 10
        this.coefficients = new ArrayList<Double>();
        for (int i = 0; i <= degree; i++) {
            this.coefficients.add(-5 + Math.random() * 10); // Random coefficient between -5 and 5
        }
    }

    /**
     * Constructor that initializes a polynomial with a given list of coefficients.
     * @param coefficients the coefficients of the polynomial.
     */
    public Polynomial(ArrayList<Double> coefficients) {
        this.coefficients = parseCoefficients(coefficients);
        degree = this.coefficients.size() - 1;
    }

    /**
     * Constructor that initializes a polynomial with a specific degree and random coefficients
     * between -15 and 15.
     * @param degree the degree of the polynomial.
     */
    public Polynomial(int degree) {
        this.degree = degree;
        coefficients = new ArrayList<Double>();
        for (int i = 0; i <= degree; i++) {
            coefficients.add(-15 + Math.random() * 30);
        }
    }

    /**
     * Removes leading zeros from the given list of coefficients.
     * @param data the list of coefficients to parse.
     * @return the parsed list with no leading zeros.
     */
    private ArrayList<Double> parseCoefficients(ArrayList<Double> data) {
        if (data.isEmpty()) {
            return data;
        }

        if (data.get(data.size() - 1) != 0) {
            return data;
        }

        data.remove(data.size() - 1);
        return parseCoefficients(data);
    }

    /**
     * Returns the coefficients of the polynomial.
     * @return the list of coefficients.
     */
    public ArrayList<Double> getCoefficients() {
        return coefficients;
    }

    /**
     * Returns the degree of the polynomial.
     * @return the degree of the polynomial.
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Returns the coefficient of a specific term in the polynomial.
     * @param index the index of the coefficient.
     * @return the coefficient at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    public double getCoefficient(int index) {
        if (index < 0 || index > degree) {
            throw new IllegalArgumentException("Invalid index");
        }
        return coefficients.get(index);
    }

    /**
     * Sets the coefficients of the polynomial to the provided list.
     * @param coefficients the new list of coefficients.
     */
    public void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients = parseCoefficients(coefficients);
        degree = this.coefficients.size() - 1;
    }

    /**
     * Sets the coefficient of a specific term in the polynomial.
     * @param k the index of the term.
     * @param coefficient the new coefficient to set.
     * @throws IllegalArgumentException if the index is invalid.
     */
    public void setCoefficient(int k, double coefficient) {
        if (k < 0) {
            throw new IllegalArgumentException("Invalid index");
        }

        while (k > coefficients.size() - 1) {
            coefficients.add(0.0);
        }

        coefficients.set(k, coefficient);
        coefficients = parseCoefficients(coefficients);
        degree = coefficients.size() - 1;
    }

    /**
     * Evaluates the polynomial at a given value of x.
     * @param x the value at which to evaluate the polynomial.
     * @return the result of the evaluation.
     */
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i <= degree; i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }

    /**
     * Adds another polynomial to this polynomial.
     * @param p the polynomial to add.
     * @return a new polynomial representing the sum.
     */
    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();
        result.degree = Math.max(degree, p.getDegree());

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

        result.coefficients = parseCoefficients(result.coefficients);
        result.degree = result.coefficients.size() - 1;

        return result;
    }

    /**
     * Subtracts another polynomial from this polynomial.
     * @param p the polynomial to subtract.
     * @return a new polynomial representing the difference.
     */
    public Polynomial subtract(Polynomial p) {
        Polynomial result = new Polynomial();
        result.degree = Math.max(degree, p.getDegree());

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

        result.coefficients = parseCoefficients(result.coefficients);
        result.degree = result.coefficients.size() - 1;

        return result;
    }

    /**
     * Multiplies this polynomial by another polynomial.
     * @param p the polynomial to multiply.
     * @return a new polynomial representing the product.
     */
    public Polynomial multiply(Polynomial p) {
        Polynomial result = new Polynomial();
        result.degree = degree + p.getDegree();

        for (int i = 0; i <= result.degree; i++) {
            double product = 0;
            for (int j = 0; j <= i; j++) {
                if (j <= degree && i - j <= p.getDegree()) {
                    product += coefficients.get(j) * p.getCoefficient(i - j);
                }
            }
            result.coefficients.add(product);
        }

        result.coefficients = parseCoefficients(result.coefficients);
        result.degree = result.coefficients.size() - 1;

        return result;
    }

    /**
     * Clears the polynomial, resetting the coefficients and degree.
     */
    public void clear() {
        coefficients.clear();
        degree = -1;
    }

    /**
     * Returns a string representation of the polynomial.
     * @return the string representation of the polynomial.
     */
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

    /**
     * Compares this polynomial to another polynomial.
     * @param otherPolynomial the polynomial to compare to.
     * @return 1 if this polynomial is greater, -1 if less, and 0 if equal.
     */
    @Override
    public int compareTo(Polynomial otherPolynomial) {
        if (degree > otherPolynomial.getDegree()) {
            return 1;
        }
        else if (degree < otherPolynomial.getDegree()) {
            return -1;
        }

        int checkDegree = degree;
        while (coefficients.get(checkDegree) == otherPolynomial.getCoefficient(checkDegree)) {
            checkDegree--;
            if (checkDegree == -1) {
                return 0;
            }
        }

        if (coefficients.get(checkDegree) > otherPolynomial.getCoefficient(checkDegree)) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
