def tilt_north(grid):
    num_rows = len(grid)
    num_cols = len(grid[0])
    for col in range(num_cols):
        for row in range(1, num_rows):
            if grid[row][col] == 'O':
                move_to_row = row
                while move_to_row > 0 and grid[move_to_row - 1][col] == '.':
                    move_to_row -= 1
                if move_to_row != row:
                    grid[row][col] = '.'
                    grid[move_to_row][col] = 'O'

    return grid

def tilt_west(grid):
    num_rows = len(grid)
    num_cols = len(grid[0])
    for row in range(num_rows):
        for col in range(1, num_cols):
            if grid[row][col] == 'O':
                move_to_col = col
                while move_to_col > 0 and grid[row][move_to_col - 1] == '.':
                    move_to_col -= 1
                if move_to_col != col:
                    grid[row][col] = '.'
                    grid[row][move_to_col] = 'O'
    return grid

def tilt_south(grid):
    num_rows = len(grid)
    num_cols = len(grid[0])
    for col in range(num_cols):
        for row in range(num_rows - 2, -1, -1):
            if grid[row][col] == 'O':
                move_to_row = row
                while move_to_row < num_rows - 1 and grid[move_to_row + 1][col] == '.':
                    move_to_row += 1
                if move_to_row != row:
                    grid[row][col] = '.'
                    grid[move_to_row][col] = 'O'
    return grid

def tilt_east(grid):
    num_rows = len(grid)
    num_cols = len(grid[0])
    for row in range(num_rows):
        for col in range(num_cols - 2, -1, -1):
            if grid[row][col] == 'O':
                move_to_col = col
                while move_to_col < num_cols - 1 and grid[row][move_to_col + 1] == '.':
                    move_to_col += 1
                if move_to_col != col:
                    grid[row][col] = '.'
                    grid[row][move_to_col] = 'O'
    return grid

def cycle(grid, num_cycles):
    cycle_history = {}
    for i in range(num_cycles):
        grid_state = tuple(map(tuple, grid))

        if grid_state in cycle_history:
            print(i)
            cycle_length = i - cycle_history[grid_state]
            remaining_cycles = (num_cycles - i) % cycle_length
            return cycle(grid, remaining_cycles)

        cycle_history[grid_state] = i

        grid = tilt_north(grid)
        grid = tilt_west(grid)
        grid = tilt_south(grid)
        grid = tilt_east(grid)
    print('\n'.join(''.join(row) for row in grid))
    return grid


def calculate_load(grid):
    sum = 0
    for row in range(len(grid)):
        for col in range(len(grid[0])):
            if grid[row][col] == 'O':
                sum += len(grid) - row
    return sum



def parse_input(file_path):
    grid = []
    with open(file_path) as f:
        for line in f:
            grid.append(list(line.strip()))
    return grid

def part1_solution(grid):
    tilted_grid = tilt_north(grid)
    return calculate_load(tilted_grid)

def part2_solution(grid):
    cycled_grid = cycle(grid, 1000000000)
    return calculate_load(cycled_grid)

parsed_grid = parse_input('input.txt')

# Exceution time

print("Part 1 Solution:", part1_solution(parsed_grid))
print("Part 2 Solution:", part2_solution(parsed_grid))
