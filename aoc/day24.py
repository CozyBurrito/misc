values = []
with open("input24.txt", "r") as f:
    for line in f.readlines():
        values.append(int(line.replace("\n","")))

total = sum(values)
third = total / 3
fourth = total / 4

print ("Sum of all values =", total)
print ("Each section for Part 1 must therefore weigh", third)
print ("Each section for Part 2 must therefore weigh", fourth)

quantum_t = -1
quantum_f = -1
for z in range(len(values) - 1, -1, -1):
    a = values[z]
    for y in range(z - 1, -1, -1):
        b = values[y]
        for x in range(y - 1, -1, -1):
            c = values[x]
            for w in range(x - 1, -1, -1):
                d = values[w]
                if a+b+c+d == fourth:
                    prod = a*b*c*d
                    if quantum_f == -1 or quantum_f > prod:
                        quantum_f = prod
                for v in range(w - 1, -1, -1):
                    e = values[v]
                    if a+b+c+d+e == fourth:
                        prod = a*b*c*d*e
                        if quantum_f == -1 or quantum_f > prod:
                            quantum_f = prod
                    for u in range(v - 1, -1, -1):
                        f = values[u]
                        if a+b+c+d+e+f == third:
                            prod = a*b*c*d*e*f
                            if quantum_t == -1 or quantum_t > prod:
                                quantum_t = prod


print ("Quantum Entanglement of Part 1",quantum_t)
print ("Quantum Entanglement of Part 2",quantum_f)
