package homeworks.homework14;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    // Конструктор:
    public Person(String name, double money) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.name = name;
        this.money = money;
        this.products = new ArrayList<>();
    }

    // Геттеры:
    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    // Покупка продукта:
    public void buyProduct(Product product) {
        if (product.getPrice() <= money) {
            products.add(product);
            money -= product.getPrice();
            System.out.println(name + " купил " + product.getName());
        } else {
            System.out.println(name + " не может позволить себе " + product.getName());
        }
    }

    // Проверяем, куплено ли что-то:
    public boolean hasBoughtAnything() {
        return !products.isEmpty();
    }

    // Переопределение методов:
    @Override
    public String toString() {
        if (products.isEmpty()) {
            return name + " ничего не купил(а)";
        }

        StringBuilder sb = new StringBuilder(name + " купил(а): ");
        for (int i = 0; i < products.size(); i++) {
            sb.append(products.get(i).toString());
            if (i < products.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }
}