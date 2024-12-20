package homeworks.homework03;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // "Создаем" экземпляры телевизоров, вручную задавая параметры:
        Television tv1 = new Television("Samsung", 55,
                true, 60000, "4K", "Черный");
        Television tv2 = new Television("LG", 43,
                false, 40000, "1080p", "Белый");

        // Выводим информацию о телевизорах:
        System.out.println(tv1);
        System.out.println(tv2);

        // "Создаем" телевизор с помощью сеттеров:
        Television tv3 = new Television();
        tv3.setBrand("Sony");
        tv3.setScreenSize(65);
        tv3.setSmart(true);
        tv3.setPrice(80000);
        tv3.setResolution("8K");
        tv3.setColor("Серебристый");

        System.out.println(tv3);

        // "Создаем" телевизор с помощью ввода параметров с клавиатуры:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите бренд телевизора: ");
        String brand = scanner.nextLine();
        System.out.println("Введите размер экрана (в дюймах): ");
        int screenSize = scanner.nextInt();
        System.out.println("Это Смарт-ТВ? (true/false): ");
        boolean isSmart = scanner.nextBoolean();
        System.out.println("Введите стоимость телевизора: ");
        double price = scanner.nextDouble();

        // Очистка буфера:
        scanner.nextLine();

        System.out.println("Введите разрешение (например, 4K, 1080p): ");
        String resolution = scanner.nextLine();
        System.out.println("Введите цвет телевизора: ");
        String color = scanner.nextLine();

        Television tv4 = new Television(brand, screenSize, isSmart,
                price, resolution, color);
        System.out.println(tv4);

        // "Создаем" телевизор со случайными параметрами:
        String[] brands = {"Sony", "Samsung", "LG", "Panasonic", "Toshiba"};
        String[] resolutions = {"4K", "1080p", "8K"};
        String[] colors = {"Черный", "Белый", "Серебристый", "Красный","Лиловый"};

        // В дело вступает великий и ужасный рандом:
        Random random = new Random();
        String randomBrand = brands[random.nextInt(brands.length)];

        // Задаем случайный размер экрана от 32 до 81 дюйма:
        int randomScreenSize = 32 + random.nextInt(50);
        boolean randomIsSmart = random.nextBoolean();

        // Задаем случайную стоимость от 15 000 до 115 000:
        double randomPrice = 15000 + random.nextInt(100000);

        String randomResolution = resolutions[random.nextInt(resolutions.length)];
        String randomColor = colors[random.nextInt(colors.length)];

        Television tv5 = new Television(randomBrand, randomScreenSize, randomIsSmart,
                randomPrice, randomResolution, randomColor);
        System.out.println(tv5);
    }
}