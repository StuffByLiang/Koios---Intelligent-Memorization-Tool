package ui.components.popups.editflashcardpopup;

import com.jfoenix.controls.JFXTextField;
import exception.InvalidFlashCardException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.FlashCard;
import ui.App;
import ui.components.popups.messagepopup.MessagePopup;
import ui.components.popups.Popup;
import ui.controllers.TopicController;

// Edit Flash Card Popup component that contains a frontside and backside text area, as well as confirm button
public class EditFlashCardPopup extends Popup {
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
        try {
            flashCard.setFrontSide(frontSide);
            flashCard.setBackSide(backSide);
        } catch (InvalidFlashCardException e) {
            new MessagePopup("Can't set the sides to be empty!").show();
        }
        App.get().getTopicController().renderFlashCards();
        this.close();
    }

    // EFFECTS: Creates the popup component
    public EditFlashCardPopup(model.FlashCard flashCard) {
        super("EditFlashCard.fxml");
        this.flashCard = flashCard;

        frontSideTextField.setText(flashCard.getFrontSide());
        backSideTextField.setText(flashCard.getBackSide());
    }
}
