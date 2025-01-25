package homeworks.homework09;

public class CasualRace extends Race {
    public CasualRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public Car determineWinner() {
        /* И пусть победит сильнейший) - смотрим по общей производительности;
        при желании можно поменять просто на того, кто первым придет к финишу
        */
        return getParticipants().stream()
                .max((car1, car2) -> Integer.compare(car1.calculatePerformance(), car2.calculatePerformance()))
                .orElse(null);
    }
}