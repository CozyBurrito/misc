import re
re_d = re.compile(r'((\d)\2*)')

def replace(match_obj):
    s = match_obj.group(1)
    return str(len(s)) + s[0]

s = '3113322113'
for i in range(40):
    s = re_d.sub(replace,s)
print (len(s))
