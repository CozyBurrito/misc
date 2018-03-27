import re

ref = { "children": lambda x: x == 3,
        "cats": lambda x: x > 7,
        "trees": lambda x: x > 3,
        "samoyeds": lambda x: x == 2,
        "akitas": lambda x: x == 0,
        "vizslas": lambda x: x == 0,
        "pomeranians": lambda x: x < 3,
        "goldfish": lambda x: x < 5,
        "cars": lambda x: x == 2,
        "perfumes": lambda x: x == 1 }

with open('input.txt', 'r') as fh:
    p = re.compile(r'^Sue ([0-9]+): ([A-Za-z]+): ([0-9]+), ([A-Za-z]+): ([0-9]+), ([A-Za-z]+): ([0-9]+)$')
    for l in fh:
        match = p.findall(l.strip())[0]
        nr = match[0]
        things = dict(zip([name for i, name in enumerate(match[1:]) if i % 2 == 0],
                          [int(count) for i, count in enumerate(match[1:]) if i % 2 == 1]))

        if sum([ref[k](v) and 1 or 0 for k,v in things.items()]) == 3: print (nr, things)
