package ui.screen;

import model.Topic;
import ui.App;
import ui.Util;

public class MainScreen extends Screen {
    // EFFECTS: constructs a main screen
    public MainScreen(App app) {
        super(app);
    }

    // EFFECTS: displays main screen
    @Override
    public void display() {
        System.out.println("\nSelect a topic for more options: ");
        for (int i = 0; i < topicManager.size(); i++) {
            String name = topicManager.getTopic(i).getName();
            System.out.println(String.format("\t%d) %s", i + 1, name));
        }
        System.out.println("\tc) Create a topic");
        System.out.println("\tq) Quit Program");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommand(String command) {
        if (command.equals("c")) {
            createTopic();
        } else if (topicManager.isValidId(command)) {
            openTopic(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a new topic from user input
    private void createTopic() {
        Util.displayInputPrompt("Enter a name:");
        String name = input.nextLine();
        topicManager.addTopic(new Topic(name));
    }

    // MODIFIES: this
    // EFFECTS: Goes to specified topic screen
    private void openTopic(String command) {
        int topicId = Integer.parseInt(command) - 1;
        app.setScreen(TOPIC_SCREEN);
        app.setTopicId(topicId);
    }
}
