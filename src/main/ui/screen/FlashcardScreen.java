package ui.screen;

import model.FlashCardManager;
import model.Topic;
import ui.App;
import ui.Util;

public class FlashcardScreen extends Screen {
    public FlashcardScreen(App app) {
        super(app);
    }

    private FlashCardManager getFlashCardManager() {
        return topicManager.getTopic(app.getTopicId()).getFlashCardManager();
    }

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

    @Override
    public void processCommand(String command) {
        switch (command) {
            case "5":
                deleteTopic();
                break;
            case "b":
                app.setScreen(MAIN_SCREEN);
                break;
        }
    }

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
