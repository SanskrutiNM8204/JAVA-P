package Practical_8.P1;

import java.util.Scanner;

public class Guardedcalc {
	public static class DivideByZeroException extends Exception {
		public DivideByZeroException() {
			super("Cannot divide by zero.");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean succeeded = false;

		while (!succeeded) {
			try {
				System.out.print("Enter first number: ");
				double first = Double.parseDouble(scanner.nextLine().trim());
				System.out.print("Enter operator (+, -, *, /): ");
				String operator = scanner.nextLine().trim();
				System.out.print("Enter second number: ");
				double second = Double.parseDouble(scanner.nextLine().trim());

				double result = switch (operator) {
					case "+" -> first + second;
					case "-" -> first - second;
					case "*" -> first * second;
					case "/" -> {
						if (second == 0) {
							throw new DivideByZeroException();
						}
						yield first / second;
					}
					default -> throw new IllegalArgumentException("Invalid operator: " + operator);
				};

				System.out.println("Result: " + result);
				succeeded = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid number. Please enter numeric values.");
			} catch (DivideByZeroException e) {
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} finally {
				System.out.println("Attempt logged.");
			}
		}

		scanner.close();
	}
}
