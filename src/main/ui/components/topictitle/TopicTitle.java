package ui.components.topictitle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Topic;
import ui.App;
import ui.components.popups.ConfirmPopup;

import java.io.IOException;

// A clickable topic with edit/delete button
public class TopicTitle extends AnchorPane {
    private StringProperty name = new SimpleStringProperty();
    private Topic topic;

    @FXML
    private Label nameLabel;

    // EFFECTS: Creates a new topic title
    public TopicTitle(Topic topic) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TopicTitle.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();

            this.topic = topic;
            this.name.set(topic.getName());
            nameLabel.setText(topic.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Deletes the topic
    @FXML
    void delete(ActionEvent event) {
        ConfirmPopup confirmDeletePopup = new ConfirmPopup((confirm, object) -> {
            if (confirm) {
                App.get().getTopicManager().removeTopic(topic);
                App.get().getMainController().renderTopics();
            }
            object.close();
            return null;
        });
        confirmDeletePopup.show();
    }

    // EFFECTS: Change to the topic screen
    @FXML
    void handleClick(MouseEvent event) {
        App app = App.get();
        app.setCurrentTopic(topic);
        app.setScreen(App.Screen.TOPIC_SCREEN);
    }
}
