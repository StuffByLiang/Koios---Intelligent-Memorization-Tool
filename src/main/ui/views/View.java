package ui.views;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

// View Component
public class View extends AnchorPane {
    protected Initializable controller;

    // EFFECTS: creates a new view
    public View(StackPane root, String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            loader.setRoot(this);
            loader.load();

            controller = loader.getController();

            this.prefWidthProperty().bind(root.widthProperty());
            this.prefHeightProperty().bind(root.heightProperty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
