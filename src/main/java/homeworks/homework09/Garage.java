package homeworks.homework09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Garage {
    private List<Car> parkedCars;

    // Конструктор по умолчанию:
    public Garage() {
        this.parkedCars = new ArrayList<>();
    }

    // Геттеры и сеттеры:
    public List<Car> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public void parkCar(Car car) {
        this.parkedCars.add(car);
    }

    public void unparkCar(Car car) {
        this.parkedCars.remove(car);
    }

    // Переопределение toString():
    @Override
    public String toString() {
        if (parkedCars.isEmpty()) {
            return "Garage is empty.";
        }
        StringBuilder sb = new StringBuilder("Garage contains:\n");
        for (Car car : parkedCars) {
            sb.append(car.toString()).append("\n");
        }
        return sb.toString();
    }

    // Переопределение equals() и hashCode():
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garage)) return false;
        Garage garage = (Garage) o;
        return Objects.equals(parkedCars, garage.parkedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkedCars);
    }
}