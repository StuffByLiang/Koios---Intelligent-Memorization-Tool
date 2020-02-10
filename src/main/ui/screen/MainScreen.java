package ui.screen;

import model.Topic;
import ui.App;
import ui.Util;

public class MainScreen extends Screen {
    public MainScreen(App app) {
        super(app);
    }

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

    @Override
    public void processCommand(String command) {
        if (command.equals("c")) {
            createTopic();
        } else if (topicManager.isValidId(command)) {
            openTopic(command);
        }
    }

    private void createTopic() {
        Util.displayInputPrompt("Enter a name:");
        String name = input.nextLine();
        topicManager.addTopic(new Topic(name));
    }

    private void openTopic(String command) {
        int topicId = Integer.parseInt(command) - 1;
        app.setScreen(TOPIC_SCREEN);
        app.setTopicId(topicId);
    }
}
