package ui.views.mainview;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.controllers.MainController;
import ui.controllers.TopicController;
import ui.views.View;

import java.io.IOException;

// Main View Component, holds all components relating to adding/viewing/deleting a topic
public class MainView extends View {
    public MainView(StackPane root) {
        super(root, "MainView.fxml");
    }

    public MainController getController() {
        return (MainController) controller;
    }
}
