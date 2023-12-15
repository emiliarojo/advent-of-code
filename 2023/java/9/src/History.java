import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Integer> history;

    public History(List<Integer> history) {
        this.history = new ArrayList<>(history);
    }

    public int getNextValue() {
        List<List<Integer>> sequences = generateSequences();

        for (int i = sequences.size() - 2; i >= 0; i--) {
            List<Integer> seq = sequences.get(i);
            int nextValue = seq.get(seq.size() - 1) + sequences.get(i + 1).get(sequences.get(i + 1).size() - 1);
            seq.add(nextValue);
        }

        return sequences.get(0).get(sequences.get(0).size() - 1);
    }

    public int getPreviousValue() {
        List<List<Integer>> sequences = generateSequences();

        sequences.get(sequences.size() - 1).add(0, 0);

        for (int i = sequences.size() - 2; i >= 0; i--) {
            List<Integer> seq = sequences.get(i);
            int prevValue = seq.get(0) - sequences.get(i + 1).get(0);
            seq.add(0, prevValue);
        }

        return sequences.get(0).get(0);
    }

    private List<List<Integer>> generateSequences() {
        List<List<Integer>> sequences = new ArrayList<>();
        sequences.add(new ArrayList<>(history));

        while (!allZeroes(sequences.get(sequences.size() - 1))) {
            List<Integer> lastSequence = sequences.get(sequences.size() - 1);
            List<Integer> newSequence = new ArrayList<>();
            for (int i = 0; i < lastSequence.size() - 1; i++) {
                newSequence.add(lastSequence.get(i + 1) - lastSequence.get(i));
            }
            sequences.add(newSequence);
        }

        return sequences;
    }

    private boolean allZeroes(List<Integer> sequence) {
        for (int num : sequence) {
            if (num != 0) return false;
        }
        return true;
    }
}
