package ui.console.screen;

import model.FlashCardSet;
import model.Tester;
import ui.console.App;
import ui.console.Util;

// screen that allows user to test themselves
public class TestScreen extends Screen {
    Tester tester;

    // EFFECTS: constructs a testing screen
    public TestScreen(App app) {
        super(app);
    }

    // MODIFIES: this
    // EFFECTS: this method runs when the app screen is changed to this screen
    @Override
    public void init() {
        super.init();
        FlashCardSet fcm = app.getTopicManager().getFlashCardSet(app.getTopicId());
        tester = new Tester(fcm);
    }

    // EFFECTS: displays the testing screen
    @Override
    public void display() {
        System.out.println("\nWhat is the backside of the following card?");
        System.out.println("\t" + tester.getFrontSide());
        System.out.println("type in \"skip\" to show answer and skip, \"quit\" to go back");
    }

    // MODIFIES: this
    // EFFECTS: this method runs before the screen is displayed
    @Override
    public void runBeforeDisplay() {
        checkIfFinished();
    }

    // MODIFIES: this
    // EFFECTS: changes screen back to the topic screen if all flash cards have been finished
    private void checkIfFinished() {
        if (tester.isFinished()) {
            int numRight = tester.getNumRightAnswers();
            int total = tester.getTotalCards();
            System.out.println(String.format("You're done! you're score is: %d out of %d", numRight, total));
            Util.pressAnyKeyToContinue();
            app.setScreen(TOPIC_SCREEN);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    @Override
    public void processCommand(String command) {
        if (command.equals("skip")) {
            skip();
        } else if (command.equals("quit")) {
            back();
        } else {
            checkAnswer(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the answer is correct, print correct and go to the next flashCard, otherwise print incorrect.
    private void checkAnswer(String answer) {
        if (tester.isRightAnswer(answer)) {
            System.out.println("Correct!!!");
        } else {
            System.out.println("Incorrect.");
        }
    }

    // MODIFIES: this
    // EFFECTS: returns back to the topic screen
    private void back() {
        app.setScreen(TOPIC_SCREEN);
    }

    // MODIFIES: this
    // EFFECTS: prints the right answer and skips to the next flash card
    private void skip() {
        System.out.println("The right answer was:");
        System.out.println("\t" + tester.getBackSide());
        Util.pressAnyKeyToContinue();
        tester.skip();
    }
}
