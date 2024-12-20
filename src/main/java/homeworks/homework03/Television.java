package homeworks.homework03;

/* Формулировка задания:
Реализовать класс Телевизор. У класса есть поля, свойства и методы.
Проверить работу в классе App, методе main. Обратить внимание на переопределение
метода toString.

Ожидаемый результат:
1.Создан класс  Телевизор;
2. У  класса  есть   поля,  свойства   и   методы.  Поля  желательно  сделать private.
Задать новые значения полям класса можно через конструктор и setters.
3. В классе переопределен метод  toString.
4. Создан класс  App с методом main.
5.   В   методе  main  класса  App  создано   несколько   экземпляров   класса Телевизор
и   проверено,   как   распечатываются   заполненные   данные   об экземплярах класса.
6. Дополнительно. Задавать параметры класса Телевизор с клавиатуры или случайным числом.

Выбранные поля класса Телевизор: бренд, размер экрана,
стоимость, разрешение экрана, цвет телевизора, наличие/отсутствие Смарт-ТВ.

Что можно сделать дополнительно:
Попробовать решить проблемы с ошибками ввода, из-за которых программа будет "вылетать".

P.S. Возможно реализация немного сумбурная и в чем-то излишняя -
это по-прежнему попытка разобраться, как все работает.
*/

public class Television {
    // Добавляем поля класса (private, как предлагается в условии):

    private String brand;
    private int screenSize;
    private double price;
    private String resolution;
    private String color;
    private boolean isSmart;

    // Создаем конструктор с параметрами для инициализации полей:

    public Television(String brand, int screenSize, boolean isSmart,
                      double price, String resolution, String color) {
        this.brand = brand;
        this.screenSize = screenSize;
        this.price = price;
        this.resolution = resolution;
        this.color = color;
        this.isSmart = isSmart;
    }

    // Задаем дефолтный конструктор (без параметров):

    public Television() {
        this.brand = "Unknown";
        this.screenSize = 0;
        this.price = 0.0;
        this.resolution = "Unknown";
        this.color = "Unknown";
        this.isSmart = false;
    }

    // Используем геттеры и сеттеры (на свойства):
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    // Переопределяем метод toString для "красивого" отображения информации об объекте:

    @Override
    public String toString() {
        return "Телевизор {" +
                "Бренд='" + brand + '\'' +
                ", Размер экрана=" + screenSize + " дюймов" +
                ", Стоимость=" + price + " руб." +
                ", Разрешение='" + resolution + '\'' +
                ", Цвет='" + color + '\'' +
                ", Смарт=" + (isSmart ? "Да" : "Нет") +
                '}';
    }

    // Тест.Используем метод main для тестирования класса (пока без App - он дальше):
    public static void main(String[] args) {
        // Создаем объект с использованием конструктора (с параметрами):
        Television tv1 = new Television("Samsung", 55, true, 49999.99, "4K", "Черный");

        // Создаем объект с использованием дефолтного конструктора:
        Television tv2 = new Television();

        // Устанавливаем значения с использованием сеттеров:
        tv2.setBrand("LG");
        tv2.setScreenSize(65);
        tv2.setPrice(159999.99);
        tv2.setResolution("8K");
        tv2.setColor("Зеленый");
        tv2.setSmart(false);

        // Выводим информацию об объектах:
        System.out.println(tv1);
        System.out.println(tv2);
    }
}