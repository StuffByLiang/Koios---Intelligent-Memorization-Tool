package ui.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Tester;
import model.Topic;
import ui.App;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Testing Screen Controller
public class TestingController implements Initializable {

    @FXML
    private Label titleLabel;

    @FXML
    private Text flashCardText;

    @FXML
    private Text messageText;

    @FXML
    private JFXTextField answerTextField;

    @FXML
    private AnchorPane correctBar;

    @FXML
    private AnchorPane incorrectBar;

    @FXML
    private AnchorPane remainingBar;

    @FXML
    private Label correctLabel;

    @FXML
    private Label incorrectLabel;

    @FXML
    private Label remainingLabel;

    private Topic topic;
    private Tester tester;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox container = (HBox) incorrectBar.getParent();
        messageText.wrappingWidthProperty().bind(container.widthProperty().subtract(3));
        flashCardText.wrappingWidthProperty().bind(container.widthProperty().subtract(3));
    }

    // EFFECTS: goes to the topic screen
    @FXML
    void back(ActionEvent event) {
        App.get().setScreen(App.Screen.TOPIC_SCREEN);
    }

    // EFFECTS: displays next flashcard
    @FXML
    void next(ActionEvent event) {
        if (tester.isFinished()) {
            return;
        }
        showMessage("The answer was " + tester.getBackSide());
        messageText.setFill(Color.web("#f44336"));
        tester.skip();
        updateDisplay();
    }

    // EFFECTS: submits answer and shows if its correct or not
    @FXML
    void submit(ActionEvent event) {
        if (tester.isFinished()) {
            return;
        }
        String answer = answerTextField.getText();
        if (tester.isRightAnswer(answer)) {
            showCorrectMessage();
        } else {
            showIncorrectMessage();
        }
        updateDisplay();
    }

    // EFFECTS: Displays Correct!
    private void showCorrectMessage() {
        showMessage("Correct!");
        messageText.setFill(Color.web("#4CAF50"));
    }

    // EFFECTS: Displays Incorrect!
    private void showIncorrectMessage() {
        showMessage("Incorrect!");
        messageText.setFill(Color.web("#f44336"));
    }

    // EFFECTS: clears the message if a key is typed
    @FXML
    void keyTyped(KeyEvent event) {
        if (messageText.getText() != "" && !event.getCode().equals(KeyCode.ENTER)) {
            showMessage("");
        }
    }

    // EFFECTS: shows a given msg in the msg box
    private void showMessage(String msg) {
        messageText.setText(msg);
    }

    // EFFECTS: this code runs when the screen is switched to this
    public void onSwitch() {
        topic = App.get().getState().getCurrentTopic();
        titleLabel.setText(topic.getName() + " Test");

        beginTest();
    }

    // EFFECTS: begins the test
    private void beginTest() {
        tester = new Tester(topic.getFlashCardSet());
        showMessage("");
        updateDisplay();
    }

    // EFFECTS: updates display
    private void updateDisplay() {
        checkIfFinished();
        updateProgressBar();
        clearInput();
        if (!tester.isFinished()) {
            flashCardText.setText(tester.getFrontSide());
        }
    }

    // EFFECTS: redraws the progress bar
    private void updateProgressBar() {
        int numRight = tester.getNumRightAnswers();
        int numSkipped = tester.getNumSkipped();
        int numRemaining = tester.getNumRemaining();
        int total = tester.getTotalCards();

        HBox parent = (HBox) correctBar.getParent();
        correctBar.setPrefWidth(parent.getWidth() * numRight / total);
        incorrectBar.setPrefWidth(parent.getWidth() * numSkipped / total);
        remainingBar.setPrefWidth(parent.getWidth() * numRemaining / total);

        correctLabel.setText(numRight + " correct");
        incorrectLabel.setText(numSkipped + " skipped");
        remainingLabel.setText(numRemaining + " remaining");
    }

    // EFFECTS: if finished, display finished
    private void checkIfFinished() {
        if (tester.isFinished()) {
            playSound();

            int numRight = tester.getNumRightAnswers();
            int total = tester.getTotalCards();
            flashCardText.setText("Finished!\n" + String.format("Your score is: %d out of %d", numRight, total));
            clearInput();
        }
    }

    // EFFECTS: plays a tada sound
    private void playSound() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("../sounds/tada.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: clears the answer input
    private void clearInput() {
        answerTextField.clear();
    }
}
