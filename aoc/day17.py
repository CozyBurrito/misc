from itertools import combinations

inp = list(map(int, open("input.txt").read().splitlines()))

q1 = 0
q2 = 0
for i in range(len(inp)-1):
    for perm in combinations(inp, i):
        if sum(perm) == 150:
            q1 += 1
    if q1 and not q2:
        q2 = q1
print("Q1: {0}\nQ2: {1}".format(q1, q2))
