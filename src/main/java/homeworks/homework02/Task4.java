package homeworks.homework02;

/* Задача 4*. Напишите программу на Java для печати сетки из заданных элементов.
Тестовые данные:
Введите число строк и столбцов сетки: 10
Введите повторяемый элемент сетки: -
Ожидаемый результат :
Сетка по запросу 10 на 10
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
        - - - - - - - - - -
*/

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        task4();
    }

    private static void task4() {
        Scanner scanner = new Scanner(System.in);

        // Получаем данные ввода, создаем переменные:
        System.out.println("Введите число строк и столбцов сетки: ");
        int params = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Введите повторяемый элемент сетки: ");
        String element = scanner.nextLine();

        // Для создания сетки используем вложенный цикл, выводим результат:
        System.out.println("Сетка по запросу " + params + "*" + params);
        for (int i = 0; i < params; i++) {
            for (int n = 0; n < params; n++) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}

