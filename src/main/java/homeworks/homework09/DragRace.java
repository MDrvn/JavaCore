package homeworks.homework09;

public class DragRace extends Race {
    public DragRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public Car determineWinner() {
        // Победитель определяется по мощности двигателя:
        return getParticipants().stream()
                .max((car1, car2) -> Integer.compare(car1.getHorsepower(), car2.getHorsepower()))
                .orElse(null);
    }
}