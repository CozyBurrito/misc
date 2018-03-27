from PIL import ImageGrab, ImageOps, Image
import os
import time
from numpy import *
import win32api, win32con


def jump():
    win32api.keybd_event(0x20, 0,0,0)
    time.sleep(.05)
    win32api.keybd_event(0x20,0 ,win32con.KEYEVENTF_KEYUP ,0)

def duck():
    win32api.keybd_event(0x28, 0,0,0)
    time.sleep(.1)
    win32api.keybd_event(0x28,0 ,win32con.KEYEVENTF_KEYUP ,0)
    
def grabC():
    c = (740,208,760,228)
    imc = ImageOps.grayscale(ImageGrab.grab(c))
    a = array(imc.getcolors()).sum()
    #print "c =", a
    return a
	
def grabB():
    b = (740,186,760,207)
    imb = ImageOps.grayscale(ImageGrab.grab(b))
    a = array(imb.getcolors()).sum()
    #print "b =", a
    return a

def main():
    cac = grabC()
    b = grabB()
    if cac != 647:
        print "SPACE"
        jump()
    elif b != 747:
        print "DOWN"
        duck()
 
if __name__ == '__main__':
    while 1 == 1:
        main()

