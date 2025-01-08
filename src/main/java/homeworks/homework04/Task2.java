package homeworks.homework04;

/*
Задача 2.

Задана последовательность, состоящая только из символов ‘>’,
‘<’ и ‘-‘. Требуется найти количество стрел, которые спрятаны в этой
последовательности. Стрелы – это подстроки вида ‘>>-->’ и ‘<--<<’.

Входные данные: в первой строке входного потока записана строка,
состоящая из символов ‘>’, ‘<’ и ‘-‘ (без пробелов). Строка может содержать до
106 символов.
Выходные данные: в единственную строку выходного потока нужно
вывести искомое количество стрелок.

Помимо сканера работа будет вестись с регулярными выражениями - потребуется импорт:
java.util.Scanner; java.util.regex.Matcher; java.util.regex.Pattern
*/

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static void main(String[] args) {
        // Читаем входную строку:
        System.out.println("Введите символы для считывания (до 106 символов): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        // Используем регулярное выражение для поиска стрел:
        String regex = ">>-->|<--<<";

        // Создаем объект Pattern и Matcher:
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Считаем количество получившихся совпадений:
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        // Выводим полученный результат:
        System.out.println("Число спрятанных стрел: " + count);
    }
}