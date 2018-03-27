import win32api, math, time


def mousePos(cord=(0,0)):
    win32api.SetCursorPos(cord)

for x, y in zip(range(100), range(100)):
    mousePos((960+x,540+y))
    time.sleep(.00005)
