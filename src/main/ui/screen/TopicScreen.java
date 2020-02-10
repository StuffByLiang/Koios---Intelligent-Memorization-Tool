package ui.screen;

import model.FlashCard;
import model.FlashCardManager;
import model.Topic;
import ui.App;
import ui.Util;

public class TopicScreen extends Screen {
    private Topic topic;

    public TopicScreen(App app) {
        super(app);
    }

    private Topic getTopic() {
        return topicManager.getTopic(app.getTopicId());
    }

    private FlashCardManager getFlashCardManager() {
        return topicManager.getFlashCardManager(app.getTopicId());
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

    private void beginTest() {

    }

    private void addFlashCard() {
        Util.displayInputPrompt("What goes on the front side?");
        String frontSide = input.nextLine();
        Util.displayInputPrompt("What goes on the back side?");
        String backSide = input.nextLine();

        FlashCardManager flashCardManager = getFlashCardManager();
        flashCardManager.addFlashCard(new FlashCard(frontSide, backSide));
    }

    private void deleteFlashCard() {
        FlashCardManager flashCardManager = getFlashCardManager();
        for (int i = 0; i < flashCardManager.size(); i++) {
            FlashCard fc = flashCardManager.getFlashCard(i);
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
            } else if (flashCardManager.isValidId(id)) {
                flashCardManager.removeFlashCard(id);
                loop = false;
            }
        }
    }

    private void viewFlashCard() {
        FlashCardManager flashCardManager = getFlashCardManager();
        for (int i = 0; i < flashCardManager.size(); i++) {
            FlashCard fc = flashCardManager.getFlashCard(i);
            String frontSide = fc.getFrontSide();
            String backSide = fc.getBackSide();
            System.out.println(String.format("\t%d) %s | %s", i + 1, frontSide, backSide));
        }
        Util.pause();
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
