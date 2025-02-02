package homeworks.homework11;

class Car {
    private String number; // Номер автомобиля
    private String model;  // Модель
    private String color;  // Цвет
    private int mileage;   // Пробег
    private double price;  // Стоимость

    // Конструктор:
    public Car(String number, String model, String color, int mileage, double price) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }

    // Геттеры и сеттеры:
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Переопределение метода toString:
    @Override
    public String toString() {
        return String.format("Number: %s, Model: %s, Color: %s, Mileage: %d, Price: %.2f",
                number, model, color, mileage, price);
    }
}