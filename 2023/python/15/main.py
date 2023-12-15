def hash_algorithm(step):
    value = 0

    for c in step:
        ascii_code = ord(c)
        value += ascii_code
        value *= 17
        value %= 256

    return value

def hashmap(steps):
    boxes = [[] for _ in range(256)]
    for step in steps:
        if '=' in step:
            label, focal_length = step.split('=')
            operation = '='
        else:
            label, operation = step[:-1], step[-1]

        box_index = hash_algorithm(label)

        if operation == '-':
            new_box = []
            for lens in boxes[box_index]:
                if not lens.startswith(label):
                    new_box.append(lens)
            boxes[box_index] = new_box
        else:
            lens = f"{label} {focal_length}"
            found = False

            for i in range(len(boxes[box_index])):
                if boxes[box_index][i].startswith(label):
                    boxes[box_index][i] = lens
                    found = True
                    break

            if found == False:
                boxes[box_index].append(lens)

    return boxes


def calculate_focusing_power(boxes):
    total_focus = 0
    for box_index, box in enumerate(boxes):

        for slot_index, lens in enumerate(box):
            label, focal_length = lens.split()
            focal_length = int(focal_length)
            total_focus += (1 + box_index) * (1 + slot_index) * focal_length

    return total_focus

def parse_input(file_path):
    with open(file_path) as file:
        content = file.read().replace('\n', '')
        steps = content.split(',')

    return steps

def part1_solution(file_path):
    steps = parse_input(file_path)

    sum = 0
    for step in steps:
        sum += hash_algorithm(step)

    return sum

def part2_solution(file_path):
    steps = parse_input(file_path)
    boxes = hashmap(steps)
    focusing_power = calculate_focusing_power(boxes)

    return focusing_power

filename = 'input.txt'
print("Part 1 Solution:", part1_solution(filename))
print("Part 2 Solution:", part2_solution(filename))
