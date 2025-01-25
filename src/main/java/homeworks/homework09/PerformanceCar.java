package homeworks.homework09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PerformanceCar extends Car {
    private List<String> addOns;

    // Конструктор по умолчанию:
    public PerformanceCar() {
        this.addOns = new ArrayList<>();
    }

    // Конструктор с параметрами:
    public PerformanceCar(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, year, (int) (horsepower * 1.5), acceleration, (int) (suspension * 0.75), durability);
        this.addOns = new ArrayList<>();
    }

    // Геттеры и сеттеры:
    public List<String> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<String> addOns) {
        this.addOns = addOns;
    }

    public void addAddOn(String addOn) {
        this.addOns.add(addOn);
    }

    // Переопределение метода toString():
    @Override
    public String toString() {
        return super.toString() + ", Add-ons: " + (addOns.isEmpty() ? "None" : String.join(", ", addOns));
    }

    // Переопределение equals() и hashCode():
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerformanceCar)) return false;
        if (!super.equals(o)) return false;
        PerformanceCar that = (PerformanceCar) o;
        return Objects.equals(addOns, that.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addOns);
    }
}