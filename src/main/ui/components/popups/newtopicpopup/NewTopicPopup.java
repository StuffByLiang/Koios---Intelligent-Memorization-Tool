package ui.components.popups.newtopicpopup;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Topic;
import ui.App;
import ui.components.popups.Popup;
import ui.controllers.MainController;

// A Popup component that displays input for a new topic
public class NewTopicPopup extends Popup {
    private MainController controller;

    @FXML
    private JFXTextField nameTextField;

    // EFFECTS: Creates a new topic
    @FXML
    void create(ActionEvent event) {
        String name = nameTextField.getText();
        Topic topic = new Topic(name);
        App.get().getState().getTopicManager().addTopic(topic);
        this.close();
        controller.renderTopics();
        nameTextField.clear();
    }

    // EFFECTS: Creates the popup
    public NewTopicPopup(MainController controller) {
        super("NewTopic.fxml");
        this.controller = controller;
    }
}
