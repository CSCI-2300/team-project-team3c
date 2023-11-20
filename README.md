# team-project-team3c
team-project-team3c created by GitHub Classroom

Deliverable 2:
- Meet this weekend on zoom or in library
- New Feature: Medium Difficulty 
    - create a Difficulty level abstraction that is implemented by the different levels
    - create UML diagram this weekend and have her check it during zoom hours on Tuesday

Issues to Fix (from GitHub of Deliverable1):
- Model logic should be in the model
    - You have a good start on breaking up the design into model, view, and controller. I think your controller contains a lot of the model logic. For example, the collection of words should be in the model, and methods that manipulate the collection of words should also be in the model. Then, you can have your view be an observer of the model, and the model can notify observers when things change.
    - Controller class: Lines 89 - 94

- Move your word collection files into the app directory of your repository, and remove the path to that file:
    - Main App class: Line 10, add the file extension: "PlayEasy.txt"

- Unused Import in Line 4 of Word.java
  
