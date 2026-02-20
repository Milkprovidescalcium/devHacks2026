#gemini api connection goes here
import os
from google import genai
from dotenv import load_dotenv

load_dotenv() #loads the data from the .env file and makes it availible for 'os' to use

client = genai.Client(api_key = os.getenv("GEMINI_API_KEY"))

def check_user_focus(image_path,user_goal): #reads the screenshot and sends to to gemini
    file = open(image_path,"rb") #"rb" mean read binary. It opens the image as binary data

    try:
        image_data = file.read()

        prompt = f"Goal: {user_goal}. Answer only 'FOCUS' or 'DISTRACTED'." #tells gemini.. to answer in either of those one word answers. The f means its just a printf basically
        response = client.models.generate_content(
            model="gemini-flash-latest",
            contents = [prompt,{"inline_data": {"data":image_data,"mime_type":"image/jpeg"}}] #mime_type says what filetype it is. jpeg in this case
        )
        return response.text.strip()

        # import random
        # return random.choice(["FOCUS", "DISTRACTED"]) # <-- google gemini api is down... just do this fornow

    finally:
        file.close()
        print("File closed successfully!!")
