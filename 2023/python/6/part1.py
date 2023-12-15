import math
import time

start_time = time.time()

def read_input(file_name):
    races = []
    with open(file_name, 'r') as file:
        lines = file.readlines()

        times = list(map(int, lines[0].split()[1:]))
        distances = list(map(int, lines[1].split()[1:]))

        races = list(zip(times, distances))

    return races


def calculate_ways(races):
    total = 1
    for race_time, record_distance in races:
        a = 1
        b = -race_time
        c = record_distance

        d = b**2 - 4*a*c

        if d < 0:
            return 0

        r1 = (-b + math.sqrt(d)) / (2*a)
        r2 = (-b - math.sqrt(d)) / (2*a)


        ways = 0
        min_range = math.ceil(min(r1, r2))
        max_range = math.floor(max(r1, r2))

        for x in range(min_range, max_range + 1):
            if 0 < x < race_time and x * (race_time - x) > record_distance:
                ways += 1

        total *= ways

    return total

file = 'input.txt'
races = read_input(file)

result = calculate_ways(races)
print(result)

end_time = time.time()
execution_time = end_time - start_time
print(f"Execution Time: ", execution_time)
