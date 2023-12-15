import numpy

def find_reflection(matrix):
    for r in range(1, len(matrix)):
        top = [matrix[r - i - 1] for i in range(r)]
        btm = matrix[r:]

        min_length = min(len(top), len(btm))

        top = top[:min_length]
        btm = btm[:min_length]

        if numpy.array_equal(top, btm):
            return r
    return 0

def find_smudged_reflection(matrix):
    for r in range(1, len(matrix)):
        top = [matrix[r - i - 1] for i in range(r)]
        btm = matrix[r:]

        total_differences = 0

        min_length = min(len(top), len(btm))

        for i in range(min_length):
            differences = 0
            for j in range(min(len(top[i]), len(btm[i]))):
                if top[i][j] != btm[i][j]:
                    differences += 1

            total_differences += differences

        if total_differences == 1:
            return r
    return 0

def parse_input(file_path):
    with open(file_path, 'r') as file:
        blocks = file.read().strip().split('\n\n')
    return [numpy.array([list(line) for line in block.split('\n')]) for block in blocks]

def part1(file_path):
    total = 0
    matrices = parse_input(file_path)

    for matrix in matrices:
        horizontal_reflection = find_reflection(matrix)
        vertical_reflection = find_reflection(numpy.transpose(matrix))

        sum = (horizontal_reflection * 100) + vertical_reflection

        total += sum

    return total

def part2(file_path):
    total = 0
    matrices = parse_input(file_path)

    for matrix in matrices:
        horizontal_reflection = find_smudged_reflection(matrix)
        vertical_reflection = find_smudged_reflection(numpy.transpose(matrix))

        sum = (horizontal_reflection * 100) + vertical_reflection

        total += sum

    return total

file_path = 'input.txt'
print("Part 1 Solution:", part1(file_path))
print("Part 2 Solution:", part2(file_path))
