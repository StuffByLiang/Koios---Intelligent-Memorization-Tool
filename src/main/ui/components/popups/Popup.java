package ui.components.popups;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import ui.App;

import java.io.IOException;

// Popup component
public abstract class Popup extends JFXDialog {
    // EFFECTS: Creates a new popup component
    public Popup(String file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(this);
            VBox content = loader.load();
            this.setContent(content);
            this.setDialogContainer(App.get().getRoot());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
