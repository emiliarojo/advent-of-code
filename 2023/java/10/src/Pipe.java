import java.util.Objects;

public class Pipe {
    int row, col, dir, dist;

    public Pipe(int row, int col, int dir, int dist) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.dist = dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pipe pipe = (Pipe) o;
        return row == pipe.row && col == pipe.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return String.format("[Pipe: row=%d, col=%d, dir=%d, dist=%d]", row, col, dir, dist);
    }

}
