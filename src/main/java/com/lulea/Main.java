package com.lulea;

import java.util.Scanner;
import java.util.Random;

/**
 * This program generates a specified number of random integers (0-999),
 * sorts them by even and odd numbers, and displays them in a specific format.
 *
 * Pseudocode:
 * 1. Prompt the user for the number of random integers (validate input).
 *    - If input is invalid, print INVALID_INPUT_MESSAGE and terminate.
 *    - If the input is too large, handle OutOfMemoryError.
 * 2. Generate the specified number of random integers and store them in an array.
 * 3. Print the unsorted array.
 * 4. Separate numbers into even and odd arrays.
 * 5. Sort even numbers in ascending order and odd numbers in descending order.
 * 6. Print the sorted numbers in the required format.
 * 7. Display the count of even and odd numbers.
 *
 * @author Ludvig Fendert (ludfen-4)
 */



public class Main {

    // Constants
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    private static final String MEMORY_ERROR_MESSAGE = "Error: Too many numbers requested, system cannot allocate memory.";
    private static final String PROMPT_MESSAGE = "How many random numbers in the range 0 - 999 are desired? ";
    private static final String NO_EVEN_MESSAGE = "No Even Numbers";
    private static final String NO_ODD_MESSAGE = "No Odd Numbers";
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 999;

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PROMPT_MESSAGE);

        int count;
        try {
            count = Integer.parseInt(scanner.nextLine().trim());
            if (count < 1) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        int[] numbers;
        try {
            numbers = new int[count];
        } catch (OutOfMemoryError e) {
            System.out.println(MEMORY_ERROR_MESSAGE);
            return;
        }

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(MAX_VALUE + 1);
        }

        System.out.println("\nHere are the random numbers:");
        printArray(numbers);

        int evenCount = 0;
        int oddCount = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        int[] evens = new int[evenCount];
        int[] odds = new int[oddCount];
        int eIndex = 0;
        int oIndex = 0;

        for (int num : numbers) {
            if (num % 2 == 0) {
                evens[eIndex++] = num;
            } else {
                odds[oIndex++] = num;
            }
        }

        sortAscending(evens);
        sortDescending(odds);

        System.out.println("\nHere are the random numbers arranged:");
        if (evenCount > 0) {
            printArray(evens);
        } else {
            System.out.print(NO_EVEN_MESSAGE);
        }

        System.out.print(" - ");
        if (oddCount > 0) {
            printArray(odds);
        } else {
            System.out.print(NO_ODD_MESSAGE);
        }

        System.out.println("\n\nOf the above " + count + " numbers, " + evenCount + " were even and " + oddCount + " odd");
    }

    /**
     * Prints the elements of an array separated by spaces.
     *
     * @param arr the array to print
     */
    private static void printArray(final int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
    }

    /**
     * Sorts an array in ascending order using Bubble Sort.
     *
     * @param arr the array to sort
     */
    private static void sortAscending(final int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts an array in descending order using Bubble Sort.
     *
     * @param arr the array to sort
     */
    private static void sortDescending(final int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
