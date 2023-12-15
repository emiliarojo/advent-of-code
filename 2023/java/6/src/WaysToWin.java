import java.util.ArrayList;
import java.util.List;

public class WaysToWin {
      private List<Race> races;

    public WaysToWin(List<Race> races) {
        this.races = races;
    }

    public List<Long> calculateWaysToWin() {
        List<Long> waysToWin = new ArrayList<>();

        for (Race race : races) {
            long winningWays = 0;
            for (long i = 0; i < race.getTime(); i++) {
                long speed = i;
                long time = race.getTime() - i;
                long distance = speed * time;

                if (distance > race.getRecord()) {
                    winningWays++;
                }
            }
            waysToWin.add(winningWays);
        }
        return waysToWin;
    }

    public long calculateWaysToWinForSingleRace() {
        if (races.isEmpty()) return 0;
        Race race = races.get(0);

        long winningWays = 0;
        for (long i = 1; i < race.getTime(); i++) {
            long distance = i * (race.getTime() - i);
            if (distance > race.getRecord()) {
                winningWays++;
            }
        }
        return winningWays;
    }

    public long calculateTotal() {
        long total = 1;
        List<Long> ways = calculateWaysToWin();
        for (long way : ways) {
            total *= way;
        }
        return total;
    }

}
