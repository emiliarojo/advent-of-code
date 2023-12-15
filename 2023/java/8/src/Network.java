import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Network {
    private final Map<String, Node> nodes = new HashMap<>();
    private String instructions;

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void addNode(String name, String left, String right) {
        nodes.put(name, new Node(left, right));
    }

    public int findPathLength(String start, char endCondition) {
        String currentNode = start;
        int steps = 0;
        int instructionIndex = 0;

        while (!currentNode.endsWith(String.valueOf(endCondition))) {
            Node node = nodes.get(currentNode);
            currentNode = instructions.charAt(instructionIndex) == 'L' ? node.getLeft() : node.getRight();
            instructionIndex = (instructionIndex + 1) % instructions.length();
            steps++;
        }

        return steps;
    }

    public BigInteger findLCMPathLengths() {
        BigInteger result = BigInteger.ONE;

        for (String node : nodes.keySet()) {
            if (node.endsWith("A")) {
                BigInteger pathLength = BigInteger.valueOf(findPathLength(node, 'Z'));
                result = lcm(result, pathLength);
            }
        }

        return result;
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.mod(b));
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }
}
