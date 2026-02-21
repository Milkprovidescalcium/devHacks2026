
import time
import os
from vision_tracker import capture_screen
from ai_logic import check_user_focus

import atexit


GOAL_FILE = "goals.txt"

VERDICT_FILE = "verdict.txt"

def clear_files():
    files_to_clear = [GOAL_FILE, VERDICT_FILE]
    for filename in files_to_clear:
        try:
            with open(filename, 'w') as f:
                f.truncate(0) 
            print(f"Successfully cleared {filename}")
        except FileNotFoundError:
            pass

atexit.register(clear_files)



def run_bridge():
    print("Python bridge is active")

    while True: #this will run forever dw its supposed to
        if(os.path.exists(GOAL_FILE)):

            file_read = open(GOAL_FILE,"r") # "r" is just read mode. It'll just read the file
            try:
                instruction = file_read.read().strip()
            finally:
                file_read.close() #its like a filereader in java, u gotta try catch and then close it

       

            if instruction.startswith("GOAL:"):
                user_goal = instruction.replace("GOAL:","") #just from the purpose of the prompt
                open(GOAL_FILE, 'w').close() #clear the goal file so it doesnt keep reading it. The terminal goes like freakin whack if u dont have this
                
                print(f"Java is analysing for {user_goal}")

                image_path = capture_screen()
                verdict = check_user_focus(image_path,user_goal)

                file_write = open(VERDICT_FILE, "w")
                try:
                    file_write.write(f"RESULT:{verdict}")
                finally:
                    file_write.close()

                print(f"Verdict sent: {verdict}")

        time.sleep(10)

if __name__ == "__main__": ##this ensures that only THIS FILE can run the thing. 
    run_bridge()
