package ui.components.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.function.BiFunction;

// A popup that can display a given message
public class MessagePopup extends Popup {
    @FXML
    private Label messageLabel;

    // EFFECTS: closes the popup
    @FXML
    void close(ActionEvent event) {
        this.close();
    }

    // EFFECTS: creates a new message popup
    public MessagePopup(String message) {
        super("Message.fxml");
        messageLabel.setText(message);
    }
}
