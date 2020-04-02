package ui.components.flashcardlistitem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.FlashCardSet;
import ui.App;
import ui.components.popups.confirmpopup.ConfirmPopup;
import ui.components.popups.editflashcardpopup.EditFlashCardPopup;

import java.io.IOException;

// FlashCard UI Element that contains a frontside, backside, and a delete/edit button
public class FlashCardListItem extends HBox {
    private model.FlashCard flashCard;
    private FlashCardSet flashCardSet;

    @FXML
    private Text frontSideText;

    @FXML
    private Text backSideText;

    @FXML
    private AnchorPane frontSidePane;

    @FXML
    private AnchorPane backSidePane;

    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private GridPane gridPane;

    // EFFECTS: creates a flash card
    public FlashCardListItem(FlashCardSet flashCardSet, model.FlashCard flashCard) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlashCardListItem.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();

            this.flashCard = flashCard;
            this.flashCardSet = flashCardSet;
            frontSideText.setText(flashCard.getFrontSide());
            backSideText.setText(flashCard.getBackSide());

            frontSideText.wrappingWidthProperty().bind(gridPane.widthProperty().divide(2).subtract(3));
            backSideText.wrappingWidthProperty().bind(gridPane.widthProperty().divide(2).subtract(3));
//            this.frontSidePane.widthProperty().addListener(observable -> {
//                System.out.println("hello");
//                VBox parent = (VBox) this.getParent();
//                System.out.println(parent.getHeight());
//                System.out.println(this.getHeight());
//            });
            this.prefHeightProperty().bind(frontSidePane.heightProperty());
            VBox parent = (VBox) this.getParent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: App.topicManager
    // EFFECTS: opens up the delete popup. If confirmed, remove the flashcard from the list
    @FXML
    void delete(ActionEvent event) {
        ConfirmPopup confirmDeletePopup = new ConfirmPopup((confirm, object) -> {
            if (confirm) {
                flashCardSet.removeFlashCard(flashCard);
                App.get().getTopicController().renderFlashCards();
            }
            object.close();
            return null;
        });
        confirmDeletePopup.show();
    }

    // EFFECTS: opens up the edit popup
    @FXML
    void edit(ActionEvent event) {
        EditFlashCardPopup editFlashCardPopup = new EditFlashCardPopup(flashCard);
        editFlashCardPopup.show();
    }
}
