import java.util.*;
import java.awt.Polygon;

public class Grid {
    private char[][] grid;
    private static final int[] row_moves = {0, 1, 0, -1};
    private static final int[] col_moves = {1, 0, -1, 0};
    private int maximumDistance = 1;
    private Set<Pipe> visitedPipes = new HashSet<>();
    private Loop loop = new Loop();


    public Grid(List<String> lines) {
        constructGridFromLines(lines);
    }

    private void constructGridFromLines(List<String> lines) {
        grid = new char[lines.size()][];
        for (int row = 0; row < lines.size(); row++) {
            grid[row] = lines.get(row).toCharArray();
        }
    }

    public int findMaxDistance() {
        Pipe startingPipe = findStartingPipe();
        if (startingPipe == null) {
            return 0;
        }

        loop.addPipe(startingPipe);

        Queue<Pipe> pipesToProcess = new LinkedList<>();
        visitedPipes.add(startingPipe);
        addAdjacentPipesToQueue(startingPipe, pipesToProcess);

        while (!pipesToProcess.isEmpty()) {
            Pipe currentPipe = pipesToProcess.poll();
            if (!visitedPipes.add(currentPipe)) {
                continue;
            }

            if (processPipe(currentPipe, pipesToProcess)) {
                maximumDistance = Math.max(maximumDistance, currentPipe.dist);
                loop.addPipe(currentPipe);
            } else {
                visitedPipes.remove(currentPipe);
            }
        }
        return maximumDistance;
    }

    private void addAdjacentPipesToQueue(Pipe start, Queue<Pipe> queue) {
        for (int i = 0; i < 4; i++) {
            int newRow = start.row + row_moves[i];
            int newCol = start.col + col_moves[i];
            if (isValidPosition(newRow, newCol)) {
                queue.add(new Pipe(newRow, newCol, i, 1));
            }
        }
    }

    private boolean processPipe(Pipe point, Queue<Pipe> queue) {
        char pipeType = grid[point.row][point.col];
        return switch (pipeType) {
            case 'J' -> processJunction(point, queue);
            case '7' -> processSeven(point, queue);
            case 'F' -> processF(point, queue);
            case 'L' -> processL(point, queue);
            case '-' -> processHorizontalPipe(point, queue);
            case '|' -> processVerticalPipe(point, queue);
            default -> false;
        };
    }

    private boolean processJunction(Pipe point, Queue<Pipe> queue) {
        if (point.dir == 0) {
            enqueueNextPoint(queue, point.row + row_moves[3], point.col + col_moves[3], 3, point.dist);
            return true;
        } else if (point.dir == 1) {
            enqueueNextPoint(queue, point.row + row_moves[2], point.col + col_moves[2], 2, point.dist);
            return true;
        }
        return false;
    }

    private boolean processSeven(Pipe point, Queue<Pipe> queue) {
        if (point.dir == 0) {
            enqueueNextPoint(queue, point.row + row_moves[1], point.col + col_moves[1], 1, point.dist);
            return true;
        } else if (point.dir == 3) {
            enqueueNextPoint(queue, point.row + row_moves[2], point.col + col_moves[2], 2, point.dist);
            return true;
        }
        return false;
    }

    private boolean processF(Pipe point, Queue<Pipe> queue) {
        if (point.dir == 3) {
            enqueueNextPoint(queue, point.row + row_moves[0], point.col + col_moves[0], 0, point.dist);
            return true;
        } else if (point.dir == 2) {
            enqueueNextPoint(queue, point.row + row_moves[1], point.col + col_moves[1], 1, point.dist);
            return true;
        }
        return false;
    }

    private boolean processL(Pipe point, Queue<Pipe> queue) {
        if (point.dir == 1) {
            enqueueNextPoint(queue, point.row + row_moves[0], point.col + col_moves[0], 0, point.dist);
            return true;
        } else if (point.dir == 2) {
            enqueueNextPoint(queue, point.row + row_moves[3], point.col + col_moves[3], 3, point.dist);
            return true;
        }
        return false;
    }

    private boolean processHorizontalPipe(Pipe point, Queue<Pipe> queue) {
        if (point.dir == 0 || point.dir == 2) {
            enqueueNextPoint(queue, point.row + row_moves[point.dir], point.col + col_moves[point.dir], point.dir, point.dist);
            return true;
        }
        return false;
    }

    private boolean processVerticalPipe(Pipe point, Queue<Pipe> queue) {
        if (point.dir == 1 || point.dir == 3) {
            enqueueNextPoint(queue, point.row + row_moves[point.dir], point.col + col_moves[point.dir], point.dir, point.dist);
            return true;
        }
        return false;
    }

    private void enqueueNextPoint(Queue<Pipe> queue, int newRow, int newCol, int newDir, int distance) {
        if (isValidPosition(newRow, newCol)) {
            queue.add(new Pipe(newRow, newCol, newDir, distance + 1));
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    private Pipe findStartingPipe() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 'S') {
                    return new Pipe(row, col, -1, 1);
                }
            }
        }
        return null;
    }

    public Loop getLoop() {
        return loop;
    }

    public int countTilesInsideLoop(List<Pipe> loopPipes) {
        Polygon polygon = new Polygon();
        for (Pipe pipe : loopPipes) {
            polygon.addPoint(pipe.col, pipe.row);
        }

        int tilesInside = 0;
        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[row].length; col++) {
                if (polygon.contains(col, row)) {
                    tilesInside++;
                }
            }
        }

        return tilesInside;
    }


}
