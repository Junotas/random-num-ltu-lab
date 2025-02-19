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

    // Constants for messages
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    private static final String MEMORY_ERROR_MESSAGE = "Error: Too many numbers requested, system cannot allocate memory.";
    private static final String PROMPT_MESSAGE = "How many random numbers in the range 0 - 999 are desired? ";
    private static final String NO_EVEN_MESSAGE = "No Even Numbers";
    private static final String NO_ODD_MESSAGE = "No Odd Numbers";

    // Constants for value limits
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 999;

    // Additional constants for formatting
    private static final String NEWLINE = "\n";
    private static final String RANDOM_NUMBERS_HEADER = NEWLINE + "Here are the random numbers:";
    private static final String ARRANGED_NUMBERS_HEADER = NEWLINE + "Here are the random numbers arranged:";

    public static void main(final String[] args) {
        // Create a Scanner object to read user input from the console.
        Scanner scanner = new Scanner(System.in);
        System.out.print(PROMPT_MESSAGE);

        int count;
        try {
            // Parse user input and trim any whitespace.
            count = Integer.parseInt(scanner.nextLine().trim());
            // Validate that the input count is at least 1.
            if (count < 1) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            // Handle the case where input is not a valid integer.
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        int[] numbers;
        try {
            // Try to allocate an array to hold the random numbers.
            numbers = new int[count];
        } catch (OutOfMemoryError e) {
            // Handle memory allocation failure.
            System.out.println(MEMORY_ERROR_MESSAGE);
            return;
        }

        // Create a Random object to generate random integers.
        Random random = new Random();
        // Populate the 'numbers' array with random integers in the range MIN_VALUE to MAX_VALUE.
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(MAX_VALUE + 1);
        }

        // Print the unsorted list of random numbers.
        System.out.println(RANDOM_NUMBERS_HEADER);
        printArray(numbers);

        // Initialize counters for even and odd numbers.
        int evenCount = 0;
        int oddCount = 0;
        // Count how many numbers are even and how many are odd.
        for (int num : numbers) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        int[] evens;
        int[] odds;
        try {
            // Allocate arrays for even and odd numbers based on their counts.
            evens = new int[evenCount];
            odds = new int[oddCount];
        } catch (OutOfMemoryError e) {
            // Handle memory allocation failure for the even/odd arrays.
            System.out.println(MEMORY_ERROR_MESSAGE);
            return;
        }

        // Populate the even and odd arrays.
        int eIndex = 0;
        int oIndex = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                evens[eIndex++] = num;
            } else {
                odds[oIndex++] = num;
            }
        }

        // Sort the even numbers in ascending order.
        sortAscending(evens);
        // Sort the odd numbers in descending order.
        sortDescending(odds);

        // Print the arranged (sorted) numbers.
        System.out.println(ARRANGED_NUMBERS_HEADER);
        if (evenCount > 0) {
            printArray(evens);
        } else {
            System.out.print(NO_EVEN_MESSAGE);
        }

        // Print the separator and then the odd numbers.
        System.out.print(" - ");
        if (oddCount > 0) {
            printArray(odds);
        } else {
            System.out.print(NO_ODD_MESSAGE);
        }

        // Print a summary of the even and odd counts.
        System.out.println(NEWLINE + NEWLINE + "Of the above " + count + " numbers, " + evenCount + " were even and " + oddCount + " odd");
    }

    /**
     * Prints the elements of an array separated by spaces.
     *
     * @param arr the array to print
     */
    private static void printArray(final int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Add a space before printing all elements except the first.
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
    }

    /**
     * Sorts an array in ascending order using the Bubble Sort algorithm.
     *
     * @param arr the array to sort
     */
    private static void sortAscending(final int[] arr) {
        // Iterate over each element in the array.
        for (int i = 0; i < arr.length - 1; i++) {
            // Compare adjacent elements and swap if they are in the wrong order.
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts an array in descending order using the Bubble Sort algorithm.
     *
     * @param arr the array to sort
     */
    private static void sortDescending(final int[] arr) {
        // Iterate over each element in the array.
        for (int i = 0; i < arr.length - 1; i++) {
            // Compare adjacent elements and swap if they are in the wrong order for descending order.
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
