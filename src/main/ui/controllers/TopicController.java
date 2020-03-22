package ui.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.Topic;
import ui.App;
import ui.components.flashcard.FlashCard;
import ui.components.popups.AddFlashCardPopup;

import java.net.URL;
import java.util.ResourceBundle;

// Topic view controller
public class TopicController implements Initializable {
    private ObservableList<FlashCard> flashCards = FXCollections.observableArrayList();
    private Topic topic;
    private AddFlashCardPopup addFlashCardPopup;

    @FXML
    private Label titleLabel;

    @FXML
    private VBox flashcardContainer;

    @FXML
    private JFXButton backButton;

    // EFFECTS: Goes back to the main screen
    @FXML
    private void back(ActionEvent event) {
        App.get().setScreen(App.Screen.MAIN_SCREEN);
    }

    // EFFECTS: Goes to the testing screen
    @FXML
    private void test(ActionEvent event) {
        App.get().setScreen(App.Screen.TESTING_SCREEN);
    }

    // EFFECTS: handles key press
    @FXML
    void handleKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.getKeyCode("N"))) {
            if (!addFlashCardPopup.isVisible()) {
                addFlashCardPopup.show();
            }
        }
    }

    // EFFECTS: Opens up the new flashcard popup
    @FXML
    private void newFlashcard(ActionEvent event) {
        addFlashCardPopup.show();
    }

    // EFFECTS: This code is run when the screen is switched to this
    public void onSwitch() {
        topic = App.get().getCurrentTopic();
        titleLabel.setText("Viewing " + topic.getName() + " Set");
        renderFlashCards();
    }

    // EFFECTS: renders all flashcards
    public void renderFlashCards() {
        flashCards.clear();
        for (model.FlashCard flashCard : topic.getFlashCardSet().getFlashCardList()) {
            flashCards.add(new FlashCard(topic.getFlashCardSet(), flashCard));
        }
        flashcardContainer.getChildren().setAll(flashCards);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addFlashCardPopup = new AddFlashCardPopup(this);
    }
}
