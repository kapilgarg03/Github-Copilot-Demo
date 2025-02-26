package com.kapil.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    private static final String RED = "\033[31m";
    private static final String RESET = "\033[0m";

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("\n*** Welcome to Kapil's Calculator - Powered by GitHub Copilot! ***\n");
        System.out.println("Supported operations: Addition (+), Subtraction (-), Multiplication (*), Division (/)");

        try {
            while (true) {

                double num1 = getNumber(String.format("%-25s", "Enter first number  ::"));
                double num2 = getNumber(String.format("%-25s", "Enter second number ::"));

                char operator = getOperator();

                double result = calculate(num1, num2, operator);
                System.out.println("\nResult: " + result);

                if (!askToContinue()) {
                    System.out.println("\nThank you for using Kapil's Calculator. Goodbye!");
                    break;
                }

            }
        } finally {
            scanner.close();
        }

    }

    private static double getNumber(String message) {
        while (true) {
            System.out.println();
            System.out.print(message);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(RED + "Error :: Invalid input. Please enter a valid number." + RESET);
                scanner.nextLine();
            }
        }
    }

    private static char getOperator() {
        System.out.println();
        System.out.print("Enter an operator (+, -, *, /) :: ");
        while (true) {
            String input = scanner.next();
            if (input.length() == 1 && "+-*/".contains(input)) {
                return input.charAt(0);
            }
            System.out.println(RED + "Error :: Invalid operator. Please enter any of (+, -, *, /)." + RESET);
        }
    }

    private static double calculate(double num1, double num2, char operator) {
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    System.out.println(RED + "\nError: Cannot divide by zero." + RESET);
                    yield 0;
                }
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException(RED + "Unexpected operator :: " + operator + RESET);
        };
    }

    private static boolean askToContinue() {
        System.out.println();
        System.out.print("Do you want to perform another calculation? (Yes/No) :: ");
        return scanner.next().equalsIgnoreCase("yes");
    }

}
