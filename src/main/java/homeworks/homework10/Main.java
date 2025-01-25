package homeworks.homework10;

/*
«Абстрактные классы и интерфейсы. Лямбда выражения».
Формулировка задания:
1. Предусмотреть функциональный интерфейс:
interface ByCondition {
boolean isOk(int number);
}
В функциональном интерфейсе обязательно проставить аннотацию.
2.   Создать   класс  Sequence  для   последовательности   со   следующим
методом:
public static int[] filter(int[] array, ByCondition condition) {
...
}
Данный   метод   возвращает   массив,   который   содержит   элементы,
удовлетворяющиие логическому выражению в condition.
3. В main в качестве condition подставить:
- проверку на четность элемента
- проверку, является ли сумма цифр элемента четным числом.

В теории, можно было бы также попробовать использовать Stream API для фильтрации потоков,
расширить метод filter(), чтобы можно было бы работать с коллекциями или добавить логирование,
чтобы эффективно бороться с ошибками.

Массив чисел в данном случае заранее задан, но при желании можно добавить и ввод данных пользователем.
*/

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {12, 34, 55, 78, 90, 33, 21};

        ByCondition isEven = number -> number % 2 == 0;

        int[] evenNumbers = Sequence.filter(array, isEven);
        System.out.println("Четные числа: " + Arrays.toString(evenNumbers));

        ByCondition isSumOfDigitsEven = number -> {
            int sum = 0;
            int temp = number;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            return sum % 2 == 0;
        };

        int[] sumEvenNumbers = Sequence.filter(array, isSumOfDigitsEven);
        System.out.println("Числа, у которых сумма цифр четная: " + Arrays.toString(sumEvenNumbers));
    }
}