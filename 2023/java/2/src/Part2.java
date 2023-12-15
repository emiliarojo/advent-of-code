import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
    public static void main(String[] args) {
        String filePath = "input.txt";
        long total = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Game game = parseGameLine(line);
                total += game.calculatePower();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(total);
    }

    private static Game parseGameLine(String line) {
        String[] parts = line.split(": ");
        int gameId = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
        Game game = new Game(gameId);

        String[] cubeSets = parts[1].split("; ");
        for (String set : cubeSets) {
            CubeSet cubeSet = parseCubeSet(set);
            game.addCubeSet(cubeSet);
        }

        return game;
    }

    private static CubeSet parseCubeSet(String set) {
        String[] cubes = set.split(", ");
        int red = 0, green = 0, blue = 0;

        for (String cube : cubes) {
            String[] parts = cube.split(" ");
            int count = Integer.parseInt(parts[0]);
            switch (parts[1]) {
                case "red":
                    red = count;
                    break;
                case "green":
                    green = count;
                    break;
                case "blue":
                    blue = count;
                    break;
            }
        }

        return new CubeSet(red, green, blue);
    }
}
