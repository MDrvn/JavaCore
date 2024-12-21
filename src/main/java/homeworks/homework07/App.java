package homeworks.homework07;

/* Задача 1. Расширить программу прошлого домашнего задания.
Добавить механизм наследования.
В программе должно быть два класса – один для обычных продуктов –
Product, а другой для специальных – DiscountProduct.
• Product - представляет обычный продукт из прошлого домашнего
задания. Характеристики Продукта: название и стоимость. Название продукта
не может быть пустой строкой, оно должно быть. Стоимость продукта не может
быть отрицательным числом.
• Скидочный продукт — специальный продукт, цена которого снижена на
размер скидки. У скидки есть также срок действия. После завершения срока
действия скидка меняется.
Ограничения в классах для продуктов:
- Название продукта не должно содержать только цифры;
- Если название продукта короче, чем 3 символа, то такое название
недействительно;
- Если стоимость продукта или скидочного продукта 0 или
отрицательная, то такая цена неправильная. Должна быть ошибка валидации.

Примечание:
Возможно, запуталась при попытке понять, как совместить ввод и исключения.
Вероятно, будет хорошей идеей ввести более разнообразные ошибки.*/

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Список покупателей и цикл для работы со вводом:
        List<Person> people = new ArrayList<>();
        System.out.println("Введите имя покупателя и сумму покупки (формат: имя_покупателя сумма).\n" +
                "Для перехода на следующую строку нажмите ENTER.\n" +
                "Чтобы закончить ввод, введите END:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода покупателя.");
                }
                String name = parts[0];
                double money = Double.parseDouble(parts[1]);
                people.add(new Person(name, money));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат суммы.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода покупателя. Попробуйте снова.");
            }
        }

        // Список продуктов и цикл для работы со скидками:
        List<Product> products = new ArrayList<>();
        System.out.println("Введите продукт и его стоимость (формат: название_продукта цена [скидка срок_действия_скидки]).\n" +
                "Пример для скидочного продукта: хлеб 50 10 2024-12-31.\n" +
                "Чтобы закончить ввод, введите END:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    throw new IllegalArgumentException("Неверный формат ввода продукта.");
                }
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                // Если ввод содержит скидку и срок действия:
                if (parts.length == 4) {
                    double discount = Double.parseDouble(parts[2]);
                    LocalDate expiryDate = LocalDate.parse(parts[3]);
                    products.add(new DiscountProduct(name, price, discount, expiryDate));
                // Обычный продукт:
                } else if (parts.length == 2) {
                    products.add(new Product(name, price));
                } else {
                    throw new IllegalArgumentException("Неверное количество аргументов для продукта.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат числа для цены или скидки.");
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверный формат даты. Используйте формат: ГГГГ-ММ-ДД.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода продукта. Попробуйте снова.");
            }
        }

        // Цикл для работы с покупками:
        System.out.println("Введите покупки (формат: имя_покупателя название_продукта).\n" +
                "Чтобы закончить ввод, введите END:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат ввода покупки.");
                }
                String personName = parts[0];
                String productName = parts[1];

                Person person = people.stream()
                        .filter(p -> p.getName().equals(personName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Покупатель не найден: " + personName));

                Product product = products.stream()
                        .filter(p -> p.getName().equals(productName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Продукт не найден: " + productName));

                person.buyProduct(product);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка при обработке покупки. Попробуйте снова.");
            }
        }

        // Результаты:
        System.out.println("\nРезультаты:");
        for (Person person : people) {
            System.out.println(person);
        }

        scanner.close();
    }
}