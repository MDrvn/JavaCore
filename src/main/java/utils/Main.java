package utils;

import utils.NumberParser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите целое число: ");
        String intInput = scanner.nextLine();
        try {
            int intResult = NumberParser.validateCount(intInput);
            System.out.println("Распарсено как Integer: " + intResult);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("Введите число с плавающей точкой: ");
        String doubleInput = scanner.nextLine();
        try {
            double doubleResult = NumberParser.validateNumber(doubleInput);
            System.out.println("Распарсено как Double: " + doubleResult);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}


