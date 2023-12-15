import java.util.List;
import java.util.ArrayList;

public class Game {
  private int id;
  private List<CubeSet> cubeSets;
  private int noRed, noGreen, noBlue;

  public Game(int id) {
    this.id = id;
    cubeSets = new ArrayList<CubeSet>();
    noRed = 0;
    noGreen = 0;
    noBlue = 0;
  }

  public int getId() {
    return id;
  }

  public int getNoRed() {
    return noRed;
  }

  public int getNoGreen() {
    return noGreen;
  }

  public int getNoBlue() {
    return noBlue;
  }

  public void addCubeSet(CubeSet cubeSet) {
    cubeSets.add(cubeSet);
  }

  public boolean isPossible() {
    for (CubeSet cubeSet : cubeSets) {
        if (cubeSet.getRed() > 12 || cubeSet.getGreen() > 13 || cubeSet.getBlue() > 14) {
            return false;
        }
    }
    return true;
  }

  public long calculatePower() {
    int maxRed = 0, maxGreen = 0, maxBlue = 0;
    for (CubeSet cubeSet : cubeSets) {
        maxRed = Math.max(maxRed, cubeSet.getRed());
        maxGreen = Math.max(maxGreen, cubeSet.getGreen());
        maxBlue = Math.max(maxBlue, cubeSet.getBlue());
    }
    return (long) maxRed * maxGreen * maxBlue;
}
}
