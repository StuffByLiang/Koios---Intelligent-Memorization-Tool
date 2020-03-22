package ui.components.popups;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.FlashCard;
import ui.App;
import ui.controllers.TopicController;

// Edit Flash Card Popup component that contains a frontside and backside text area, as well as confirm button
public class EditFlashCardPopup extends Popup {
    private TopicController controller;
    private FlashCard flashCard;

    @FXML
    private JFXTextField frontSideTextField;

    @FXML
    private JFXTextField backSideTextField;

    // MODIFIES: App.topicManager
    // EFFECTS: updates both sides of the flashcard with new text and closes the popup
    @FXML
    void edit(ActionEvent event) {
        String frontSide = frontSideTextField.getText();
        String backSide = backSideTextField.getText();
        flashCard.setFrontSide(frontSide);
        flashCard.setBackSide(backSide);
        controller.renderFlashCards();
        this.close();
    }

    // EFFECTS: Creates the popup component
    public EditFlashCardPopup(model.FlashCard flashCard) {
        super("EditFlashCard.fxml");
        this.controller = App.get().getTopicController();
        this.flashCard = flashCard;

        frontSideTextField.setText(flashCard.getFrontSide());
        backSideTextField.setText(flashCard.getBackSide());
    }
}
