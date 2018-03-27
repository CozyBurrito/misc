#!/usr/bin/python

program = '''jio a, +18
inc a
tpl a
inc a
tpl a
tpl a
tpl a
inc a
tpl a
inc a
tpl a
inc a
inc a
tpl a
tpl a
tpl a
inc a
jmp +22
tpl a
inc a
tpl a
inc a
inc a
tpl a
inc a
tpl a
inc a
inc a
tpl a
tpl a
inc a
inc a
tpl a
inc a
inc a
tpl a
inc a
inc a
tpl a
jio a, +8
inc b
jie a, +4
tpl a
inc a
jmp +2
hlf a
jmp -7'''.split('\n')

a = 1 # or 1 for step 2
b = 0
i = 0

while True:
    if i < 0 or i >= len(program):
        break
    line = program[i]
    inst, reg = line.split(' ', 1)
    if inst == 'hlf':
        exec ('%s //= 2' % reg)
        i += 1
    elif inst == 'tpl':
        exec ('%s *= 3' % reg)
        i += 1
    elif inst == 'inc':
        exec ('%s += 1' % reg)
        i += 1
    elif inst == 'jmp':
        exec ('i = i %s' % reg)
    elif inst == 'jie':
        reg, offset = reg.split(',')
        if eval(reg) % 2 == 0:
            exec ('i = i %s' % offset)
        else:
            i += 1
    elif inst == 'jio':
        reg, offset = reg.split(',')
        if eval(reg) == 1:
            exec ('i = i %s' % offset)
        else:
            i += 1

print ('a = %d, b = %d' % (a, b))
