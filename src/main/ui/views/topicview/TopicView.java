package ui.views.topicview;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.controllers.TopicController;

import java.io.IOException;

// Topic View Component, displays a topic and actions for its flashcards
public class TopicView extends AnchorPane {
    private TopicController controller;

    public TopicView(StackPane root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TopicView.fxml"));
            loader.setRoot(this);
            loader.load();

            controller = loader.getController();

            this.prefWidthProperty().bind(root.widthProperty());
            this.prefHeightProperty().bind(root.heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TopicController getController() {
        return controller;
    }
}
