package ui.views.topicview;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.controllers.TestingController;
import ui.controllers.TopicController;
import ui.views.View;

import java.io.IOException;

// Topic View Component, displays a topic and actions for its flashcards
public class TopicView extends View {
    public TopicView(StackPane root) {
        super(root, "TopicView.fxml");
    }

    public TopicController getController() {
        return (TopicController) controller;
    }
}
