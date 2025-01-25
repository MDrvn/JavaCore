package homeworks.homework09;

public class DriftRace extends Race {
    public DriftRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public Car determineWinner() {
        // Победитель определяется по навыкам дрифта:
        return getParticipants().stream()
                .max((car1, car2) -> Integer.compare(car1.getDriftSkill(), car2.getDriftSkill()))
                .orElse(null);
    }
}
