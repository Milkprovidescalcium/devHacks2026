#### Inspiration  

Lowkey we kinda stink at lockin in, that's why we looked for solution to this real life problem. Behold, Hocus Focus!

#### What it does  

Just run and forget, dont worry about any extra inputs. Just input what you're working on and Hocus Focus will track your screen in 
the background, reminding you to get back on track whenever you lose focus.

#### How we built it

With a Java Jframe front-end and a Python backend with an API connection to Google Gemini.
The front and backend's commuicate with eachother via text files, Python will handle reading the screen and 
prompting Gemini to determine whether the user is distracted. Java will then recieve this message and send a popup
reminder using Jframe accordingly.

#### Challenges we ran into

As first year compsci students we have limited if any experience with API's, packages, imports, and full stack development. 
We had to do lots of research pulling from external sources in order to make this possible.

A large problem we have is Gemini rate limiting our request. As of today Google Gemini limits API requests to 20 per day. This gave us problems
in testing due to us being kinda broke and not able get Gemini pro.

#### Accomplishments that we're proud of

Successfully connecting to an API for the first time and coming up with our makeshift "communication" between the frontend/backend using
txt files were among the things we learned and were proud of

#### What we learned

We learned alot of things about full-stack development and etc. Out of class learning is an exciting expereince
and it's super sick to have opportunites to pursue it and we will defo be back for more Hackathons!

#### What's next for Hocus Focus -- AI Focus App  

probs just stuff it away in one of our laptops. I don't think this is okay to launch since it's an AI tracking your screen and I'm
sure that comes with it's legal issues, but it's an exciting prototype and premise and who knows how far an idea can go?



## **BEFORE YOU CLONE**:  

cd to /backend/  
### Run:  
- `python -m venv venv ` 
- `.\venv\Scripts\activate`  or `source venv/bin/activate.fish`
- `pip install -r requirements.txt`



for debugging: `backend\venv\Scripts\python.exe backend\bridge.py`  

The .bat file is for Windows and the .sh is for Linux but only for fish terminals cuz that's what I use screwwww bash

The run_appTest.sh is like the run_app.sh but is better cuz the assets work so just run that one not the old one...
