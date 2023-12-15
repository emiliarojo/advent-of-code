existing_patterns = {}

def count_arrangements(pattern, groups):
    if not groups:
        if "#" in pattern:
            return 0
        else:
            return 1

    if not pattern:
        if not groups:
            return 1
        else:
            return 0

    pattern = (pattern, tuple(groups))
    if pattern in existing_patterns:
        return existing_patterns[pattern]

    arrangements = 0

    if pattern[0] == "." or pattern[0] == "?":
        arrangements += count_arrangements(pattern[1:], groups)

    if pattern[0] == "#" or pattern[0] == "?":
        if groups[0] <= len(pattern):
            if groups[0] == len(pattern) or pattern[groups[0]] != "#":
                if "." not in pattern[:groups[0]]:
                    arrangements += count_arrangements(pattern[groups[0] + 1:], groups[1:])

    existing_patterns[pattern] = arrangements
    return arrangements

def part_1(file_path):
    total = 0
    for line in open(file_path):
        pattern, groups = line.split()
        groups = list(map(int, groups.split(",")))
        total += count_arrangements(pattern, groups)
    return total

def part_2(file_path):
    total = 0
    for line in open(file_path):
        pattern, groups = line.split()
        new_pattern = ""
        new_groups = []
        for _ in range(5):
            new_groups += list(map(int, groups.split(",")))
            new_pattern += pattern + "?"
        # print(new_pattern, new_groups)
        new_pattern = new_pattern[:-1]
        total += count_arrangements(new_pattern, new_groups)
    return total

print("Part 1 Solution:", part_1("input.txt"))
print("Part 2 Solution:", part_2("input.txt"))
