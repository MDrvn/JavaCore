package homeworks.homework02;

/* Задача 3*. Напишите Java-программу для объединения данной строки с самим собой
заданное количество раз.

Тестовые данные:
Исходная строка: Java
Сколько раз вывести строку? 8

Ожидаемый результат :
После повторения 8 раз: JavaJavaJavaJavaJavaJavaJavaJava

*/

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        task3();
    }
    private static void task3() {
        Scanner scanner = new Scanner(System.in);

        // Получаем данные ввода, создаем переменные:
        System.out.println("Исходная строка: ");
        String line = scanner.nextLine();

        System.out.println("Сколько раз вывести строку? ");
        int times = scanner.nextInt();

        String result = "";

        // Используем цикл и конкатенацию строк, выводим полученный результат:
        for (int i = 1; i <= times; i++) {
            result += line;

        }
        System.out.println("После повторения " + times + " раз: " + result);
        scanner.close();
    }
}