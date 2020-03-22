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
import ui.components.popups.MessagePopup;
import ui.components.topictitle.TopicTitle;
import ui.components.popups.NewTopicPopup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// Main Screen Controller
public class MainController implements Initializable {
    private ObservableList<TopicTitle> topicComponents = FXCollections.observableArrayList();
    private NewTopicPopup addTopicPopup;

    @FXML
    private VBox setContainer;

    // EFFECTS: opens up the new set popup
    @FXML
    void newSet(ActionEvent event) {
        addTopicPopup.show();
    }

    // EFFECTS: saves all flashcards
    @FXML
    void save(ActionEvent event) {
        String result = App.get().saveTopicManager();
        new MessagePopup(result).show();
    }

    // EFFECTS: loads saved flashcards
    @FXML
    void load(ActionEvent event) {
        String result = App.get().loadTopicManager();
        new MessagePopup(result).show();
    }

    // EFFECTS: displays topics
    public void renderTopics() {
        try {
            TopicManager topicManager = App.get().getTopicManager();
            List<Topic> topicList = topicManager.getTopicList();

            topicComponents.clear();

            for (Topic topic : topicList) {
                topicComponents.add(new TopicTitle(topic));
            }

            setContainer.getChildren().setAll(topicComponents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTopicPopup = new NewTopicPopup(this);
        renderTopics();
    }
}