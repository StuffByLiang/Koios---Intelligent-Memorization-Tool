package ui.views.mainview;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.controllers.MainController;

import java.io.IOException;

// Main View Component, holds all components relating to adding/viewing/deleting a topic
public class MainView extends AnchorPane {
    private MainController controller;

    public MainView(StackPane root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            loader.setRoot(this);
            loader.load();

            controller = loader.getController();

            this.prefWidthProperty().bind(root.widthProperty());
            this.prefHeightProperty().bind(root.heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainController getController() {
        return controller;
    }
}
