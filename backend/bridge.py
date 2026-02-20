import time
import os
from vision_tracker import capture_screen
from ai_logic import check_user_focus

MAILBOX = "mailbox.txt"

def run_bridge():
    print("Python bridge is active")

    while True: #this will run forever dw its supposed to
        if(os.path.exists(MAILBOX)):

            file_read = open(MAILBOX,"r") # "r" is just read mode. It'll just read the file
            try:
                instruction = file_read.read().strip()
            finally:
                file_read.close() #its like a filereader in java, u gotta try catch and then close it

            if instruction.startswith("GOAL:"):
                user_goal = instruction.replace("GOAL:","") #just from the purpose of the prompt
                print(f"Java is analysing for {user_goal}")

                image_path = capture_screen()
                verdict = check_user_focus(image_path,user_goal)

                file_write = open(MAILBOX, "w")
                try:
                    file_write.write(f"RESULT:{verdict}")
                finally:
                    file_write.close()

                print(f"Verdict sent: {verdict}")

            time.sleep(30)

if __name__ == "__main__": ##this ensures that only THIS FILE can run the thing. 
    run_bridge()
