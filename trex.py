from PIL import ImageGrab, ImageOps, Image
import os
import time
from numpy import *
import win32api, win32con


def jump():
    win32api.keybd_event(0x20,0,0,0)
    time.sleep(.05)
    win32api.keybd_event(0x20,0,win32con.KEYEVENTF_KEYUP ,0)

def duck():
    win32api.keybd_event(0x28,0,0,0)
    time.sleep(.5)
    win32api.keybd_event(0x28,0,win32con.KEYEVENTF_KEYUP ,0)
    
def grab():
    imc = ImageOps.grayscale(ImageGrab.grab((729,226,760,227)))
    a = array(imc.getcolors()).sum()
    #print a
    return a

def grabB():
    imc = ImageOps.grayscale(ImageGrab.grab((729,200,760,201)))
    a = array(imc.getcolors()).sum()
    #print "b =",a
    return a

def main():
    c = grab()
    b = grabB()
    if c != 278:
        print "SPACE"
        jump()
    elif b != 278:
        print "DOWN"
        duck()
 
if __name__ == '__main__':
    while 1 == 1:
        main()

