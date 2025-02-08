package homeworks.homework09;
import java.util.Objects;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int horsepower;
    private int acceleration;
    private int suspension;
    private int durability;
    private int driftSkill; // Поле для навыков дрифта

    // Конструктор по умолчанию:
    public Car() {}

    //Конструктор для автомобилей с параметром driftSkill:

    public Car(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability, int driftSkill) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.suspension = suspension;
        this.durability = durability;
        this.driftSkill = driftSkill;
    }

    // Конструктор для автомобилей без параметра driftSkill (при желании можно сделать проще):

    public Car(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability) {
        this(brand, model, year, horsepower, acceleration, suspension, durability, 0); // driftSkill по умолчанию
    }

    // Геттеры и сеттеры:
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getSuspension() {
        return suspension;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getDriftSkill() {
        return driftSkill;
    }

    public void setDriftSkill(int driftSkill) {
        this.driftSkill = driftSkill;
    }

    // Вычисление общей производительности автомобиля для СasualRace (необязательная вещь):
    public int calculatePerformance() {
        return (this.horsepower / this.acceleration) + (this.suspension + this.durability);
    }

    // Переопределение toString():
    @Override
    public String toString() {
        return String.format("%s %s (%d) - HP: %d, Acceleration: %d, Suspension: %d, Durability: %d, Drift Skill: %d",
                brand, model, year, horsepower, acceleration, suspension, durability, driftSkill);
    }

    // Переопределение equals() и hashCode():
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
                horsepower == car.horsepower &&
                acceleration == car.acceleration &&
                suspension == car.suspension &&
                durability == car.durability &&
                driftSkill == car.driftSkill &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, horsepower, acceleration, suspension, durability, driftSkill);
    }
}