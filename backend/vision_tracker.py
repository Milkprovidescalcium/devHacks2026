import mss
import cv2
import numpy as np

def capture_screen():
    screenshot = mss.mss()

    try:
        monitor = screenshot.monitors[1]
        screenshot.shot(output = 'screen.png')

        image = cv2.imread('screen.png')
      

    finally:
        screenshot.close()

    return 'screen.png'
