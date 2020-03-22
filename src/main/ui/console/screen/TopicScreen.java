package ui.console.screen;

import model.FlashCard;
import model.FlashCardSet;
import model.Topic;
import ui.console.App;
import ui.console.Util;

// this screen shows all the actions you can do with a topic
public class TopicScreen extends Screen {
    private Topic topic;

    // EFFECTS: constructs a topic screen
    public TopicScreen(App app) {
        super(app);
    }

    // EFFECTS: Gets the current topic
    private Topic getTopic() {
        return topicManager.getTopic(app.getTopicId());
    }

    // EFFECTS: gets the current flash card manager for the current topic
    private FlashCardSet getFlashCardManager() {
        return topicManager.getFlashCardManager(app.getTopicId());
    }

    // EFFECTS: displays the topic menu
    @Override
    public void display() {
        System.out.println("\nSelect an option: ");
        System.out.println("\t1) Test yourself");
        System.out.println("\t2) Add Flashcards");
        System.out.println("\t3) View Flashcards");
        System.out.println("\t4) Delete Flashcards");
        System.out.println("\t5) Delete this topic");
        System.out.println("\tb) Back");
        System.out.println("\tq) Quit Program");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommand(String command) {
        switch (command) {
            case "1":
                beginTest();
                break;
            case "2":
                addFlashCard();
                break;
            case "3":
                viewFlashCard();
                break;
            case "4":
                deleteFlashCard();
                break;
            case "5":
                deleteTopic();
                break;
            case "b":
                app.setScreen(MAIN_SCREEN);
                break;
        }
    }

    // EFFECTS: switches screen to the testing screen
    private void beginTest() {
        app.setScreen(TEST_SCREEN);
    }

    // MODIFIES: this
    // EFFECTS: adds a flash card from user input to the current topic
    private void addFlashCard() {
        Util.displayInputPrompt("What goes on the front side?");
        String frontSide = input.nextLine();
        Util.displayInputPrompt("What goes on the back side?");
        String backSide = input.nextLine();

        FlashCardSet flashCardSet = getFlashCardManager();
        flashCardSet.addFlashCard(new FlashCard(frontSide, backSide));
    }

    // MODIFIES: this
    // EFFECTS: deletes a flash card from user input to the current topic
    private void deleteFlashCard() {
        FlashCardSet flashCardSet = getFlashCardManager();
        for (int i = 0; i < flashCardSet.size(); i++) {
            FlashCard fc = flashCardSet.getFlashCard(i);
            String frontSide = fc.getFrontSide();
            String backSide = fc.getBackSide();
            System.out.println(String.format("\t%d) %s | %s", i + 1, frontSide, backSide));
        }

        boolean loop = true;

        while (loop) {
            Util.displayInputPrompt("Select a flashcard above to delete, 0 to cancel:");
            int command = input.nextInt();
            int id = command - 1;
            input.nextLine();

            if (command == 0) {
                loop = false;
            } else if (flashCardSet.isValidId(id)) {
                flashCardSet.removeFlashCard(id);
                loop = false;
            }
        }
    }

    // EFFECTS: views all flash cards from the current topic
    private void viewFlashCard() {
        FlashCardSet flashCardSet = getFlashCardManager();
        for (int i = 0; i < flashCardSet.size(); i++) {
            FlashCard fc = flashCardSet.getFlashCard(i);
            String frontSide = fc.getFrontSide();
            String backSide = fc.getBackSide();
            System.out.println(String.format("\t%d) %s | %s", i + 1, frontSide, backSide));
        }
        Util.pressAnyKeyToContinue();
    }


    // MODIFIES: this
    // EFFECTS: deletes the current topic
    private void deleteTopic() {
        boolean loop = true;

        while (loop) {
            Util.displayInputPrompt("Are you sure? (y/n)");
            String command = input.nextLine();
            switch (command) {
                case "y":
                    app.setScreen(MAIN_SCREEN);
                    loop = false;
                    topicManager.removeTopic(app.getTopicId());
                    break;
                case "n":
                    loop = false;
            }
        }
    }
}
