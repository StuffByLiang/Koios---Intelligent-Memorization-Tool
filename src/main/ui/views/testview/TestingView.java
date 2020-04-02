package ui.views.testview;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.controllers.TestingController;
import ui.views.View;

import java.io.IOException;

// Testing View Component, holds all components related to testing
public class TestingView extends View {
    public TestingView(StackPane root) {
        super(root, "TestingView.fxml");
    }

    public TestingController getController() {
        return (TestingController) controller;
    }
}
