package homeworks.homework06;

/* 1. Создать классы Покупатель (Person) и Продукт (Product).
Характеристики Покупателя: имя, сумма денег и пакет с продуктами
(массив объектов типа Продукт). Имя не может быть пустой строкой. Деньги не
могут быть отрицательным числом.
Если Покупатель может позволить себе Продукт, то Продукт добавляется
в пакет. Если у Покупателя недостаточно денег, то добавление не происходит.
Характеристики Продукта: название и стоимость. Название продукта не
может быть пустой строкой, оно должно быть. Стоимость продукта не может
быть отрицательным числом.
2. Поля в классах должны быть private, доступ к полям осуществляется
через геттеры и сеттеры или конструктор класса.
3. В классах переопределены методы toString(), equals(), hashcode().
4. Создать в классе App метод main и проверить работу приложения.
Данные Покупателей и Продукты вводятся с клавиатуры или задаются
случайным образом. Продукты в цикле выбираются покупателями по очереди
и, пока не введено слово END, наполняется пакет.
5. Обработать следующие ситуации:
а. Если покупатель не может позволить себе продукт, то
напечатайте соответствующее сообщение ("[Имя человека] не может
позволить себе [Название продукта]").
б. Если ничего не куплено, выведите имя человека, за которым
следует "Ничего не куплено".
в. В случае неверного ввода (сообщение об исключении: "Деньги не
могут быть отрицательными") или пустого имени: (сообщение об
исключении: "Имя не может быть пустым").
6. Программа реализуется в отдельной ветке git homeworks/homework06.
При сохранении состояния программы (коммиты) пишется сообщение с
описанием хода работы по задаче.
В корне папки с программой должен быть файл .gitignore.
Программа локально коммитится и публикуется в репозиторий GitHub на
проверку.

Примечание:
Может быть улучшено. Например, можно дать пользователю в начале четкие инструкции
по заполнению - просто вывести большой кусок текста перед основным вводом. Кроме того,
можно будет дополнительно подправить вывод и общее оформление или в принципе немного
пересмотреть общую логику действий и попробовать решить задачу немного иначе.

Дополнительно использована конструкция try-catch.*/

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