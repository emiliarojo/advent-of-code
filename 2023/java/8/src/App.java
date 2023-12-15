import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        try {
            Network network = parseInput("input.txt");
            System.out.println("Part 1 Solution: " + part1Solution(network));
            System.out.println("Part 2 Solution: " + part2Solution(network));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Network parseInput(String filePath) throws IOException {
        Network network = new Network();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String instructions = reader.readLine().trim();
        network.setInstructions(instructions);
        reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" = ");
            String nodeName = parts[0];
            String[] connections = parts[1].substring(1, parts[1].length() - 1).split(", ");
            network.addNode(nodeName, connections[0], connections[1]);
        }

        reader.close();
        return network;
    }

    public static int part1Solution(Network network) {
        return network.findPathLength("AAA", 'Z');
    }

    public static BigInteger part2Solution(Network network) {
        return network.findLCMPathLengths();
    }
}
