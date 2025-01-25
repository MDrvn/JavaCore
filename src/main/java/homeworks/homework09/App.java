package homeworks.homework09;

/*
Основная структура программы должна включать следующие элементы:
Car
Базовый автомобиль обладает следующими свойствами: маркой (строка),
моделью (строка), годом выпуска (int), мощностью в лошадиных силах (int),
ускорением (int), подвеской (int) и долговечностью (int).
Каждый отдельный тип автомобиля дополняет эти свойства. Вот типы:
1. PerformanceCar – гоночный автомобиль.
Имеет дополнения addOns (массив строк, по умолчанию – пустой)
Увеличенная мощность двигателя на 50%.
Уменьшенная подвеска на 25%.
2. ShowCar – спортивная машина. Looking cool there, bro.
Включает поле stars (int). (по умолчанию – 0), поле для оценки популярности
автомобиля.

Race
Гонка   имеет   следующие   свойства:   длина   (int),   маршрут   (строка),
призовой   фонд   (int)   и   участники   (коллекция   автомобилей),
• CasualRace – обычная гонка.
• DragRace – гонка за самый мощный двигатель. Идеальное переключение
передач — залог победы.
• DriftRace – дрифтовая гонка.

Garage
• Garage – место, где остаются все автомобили, когда они не участвуют в
гонках.   Гараж   также   предоставляет   возможность   модифицировать
припаркованный автомобиль. Включает parkedCars (массив объектов типа Car).
Каждый из представленных классов должен включать:
1. Конструктор пустой и с параметрами;
2. Переопределенный метод toString();
3. Геттеры и сеттеры для полей. Обратить внимание, что поля требуется
сделать private;
4. У классов переопределены методы equals() и hashcode().
Работу с классами проверить в методе main класса App.

Поскольку в условиях задания не было сказано напрямую про создание списков
марок автомобилей и др. и работу с ними, то этот вариант реализации
включает в себя лишь заранее созданные экземпляры классов (что, конечно, лишает разнообразия).
Но при желании все это вполне можно переработать и расширить (например, добавить ручной ввод
автомобиля и его свойств с последующим сохранением, попробовать предсказывать, кто победит и почему,
изменить проверку в классе App - сейчас она немного "ленивая" и не претендует на универсальность,
и т.д.).

HP = Horse Power

Дополнительно был добавлен Drift Skill - его нет в оригинальных условиях задачи.
Drift Race без Drift Skill совсем не то)
*/

public class App {
    public static void main(String[] args) {
        // Создание экземпляров автомобилей:
        PerformanceCar performanceCar = new PerformanceCar("Ferrari", "488", 2020, 720, 2, 80, 70);
        performanceCar.addAddOn("Spoiler");
        performanceCar.addAddOn("Nitro");

        ShowCar showCar = new ShowCar("Lamborghini", "Aventador", 2019, 770, 3, 85, 80, 5);

        // Создание гонки:
        Race casualRace = new CasualRace(500, "Mountain Track", 10000);
        casualRace.addParticipant(performanceCar);
        casualRace.addParticipant(showCar);

        // Создание гаража:
        Garage garage = new Garage();
        garage.parkCar(performanceCar);
        garage.parkCar(showCar);

        // Вывод информации:
        System.out.println("Performance Car:");
        System.out.println(performanceCar);

        System.out.println("\nShow Car:");
        System.out.println(showCar);

        System.out.println("\nCasual Race:");
        System.out.println(casualRace);

        System.out.println("\nGarage:");
        System.out.println(garage);
    }
}