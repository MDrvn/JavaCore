package homeworks.homework14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Список покупателей:
        List<Person> people = new ArrayList<>();
        System.out.println("Введите имя покупателя и сумму покупки (формат: имя_покупателя сумма).\n" +
                "Нажав ENTER, можно перейти на новую строку и ввести новые данные, если необходимо.\n"+
                "Чтобы закончить ввод, перейдите с помощью ENTER на новую строку и введите END:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                String name = parts[0];
                double money = Double.parseDouble(parts[1]);
                people.add(new Person(name, money));
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Попробуйте снова");
            }
        }

        // Список продуктов
        List<Product> products = new ArrayList<>();
        System.out.println("Введите продукт и его стоимость (формат: название_продукта стоимость).\n"+
                "Нажав ENTER, можно перейти на новую строку и ввести новые данные, если необходимо.\n"+
                "Чтобы закончить ввод, перейдите с помощью ENTER на новую строку и введите END:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                products.add(new Product(name, price));
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Попробуйте снова.");
            }
        }

        // Цикл покупок
        System.out.println("Введите покупки (формат: имя_покупателя название_продукта).\n" +
                "Нажав ENTER, можно перейти на новую строку и ввести новые данные, если необходимо.\n"+
                "Чтобы закончить ввод, перейдите с помощью ENTER на новую строку и введите END:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("END")) {
                break;
            }
            try {
                String[] parts = input.split(" ");
                String personName = parts[0];
                String productName = parts[1];

                Person person = people.stream()
                        .filter(p -> p.getName().equals(personName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Покупатель не найден"));

                Product product = products.stream()
                        .filter(p -> p.getName().equals(productName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));

                person.buyProduct(product);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // Результаты:
        System.out.println("Результаты:");
        for (Person person : people) {
            System.out.println(person);
        }

        scanner.close();
    }
}
