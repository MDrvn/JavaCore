package homeworks.homework07;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    // Конструктор:
    public Product(String name, double price) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    // Валидация имени продукта:
    protected void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        if (name.matches("\\d+")) {
            throw new IllegalArgumentException("Название продукта не может состоять только из цифр");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Название продукта должно содержать минимум 3 символа");
        }
    }

    // Валидация цены продукта:
    protected void validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть больше 0");
        }
    }

    // Геттеры:
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Переопределение методов:
    @Override
    public String toString() {
        return name + " (" + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}