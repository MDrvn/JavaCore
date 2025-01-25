package homeworks.homework09;

import java.util.Objects;

public class ShowCar extends Car {
    private int stars;

    // Конструктор по умолчанию:
    public ShowCar() {
        this.stars = 0;
    }

    // Конструктор с параметрами:
    public ShowCar(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability, int stars) {
        super(brand, model, year, horsepower, acceleration, suspension, durability);
        this.stars = stars;
    }

    // Геттеры и сеттеры:
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    // Переопределение toString:
    @Override
    public String toString() {
        return super.toString() + ", Stars: " + stars;
    }

    // Переопределение equals() и hashCode():
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowCar)) return false;
        if (!super.equals(o)) return false;
        ShowCar showCar = (ShowCar) o;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }
}