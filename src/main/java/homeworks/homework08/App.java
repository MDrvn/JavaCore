package homeworks.homework08;

/*
Добавить работу с файлом для домашнего задания 6 «Понятия ООП:
инкапсуляция».
Характеристики Покупателя: имя, сумма денег и пакет с продуктами
(массив объектов типа Продукт). Имя не может быть пустой строкой. Деньги не
могут быть отрицательным числом.
Если Покупатель может позволить себе Продукт, то Продукт добавляется
в пакет. Если у Покупателя недостаточно денег, то добавление не происходит.
Характеристики Продукта: название и стоимость. Название продукта не
может быть пустой строкой, оно должно быть. Стоимость продукта не может
быть отрицательным числом.
Данные Покупателей и Продукты считываются из файла. Продукты в
цикле выбираются покупателями по очереди и, пока не введено слово END,
наполняется пакет. Результат работы программы выводится в файл.

Ход решения:
1. Сформировать файл с данными задачи. Файл должен быть в формате
*.txt. Структура файла для задачи повторяет требования к вводу из консоли.
2. Считать файл в программу в классе App, методе main.
3. Реализовать вывод данных в файл *.txt. Требования к выводу остаются
прежними.
4. Проверить, что функционал программы работает без ошибок.

Можно было бы просто импортировать код старой задачи, но так проще контролировать, что происходит.
В целом, можно еще улучшить программу - немного упростить ввод для пользователя,
а также добавить инструкции по вводу. Можно также добавить дополнительные ситуации с ошибками -
здесь они представлены не все, но иначе это бы заняло немало места)
Файлы - input.txt, output.txt - cохраняются в текущую директорию
*/

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {

        String inputFileName = System.getProperty("user.dir") + "/input.txt";
        String outputFileName = System.getProperty("user.dir") + "/output.txt";

        // Ввод данных с консоли и запись в файл input.txt:
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFileName))) {
            Scanner scanner = new Scanner(System.in);

            // Ввод данных о покупателях:
            System.out.println("Введите данные о покупателях (формат: Имя = Деньги; ...):");
            String buyersInput = scanner.nextLine();
            writer.write(buyersInput);
            writer.newLine();

            // Ввод данных о товарах:
            System.out.println("Введите данные о товарах (формат: Название = Стоимость; ...):");
            String productsInput = scanner.nextLine();
            writer.write(productsInput);
            writer.newLine();

            // Ввод данных о покупках:
            System.out.println("Введите данные о покупках (формат: Имя Покупка). Введите END для завершения:");
            while (true) {
                String purchaseInput = scanner.nextLine();
                if (purchaseInput.equalsIgnoreCase("END")) {
                    writer.write("END");
                    writer.newLine();
                    break;
                }
                writer.write(purchaseInput);
                writer.newLine();
            }
            System.out.println("Данные успешно записаны в файл " + inputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
            return;
        }

        // Чтение данных из файла input.txt:
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<String> purchases = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            // Чтение покупателей:
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                String[] personData = line.split("; ");
                for (String personInfo : personData) {
                    String[] parts = personInfo.split(" = ");
                    if (parts.length != 2) {
                        System.out.println("Некорректный формат данных о покупателях: " + personInfo);
                        continue;
                    }
                    String name = parts[0].trim();
                    double money = Double.parseDouble(parts[1].trim().replace(",", "."));
                    people.add(new Person(name, money));
                }
            }

            // Чтение товаров:
            line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                String[] productData = line.split("; ");
                for (String productInfo : productData) {
                    String[] parts = productInfo.split(" = ");
                    if (parts.length != 2) {
                        System.out.println("Некорректный формат данных о товарах: " + productInfo);
                        continue;
                    }
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    products.add(new Product(name, price));
                }
            }

            // Чтение покупок:
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("END")) {
                    break;
                }
                purchases.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования числа: " + e.getMessage());
            return;
        }

        // Обработка покупок:
        StringBuilder output = new StringBuilder();
        Map<String, List<String>> personPurchases = new HashMap<>();

        for (String purchase : purchases) {
            if (!purchase.contains(" ")) {
                System.out.println("Ошибка в формате строки покупки: " + purchase);
                continue;
            }

            try {
                String[] parts = purchase.split(" ", 2); // Разделяем строку на имя покупателя и название товара
                String personName = parts[0];
                String productName = parts[1];

                // Находим покупателя:
                Person person = people.stream()
                        .filter(p -> p.getName().equals(personName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Покупатель не найден: " + personName));

                // Находим товар:
                Product product = products.stream()
                        .filter(p -> p.getName().equals(productName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Продукт не найден: " + productName));

                // Проверяем возможность покупки:
                if (person.buyProduct(product)) {
                    output.append(String.format("%s купил(а) %s%n", person.getName(), product.getName()));
                    personPurchases.computeIfAbsent(person.getName(), k -> new ArrayList<>()).add(product.getName());
                } else {
                    output.append(String.format("%s не может позволить себе %s%n", person.getName(), product.getName()));
                }
            } catch (Exception e) {
                System.out.println("Ошибка обработки покупки: " + purchase + " - " + e.getMessage());
            }
        }

        // Вывод покупок каждого человека:
        for (Person person : people) {
            List<String> purchasedProducts = personPurchases.getOrDefault(person.getName(), Collections.emptyList());
            if (purchasedProducts.isEmpty()) {
                output.append(String.format("%s - Ничего не куплено%n", person.getName()));
            } else {
                output.append(String.format("%s - %s%n", person.getName(), String.join(", ", purchasedProducts)));
            }
        }

        // Запись результата в файл output.txt:
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(output.toString());
            System.out.println("Результаты успешно записаны в файл " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}