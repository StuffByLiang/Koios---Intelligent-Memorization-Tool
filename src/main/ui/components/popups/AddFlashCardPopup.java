package ui.components.popups;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.FlashCard;
import ui.App;
import ui.controllers.TopicController;

// Add Flash Card UI component, has a text area for frontside and backside, and a create button
public class AddFlashCardPopup extends Popup {
    private TopicController controller;

    @FXML
    private JFXTextField frontSideTextField;

    @FXML
    private JFXTextField backSideTextField;

    // EFFECTS: Creates and initializes the popup
    public AddFlashCardPopup(TopicController controller) {
        super("AddFlashCard.fxml");
        this.controller = controller;
    }

    // MODIFIES: App.topicManager
    // EFFECTS: Add a flash card to the current selected topic
    @FXML
    void create(ActionEvent event) {
        String frontSide = frontSideTextField.getText();
        String backSide = backSideTextField.getText();
        FlashCard flashCard = new FlashCard(frontSide, backSide);
        App.get().getCurrentTopic().getFlashCardSet().addFlashCard(flashCard);
        this.close();
        controller.renderFlashCards();
        frontSideTextField.clear();
        backSideTextField.clear();
    }
}
