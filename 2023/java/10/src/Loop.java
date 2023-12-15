import java.util.ArrayList;
import java.util.List;

public class Loop {
    private List<Pipe> loopPipes;

    public Loop() {
        loopPipes = new ArrayList<>();
    }

    public void addPipe(Pipe pipe) {
        loopPipes.add(pipe);
    }

    public List<Pipe> getLoopPipes() {
        return loopPipes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Loop Pipes:\n");
        for (Pipe pipe : loopPipes) {
            sb.append("[").append(pipe.row).append(", ").append(pipe.col).append("]\n");
        }
        return sb.toString();
    }
}
