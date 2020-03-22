package ui.components.popups;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.function.BiFunction;

// Confirm popup ui element, prompts yes and no and passes that value to a callback function
public class ConfirmPopup extends Popup {
    private BiFunction<Boolean, ConfirmPopup, Void> callback;

    // EFFECTS: Creates a popup ui element
    public ConfirmPopup(BiFunction<Boolean, ConfirmPopup, Void> callback) {
        super("Confirm.fxml");
        this.callback = callback;
    }

    // EFFECTS: passes true to the callback
    @FXML
    void yes(ActionEvent event) {
        callback.apply(true, this);
    }

    // EFFECTS: passes false to the callback
    @FXML
    void no(ActionEvent event) {
        callback.apply(false, this);
    }
}
