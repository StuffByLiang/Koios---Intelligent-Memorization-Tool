package ui.views.testview;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.controllers.TestingController;

import java.io.IOException;

// Testing View Component, holds all components related to testing
public class TestingView extends AnchorPane {
    private TestingController controller;

    public TestingView(StackPane root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TestingView.fxml"));
            loader.setRoot(this);
            loader.load();

            controller = loader.getController();

            this.prefWidthProperty().bind(root.widthProperty());
            this.prefHeightProperty().bind(root.heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TestingController getController() {
        return controller;
    }
}
