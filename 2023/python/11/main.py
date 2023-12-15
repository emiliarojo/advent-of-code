from collections import deque
from itertools import combinations

def find_galaxies(grid):
    galaxies = []
    for r, row in enumerate(grid):
        for c, cell in enumerate(row):
            if cell == '#':
                galaxies.append((r, c))
    return galaxies

def expand_universe(grid, factor):
    expanded_universe = []
    rows, cols = len(grid), len(grid[0])

    for r in range(rows):
        expanded_row = grid[r] * factor
        expanded_universe.extend([expanded_row] * factor)

    expanded_cols = [expanded_universe[i][j] for i in range(len(expanded_universe)) for j in range(cols)]
    expanded_universe = ["".join(expanded_cols[i:i + cols]) for i in range(0, len(expanded_cols), cols)]

    return expanded_universe

def bfs(grid, start):
    rows, cols = len(grid), len(grid[0])
    distances = {}
    queue = deque([(start, 0)])
    visited = set([start])

    while queue:
        (r, c), distance = queue.popleft()

        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols and (nr, nc) not in visited:
                visited.add((nr, nc))
                if grid[nr][nc] == '#':
                    distances[(nr, nc)] = distance + 1
                queue.append(((nr, nc), distance + 1))

    return distances

def total_shortest_paths(grid, part):
    galaxy_positions = find_galaxies(grid)
    if part == 1:
        expansion = 2
    else:
        expansion = 1000000

    expanded_universe = expand_universe(grid, expansion)
    total_distance = 0

    for p, q in combinations(galaxy_positions, 2):
        distances = bfs(expanded_universe, p)
        if q in distances:
            total_distance += distances[q]

    return total_distance

with open('input.txt') as f:
    input_lines = [line.strip() for line in f.readlines()]

part_1_result = total_shortest_paths(input_lines, 1)
part_2_result = total_shortest_paths(input_lines, 2)

print("Part 1:", part_1_result)
print("Part 2:", part_2_result)
