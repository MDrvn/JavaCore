package homeworks.homework09;

import java.util.ArrayList;
import java.util.List;

public abstract class Race {
    private int length;                // Длина трассы
    private String route;              // Маршрут
    private int prizePool;             // Призовой фонд
    private List<Car> participants;    // Участники гонки

    // Конструктор:
    public Race(int length, String route, int prizePool) {
        this.length = length;
        this.route = route;
        this.prizePool = prizePool;
        this.participants = new ArrayList<>();
    }

    // Геттеры и сеттеры:
    public int getLength() {
        return length;
    }

    public String getRoute() {
        return route;
    }

    public int getPrizePool() {
        return prizePool;
    }

    public List<Car> getParticipants() {
        return participants;
    }

    // Добавление участника:
    public void addParticipant(Car car) {
        this.participants.add(car);
    }

    // Метод для определения победителя (см. в наследниках):
    public abstract Car determineWinner();

    // Переопределение toString():
    @Override
    public String toString() {
        return String.format("Race on %s, Length: %d, Prize Pool: %d, Participants: %d",
                route, length, prizePool, participants.size());
    }
}