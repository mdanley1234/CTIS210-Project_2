package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Polynomial> polynomials = new ArrayList<>();
        ArrayList<Integer> degrees = new ArrayList<>();
        ArrayList<Double> coefficients = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of random polynomials to generate: ");
        int numPolynomials = scanner.nextInt();
        scanner.close();

        for (int i = 0; i < numPolynomials; i++) {
            Polynomial poly = new Polynomial();
            polynomials.add(poly);
            System.out.println("Polynomial " + (i + 1) + ": " + poly.toString());
            degrees.add(poly.getDegree());
            coefficients.addAll(poly.getCoefficients());
        }

        int maxDegree = Collections.max(degrees);
        int minDegree = Collections.min(degrees);
        double maxCoefficient = Collections.max(coefficients);
        double minCoefficient = Collections.min(coefficients);

        System.out.println("Max Degree: " + maxDegree);
        System.out.println("Min Degree: " + minDegree);
        System.out.println("Max Coefficient: " + maxCoefficient);
        System.out.println("Min Coefficient: " + minCoefficient);
    }

    // public static void main(String[] args) {
    //     // Preset coefficient lists
    //     ArrayList<Double> c1 = new ArrayList<>(Arrays.asList(1.0, 2.0, 0.0, 4.0, 0.0));
    //     ArrayList<Double> c2 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 0.0));
    //     ArrayList<Double> c3 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 0.0)); // Copy c2

    //     // Create polynomials
    //     Polynomial p1 = new Polynomial(3); // Random 3rd degree polynomial
    //     Polynomial p2 = new Polynomial(3); // Random 3rd degree polynomial
    //     Polynomial p3 = new Polynomial(); // Empty polynomial
    //     Polynomial p4 = new Polynomial(); // Empty polynomial
    //     Polynomial p5 = new Polynomial(c1); // Pre-determined polynomial
    //     Polynomial p6 = new Polynomial(c2); // Pre-determined polynomial

    //     // Test toString Method
    //     System.out.println("Polynomial toString Test:");
    //     System.out.println("p1: " + p1.toString());
    //     System.out.println("p2: " + p2.toString());
    //     System.out.println("p3: " + p3.toString());
    //     System.out.println("p4: " + p4.toString());
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());

    //     // Test degree getter
    //     System.out.println("\nPolynomial Degree Getter Test:");
    //     System.out.println("p1 degree: " + p1.getDegree());
    //     System.out.println("p2 degree: " + p2.getDegree());
    //     System.out.println("p3 degree: " + p3.getDegree());
    //     System.out.println("p4 degree: " + p4.getDegree());
    //     System.out.println("p5 degree: " + p5.getDegree());
    //     System.out.println("p6 degree: " + p6.getDegree());

    //     // Test coefficients getter
    //     System.out.println("\nPolynomial Coefficients Getter Test:");
    //     System.out.println("p1 coefficients: " + p1.getCoefficients());
    //     // System.out.println("p2 coefficients: " + p2.getCoefficients());
    //     System.out.println("p3 coefficients: " + p3.getCoefficients());
    //     // System.out.println("p4 coefficients: " + p4.getCoefficients());
    //     System.out.println("p5 coefficients: " + p5.getCoefficients());
    //     // System.out.println("p6 coefficients: " + p6.getCoefficients());

    //     // Test coefficient getter
    //     System.out.println("\nPolynomial Single Coefficient Getter Test:");
    //     System.out.println("p1 coefficient 0: " + p1.getCoefficient(0));
    //     System.out.println("p1 coefficient 1: " + p1.getCoefficient(1));
    //     System.out.println("p1 coefficient 2: " + p1.getCoefficient(2));
    //     System.out.println("p1 coefficient 3: " + p1.getCoefficient(3));
    //     // System.out.println("p1 coefficient 4: " + p1.getCoefficient(4)); Test Error Handling

    //     // Test coefficient setter
    //     System.out.println("\nPolynomial Single Coefficient Setter Test:");
    //     System.out.println("p2 before setting coefficient: " + p2.toString());
    //     p2.setCoefficient(2, 5.0);
    //     System.out.println("p2 after setting coefficient (5x^2): " + p2.toString());
    //     p2.setCoefficient(6, 4.5);
    //     System.out.println("p2 after setting coefficient (4.5x^6): " + p2.toString());

    //     // Test coefficients setter
    //     System.out.println("\nPolynomial Coefficients Setter Test:");
    //     System.out.println("p3 before setting coefficients: " + p3.toString());
    //     System.out.println("c1 coefficient list: " + c1);
    //     p3.setCoefficients(c1);
    //     System.out.println("p3 after setting coefficients (c1): " + p3.toString());

    //     // Test Evaluate Method
    //     System.out.println("\nPolynomial Evaluate Method Test:");
    //     System.out.println("p3: " + p3.toString());
    //     System.out.println("p3 evaluated at x=2: " + p3.evaluate(2));
    //     System.out.println("p3 evaluated at x=4: " + p3.evaluate(4));
    //     System.out.println("p3 evaluated at x=-5: " + p3.evaluate(-5));

    //     // Test Add Method
    //     System.out.println("\nPolynomial Add Method Test:");
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());
    //     System.out.println("p5 + p6: " + p5.add(p6).toString());

    //     // Test Subtract Method
    //     System.out.println("\nPolynomial Subtract Method Test:");
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());
    //     System.out.println("p5 - p6: " + p5.subtract(p6).toString());

    //     // Test Multiply Method
    //     System.out.println("\nPolynomial Multiply Method Test:");
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());
    //     System.out.println("p5 * p6: " + p5.multiply(p6).toString());

    //     // Test Clear Method
    //     System.out.println("\nPolynomial Clear Method Test:");
    //     System.out.println("p5 before clear: " + p5.toString());
    //     p5.clear();
    //     System.out.println("p5 after clear: " + p5.toString());

    //     // Test compareTo Method
    //     System.out.println("\nPolynomial compareTo Method Test:");
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());
    //     System.out.println("p5.compareTo(p6): " + p5.compareTo(p6));
    //     p5.setCoefficients(c2);
    //     p6.setCoefficients(c3);
    //     System.out.println("p5 and p6 are both set to c2:");
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());
    //     System.out.println("p5.compareTo(p6): " + p5.compareTo(p6));
    //     p5.setCoefficient(2, 5.0);
    //     System.out.println("p5 2nd coefficient has been changed to 5:");
    //     System.out.println("p5: " + p5.toString());
    //     System.out.println("p6: " + p6.toString());
    //     System.out.println("p5.compareTo(p6): " + p5.compareTo(p6));

    //     // Prompt user for number of polynomials to be generated
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter the number of random polynomials to generate: ");
    //     int numPolynomials = scanner.nextInt();

    //     // Generate random polynomials
    //     System.out.println("\nGenerating " + numPolynomials + " Random Polynomials:");
    //     ArrayList<Polynomial> randomPolynomials = new ArrayList<>();
    //     for (int i = 0; i < numPolynomials; i++) {
    //         Polynomial poly = new Polynomial((int) (Math.random() * 5) + 1); // Random degree between 1 and 5
    //         randomPolynomials.add(poly);
    //         System.out.println("Random Polynomial " + (i + 1) + ": " + poly.toString());
    //     }
    //     scanner.close();

    //     // Sort list using compareTo method
    //     System.out.println("\nSorting Random Polynomials:");
    //     randomPolynomials.sort(Polynomial::compareTo);

    //     // Print sorted list
    //     System.out.println("\nSorted Random Polynomials:");
    //     for (int i = 0; i < randomPolynomials.size(); i++) {
    //         System.out.println("Random Polynomial " + (i + 1) + ": " + randomPolynomials.get(i).toString());
    //     }
    // }
}