package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.Topic;
import model.TopicManager;
import ui.App;
import ui.components.popups.messagepopup.MessagePopup;
import ui.components.topiclistitem.TopicListItem;
import ui.components.popups.newtopicpopup.NewTopicPopup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// Main Screen Controller
public class MainController implements Initializable {
    private ObservableList<TopicListItem> topicListItems = FXCollections.observableArrayList();
    private NewTopicPopup newTopicPopup;

    @FXML
    private VBox setContainer;

    // EFFECTS: opens up the new set popup
    @FXML
    void newSet(ActionEvent event) {
        newTopicPopup.show();
    }

    // EFFECTS: saves all flashcards
    @FXML
    void save(ActionEvent event) {
        String result = App.get().getState().save();
        new MessagePopup(result).show();
    }

    // EFFECTS: loads saved flashcards
    @FXML
    void load(ActionEvent event) {
        String result = App.get().getState().load();
        new MessagePopup(result).show();
    }

    // EFFECTS: displays topics
    public void renderTopics() {
        try {
            TopicManager topicManager = App.get().getState().getTopicManager();
            List<Topic> topicList = topicManager.getTopicList();

            topicListItems.clear();

            for (Topic topic : topicList) {
                topicListItems.add(new TopicListItem(topic));
            }

            setContainer.getChildren().setAll(topicListItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newTopicPopup = new NewTopicPopup(this);
        renderTopics();
    }
}