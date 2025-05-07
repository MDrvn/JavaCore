package utils;

public class NumberParser {

    /*
     Парсит строку в целое число.
     Выкидывает IllegalArgumentException, если строка НЕ является валидным числом.
     */

    public static int parseCount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невалидное значение");
        }
    }

    /*
     Проверяет строку при попытке распарсить ее в целое число.
     При успехе возвратит число.
     Выкинет IllegalArgumentException, если строка НЕ является валидным числом.
     */
    public static int validateCount(String input) {
        try {
            return parseCount(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка: " + e.getMessage());
        }
    }

    /*
     Парсит строку в число с плавающей точкой.
     Выкинет llegalArgumentException, если строка НЕ является валидным числом.
     */
    public static double parseNumber(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невалидное значение");
        }
    }

    /*
     Проверяет строку при попытке её парсинга в число с плавающей точкой.
     Вернет число при успехе.
     Выбросит IllegalArgumentException, если строка НЕ является валидным числом
     */
    public static double validateNumber(String input) {
        try {
            return parseNumber(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка: " + e.getMessage());
        }
    }
}