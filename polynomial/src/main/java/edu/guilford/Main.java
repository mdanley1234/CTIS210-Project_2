package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main class to manage polynomial generation, sorting, and searching.
 */
public class Main {
    /**
     * Main method to interact with the user and perform various operations like sorting and searching polynomials.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ArrayList<Polynomial> polynomials = new ArrayList<>();
        ArrayList<Integer> degrees = new ArrayList<>();
        ArrayList<Double> coefficients = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of random polynomials to generate: ");
        int numPolynomials = scanner.nextInt();
        scanner.close();

        // Generate random polynomials and store them
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

        // Output the statistics
        System.out.println("");
        System.out.println("Max Degree: " + maxDegree);
        System.out.println("Min Degree: " + minDegree);
        System.out.println("Max Coefficient: " + maxCoefficient);
        System.out.println("Min Coefficient: " + minCoefficient);
        System.out.println("");

        // Test Sorting Methods
        selectionSort(polynomials);

        // Scramble array before applying the next sorting method
        scrambleArray(polynomials);
        mergeSort(polynomials);

        // Scramble array again before applying the final sorting method
        scrambleArray(polynomials);
        builtInSort(polynomials);
        System.out.println("");

        // Test Search Methods
        Polynomial searchPolynomial = polynomials.get((int) (Math.random() * polynomials.size()));
        System.out.println("Selected Search Polynomial: " + searchPolynomial);

        // Perform Linear Search
        int linearSearchResult = linearSearch(polynomials, searchPolynomial);
        System.out.println("Linear Search Result Index: " + linearSearchResult);

        // Perform Binary Search
        int binarySearchResult = binarySearch(polynomials, searchPolynomial);
        System.out.println("Binary Search Result Index: " + binarySearchResult);

        // Perform Built-in Binary Search
        int builtInBinarySearchResult = builtInBinarySearch(polynomials, searchPolynomial);
        System.out.println("Built-in Binary Search Result Index: " + builtInBinarySearchResult);
        System.out.println("");

        // Create a new polynomial that can't exist in the list
        Polynomial nonExistentPolynomial = new Polynomial();
        nonExistentPolynomial.setCoefficient(100, 1.0); // Set a high degree coefficient to ensure it doesn't exist

        System.out.println("Non-existent Polynomial: " + nonExistentPolynomial);

        // Perform Linear Search on non-existent polynomial
        int linearSearchNonExistentResult = linearSearch(polynomials, nonExistentPolynomial);
        System.out.println("Linear Search Result Index for Non-existent Polynomial: " + linearSearchNonExistentResult);

        // Perform Binary Search on non-existent polynomial
        int binarySearchNonExistentResult = binarySearch(polynomials, nonExistentPolynomial);
        System.out.println("Binary Search Result Index for Non-existent Polynomial: " + binarySearchNonExistentResult);

        // Perform Built-in Binary Search on non-existent polynomial
        int builtInBinarySearchNonExistentResult = builtInBinarySearch(polynomials, nonExistentPolynomial);
        System.out.println("Built-in Binary Search Result Index for Non-existent Polynomial: " + builtInBinarySearchNonExistentResult);
    }

    /**
     * Scrambles the order of the polynomials in the provided list using a random shuffle.
     *
     * @param polynomials list of polynomials to scramble
     */
    public static void scrambleArray(ArrayList<Polynomial> polynomials) {
        Collections.shuffle(polynomials);
    }

    /**
     * Sorts the list of polynomials using selection sort.
     *
     * @param polynomials the list of polynomials to be sorted
     */
    public static void selectionSort(ArrayList<Polynomial> polynomials) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < polynomials.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < polynomials.size(); j++) {
                if (polynomials.get(j).compareTo(polynomials.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(polynomials, i, minIndex);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Selection Sort Time: " + (endTime - startTime) + " ms");
    }

    /**
     * Sorts the list of polynomials using merge sort.
     *
     * @param polynomials the list of polynomials to be sorted
     */
    public static void mergeSort(ArrayList<Polynomial> polynomials) {
        long startTime = System.currentTimeMillis();
        mergeSortHelper(polynomials, 0, polynomials.size() - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Merge Sort Time: " + (endTime - startTime) + " ms");
    }

    /**
     * Helper method to perform merge sort on the polynomials list.
     *
     * @param polynomials the list of polynomials to be sorted
     * @param left        the left index of the range to be sorted
     * @param right       the right index of the range to be sorted
     */
    private static void mergeSortHelper(ArrayList<Polynomial> polynomials, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(polynomials, left, mid);
            mergeSortHelper(polynomials, mid + 1, right);
            merge(polynomials, left, mid, right);
        }
    }

    /**
     * Merges two sorted sublists of polynomials into one sorted list.
     *
     * @param polynomials the list of polynomials to be merged
     * @param left        the left index of the range to merge
     * @param mid         the midpoint index of the range
     * @param right       the right index of the range to merge
     */
    private static void merge(ArrayList<Polynomial> polynomials, int left, int mid, int right) {
        ArrayList<Polynomial> leftList = new ArrayList<>(polynomials.subList(left, mid + 1));
        ArrayList<Polynomial> rightList = new ArrayList<>(polynomials.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                polynomials.set(k++, leftList.get(i++));
            } else {
                polynomials.set(k++, rightList.get(j++));
            }
        }
        while (i < leftList.size()) {
            polynomials.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            polynomials.set(k++, rightList.get(j++));
        }
    }

    /**
     * Sorts the list of polynomials using the built-in Collections.sort method.
     *
     * @param polynomials the list of polynomials to be sorted
     */
    public static void builtInSort(ArrayList<Polynomial> polynomials) {
        long startTime = System.currentTimeMillis();
        Collections.sort(polynomials);
        long endTime = System.currentTimeMillis();
        System.out.println("Built-in Sort Time: " + (endTime - startTime) + " ms");
    }

    /**
     * Performs a linear search for a polynomial in the list.
     *
     * @param polynomials the list of polynomials to search through
     * @param target      the polynomial to search for
     * @return the index of the target polynomial, or -1 if not found
     */
    public static int linearSearch(ArrayList<Polynomial> polynomials, Polynomial target) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < polynomials.size(); i++) {
            if (polynomials.get(i).equals(target)) {
                long endTime = System.currentTimeMillis();
                System.out.println("Linear Search Time: " + (endTime - startTime) + " ms");
                return i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Linear Search Time: " + (endTime - startTime) + " ms");
        return -1;
    }

    /**
     * Performs a binary search for a polynomial in the list.
     *
     * @param polynomials the list of polynomials to search through
     * @param target      the polynomial to search for
     * @return the index of the target polynomial, or -1 if not found
     */
    public static int binarySearch(ArrayList<Polynomial> polynomials, Polynomial target) {
        long startTime = System.currentTimeMillis();
        int left = 0;
        int right = polynomials.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = polynomials.get(mid).compareTo(target);
            if (cmp < 0) {
                left = mid + 1;
            } else if (cmp > 0) {
                right = mid - 1;
            } else {
                long endTime = System.currentTimeMillis();
                System.out.println("Binary Search Time: " + (endTime - startTime) + " ms");
                return mid;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Binary Search Time: " + (endTime - startTime) + " ms");
        return -1;
    }

    /**
     * Performs a built-in binary search for a polynomial in the list using Collections.binarySearch.
     *
     * @param polynomials the list of polynomials to search through
     * @param target      the polynomial to search for
     * @return the index of the target polynomial, or -1 if not found
     */
    public static int builtInBinarySearch(ArrayList<Polynomial> polynomials, Polynomial target) {
        long startTime = System.currentTimeMillis();
        int index = Collections.binarySearch(polynomials, target);
        long endTime = System.currentTimeMillis();
        System.out.println("Built-in Binary Search Time: " + (endTime - startTime) + " ms");
        return index;
    }
}
