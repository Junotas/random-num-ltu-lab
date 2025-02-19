package com.lulea;

import java.util.Scanner;
import java.util.Random;

public class Main {

    // Constants
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input.";
    private static final String IMPOSSIBLE_TO_HANDLE_MESSAGE = "Impossible to handle the entered number.";
    private static final String NO_EVEN_NUMBERS_MESSAGE = "No Even Numbers";
    private static final String NO_ODD_NUMBERS_MESSAGE = "No Odd Numbers";
    private static final int RANDOM_NUMBER_UPPER_BOUND = 1000;
    private static final int MINIMUM_VALID_NUMBER = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("How many random numbers in the range 0 - 999 are desired? ");

        int n;
        try {
            n = Integer.parseInt(scanner.nextLine());
            if (n < MINIMUM_VALID_NUMBER) {
                System.out.println(INVALID_INPUT_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
            return;
        }

        int[] numbers;
        try {
            numbers = new int[n];
        } catch (OutOfMemoryError e) {
            System.out.println(IMPOSSIBLE_TO_HANDLE_MESSAGE);
            return;
        }

        // Fill the array with random numbers and print them
        System.out.println("\nHere are the random numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = random.nextInt(RANDOM_NUMBER_UPPER_BOUND); // range 0 - 999
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Separate even and odd numbers
        int evenCount = 0, oddCount = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        int[] evens = new int[evenCount];
        int[] odds = new int[oddCount];
        int evenIndex = 0, oddIndex = 0;

        for (int num : numbers) {
            if (num % 2 == 0) {
                evens[evenIndex++] = num;
            } else {
                odds[oddIndex++] = num;
            }
        }

        // Sort evens (ascending) and odds (descending)
        bubbleSort(evens, true);
        bubbleSort(odds, false);

        // Print sorted numbers
        System.out.println("\nHere are the random numbers arranged:");
        if (evenCount > 0) {
            printArray(evens);
        } else {
            System.out.print(NO_EVEN_NUMBERS_MESSAGE);
        }
        System.out.print(" - ");
        if (oddCount > 0) {
            printArray(odds);
        } else {
            System.out.print(NO_ODD_NUMBERS_MESSAGE);
        }
        System.out.println();

        // Print counts
        System.out.println("\nOf the above " + n + " numbers, " + evenCount + " were even and " + oddCount + " odd");

        scanner.close();
    }

    // Bubble Sort: Ascending if order=true, Descending if order=false
    private static void bubbleSort(int[] arr, boolean order) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if ((order && arr[j] > arr[j + 1]) || (!order && arr[j] < arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Print array elements in one line
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}