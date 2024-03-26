# FitApp
**Required Must-Have Stories:**
  - User can view all exercises and make a selection from them
  - User can filter results based on criteria such as difficulty
  - User can favorite an exercise to view it anytime from the favorites page
  - User can start a timer within the app to start an exercise
  - Upon ending the timer, the exercise and its duration is added to the user's activity
  - User is shown a bar graph representing their progress throughout the last 7 days

**Nice to Have Stories:**
  - User can view a breakdown of their activity for each day. This includes what exercise and its duration
  - User can receive recommendations based on what exercises they perform most
  - Icons are used for some text on the exercise info cards, such as what muscle the exercise targets

**Screen Archetypes**
  - Home Screen:
    - Displays user activity
    - Shows a recommended exercise
  - Exercise List:
    - Displays all exercises
    - Allows for filtered results
  - Exercise Select (fragment):
    - Shows a full description of the exercise
    - displays the timer with start and end buttons
  - Favorites Page:
    - Displays all exercises favorited by the user

**Navigation Flows**
  - Navigation Bar:
    - Exercise List
    - Home Page
    - Favorites Page
  - Exercise Screen:
    - ==> Selected Exercise fragment
  - Favorites Screen:
    - ==> Selected Exercise fragment
  - Exercise Fragment:
    - ==> Home (After hitting the end time button)
  
