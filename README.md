
# CPSC 210 Term Project: Koios - Intelligent Memorization Tool
  
## Summary
[Studies after studies](https://www.sciencedaily.com/releases/2011/06/110615171410.htm) have shown that the **best way to enhance memorization** is by repeated retrieval, ie. testing yourself repeatedly. The best way to do that currently is through flashcards, or by doing practice questions and examples. However, writing flashcards is a tedious act in and of itself, and tracking your progress is pretty much impossible.

**Koios** is an application that allows students to study smarter, not harder. It works by allowing students to create smart flashcards for their courses, and gives a study schedule based on science by tracking test dates. It quantifies your progress, motivating you to study, and uses artificial intelligence to determine which order of flashcards will give you the fastest and most effective memorization.

## Why this project interests me
This project is of interest to me because I find quizlet's progress tracking completely useless, and their flashcard creation system is boring. Also because I need to study for my upcoming midterms.

## User Stories
- As a user, I want to be able to add/delete/edit a flashcard to my collection of flash cards for a topic
- As a user, I want to be able to be tested on flashcards in my collection and view my score
- As a user, I want to be able to view flashcards in my collection
- As a user, I want to be able to add/delete/view different topics so I can separate my flash cards.

## Phase 2
- As a user, I want to be able to save all topics and flash cards automatically when I quit the program
- As a user, I want to be able to load all topics and flash cards automatically when I start the program

## Instructions for Grader
- You can generate the first required event by clicking a topic on the main screen, and then press [N] to open a new flashcard popup
- You can generate the second required event by clicking Add Flashcard on the topic screen
- You can trigger my audio component by finishing all questions in a test
- You can save the state of my application by clicking save on the main screen
- You can reload the state of my application by clicking load on the main screen

## Phase 3 (GUI) Step by Step Instructions

1. Run the Main class under src/main/ui to begin the program
2. You may click load to load the default topics
3. Click on a topic to view its flashcard, or add a topic by clicking New Topic, or delete a topic by clicking delete on a topic
4. Once you have clicked a topic, you can view, add, and edit flashcards.
5. To test yourself, click "Test Yourself!".
6. Enter the correct answer once you're in the test view. You may skip the question if you don't know the answer.
7. When finished, it'll play a sound, and display your score.
8. Click back to go back!

## Phase 4 (Design)

### Task 2
Test and design a class that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.

- Class: main.model.FlashCard
- Test: test.model.FlashCardTest
- Method: constructor, setFrontSide, setBackSide
- The change: removed requires clause specifying the given strings must be non empty, so I throw an InvalidFlashCardException if string is null or empty
- Why: Empty flashcard may not render properly in the UI

### Task 3: Coupling and cohesion analysis
Upon inspection of the model package, it seems like there's no major issues. Each class is responsible for its own thing, and changes to one class do not propagate undetected in other classes.

Therefore, the following changes will be made to the ui class.

1. The ui.App class is a HUGE class that doesn't satisfy the single responsiblity principle. It manages the view and the state. So, I refactored it by extracting all things related to the State into a seperate class and putting a reference to that class in the App class. This makes more sense; Anything related to the model package should have its own class. Furthermore, if any future developer wants to develop this project further, all they have to do to connect the ui to the model is by editing the State class.
2. There was semantic coupling in the MainView, TestingView and TopicView classes. A change in one of those classes would mean I would have to change the other classes equally. I fixed this by extracting duplicate code into an Abstract Class called View.