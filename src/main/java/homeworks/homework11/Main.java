package homeworks.homework11;
/*
Домашнее задание по теме «Java Collections. Stream API»
Формулировка задания:
1. Реализовать класс Автомобиль. У класса есть поля, свойства и
методы.
Поля класса:
а) Номер автомобиля;
б) Модель;
в) Цвет;
г) Пробег;
д) Стоимость.
Обратить внимание на переопределение метода toString, на сеттеры и
геттеры, модификаторы доступа полей.

2. Проверить работу в классе Main, методе main.

3. Создать объект Java Collections со списком автомобилей.

4. Используя Java Stream API, вывести (можно сделать любые 2 пункта
из 4):
1) Номера всех автомобилей, имеющих заданный в переменной цвет
colorToFind или нулевой пробег mileageToFind.
2) Количество уникальных моделей в ценовом диапазоне от n до m тыс.
3) Вывести цвет автомобиля с минимальной стоимостью.
4) Среднюю стоимость искомой модели modelToFind

Были выбраны все 4 пункта - такая работа с данными однозначно пригодится в дальнейшем.
Можно дополнительно добавить работу с данными из файла или попытаться реализовать более сложные варианты
выбора параметров и вывода результатов (например, через считывание с консоли введенных пользователем данных,
возможность выбора нескольких параметров)
*/

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Создание списка автомобилей:
        List<Car> cars = Arrays.asList(
                new Car("a123me", "Mercedes", "White", 0, 8300000),
                new Car("b873of", "Volga", "Black", 0, 673000),
                new Car("w487mn", "Lexus", "Grey", 76000, 900000),
                new Car("p987hj", "Volga", "Red", 610, 704340),
                new Car("c987ss", "Toyota", "White", 254000, 761000),
                new Car("o983op", "Toyota", "Black", 698000, 740000),
                new Car("p146op", "BMW", "White", 271000, 850000),
                new Car("u893ii", "Toyota", "Purple", 210900, 440000),
                new Car("l097df", "Toyota", "Black", 108000, 780000),
                new Car("y876wd", "Toyota", "Black", 160000, 1000000)
        );

        // Вывод всех автомобилей в базе в табличном формате:
        System.out.println("Автомобили в базе:");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "Number", "Model", "Color", "Mileage", "Cost");
        cars.forEach(car -> System.out.printf("%-10s %-10s %-10s %-10d %-10.2f%n",
                car.getNumber(), car.getModel(), car.getColor(), car.getMileage(), car.getPrice()));
        System.out.println();

        // 1. Вывод номеров автомобилей с заданным цветом или нулевым пробегом:
        String colorToFind = "Black";
        int mileageToFind = 0;
        List<String> numbers = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());
        System.out.println("Номера автомобилей с цветом " + colorToFind + " или пробегом " + mileageToFind + ": " + numbers);

        // 2. Количество уникальных моделей в ценовом диапазоне:
        double priceFrom = 700000;
        double priceTo = 800000;
        long uniqueModelsCount = cars.stream()
                .filter(car -> car.getPrice() >= priceFrom && car.getPrice() <= priceTo)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Количество уникальных моделей в ценовом диапазоне от " + priceFrom + " до " + priceTo + ": " + uniqueModelsCount);

        // 3. Цвет автомобиля с минимальной стоимостью:
        String colorOfCheapestCar = cars.stream()
                .min(Comparator.comparingDouble(Car::getPrice))
                .map(Car::getColor)
                .orElse("Не найдено");
        System.out.println("Цвет автомобиля с минимальной стоимостью: " + colorOfCheapestCar);

        // 4. Средняя стоимость автомобилей заданной модели:
        String modelToFind = "Toyota";
        double averagePrice = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.printf("Средняя стоимость автомобилей модели %s: %.2f%n", modelToFind, averagePrice);
    }
}