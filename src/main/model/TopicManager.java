package model;

import ui.Util;

import java.util.ArrayList;
import java.util.List;

// represents a list of topics
public class TopicManager {
    private List<Topic> topicList;

    // EFFECTS: creates an empty list of topics
    public TopicManager() {
        topicList = new ArrayList<>();
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    // REQUIRES: topic must be in list
    // EFFECTS: returns the flash card manager for the specified topic id
    public FlashCardManager getFlashCardManager(int topicId) {
        return getTopic(topicId).getFlashCardManager();
    }

    /* MODIFIES: this
     * EFFECTS: adds topic to the list
     */
    public void addTopic(Topic topic) {
        topicList.add(topic);
    }

    // REQUIRES: the list of topics must contain the topic
    // EFFECTS: returns a topic at pos i of the list
    public Topic getTopic(int i) {
        return topicList.get(i);
    }

    // REQUIRES: the list of topic must contain the topic
    // EFFECTS: updates a topic's name at pos i of the list
    public void updateTopic(int i, String name) {
        Topic topic = getTopic(i);
        topic.setName(name);
    }

    /* REQUIRES: the list of topic must contain the topic
     * MODIFIES: this
     * EFFECTS: removes a flash card from the list at pos i
     */
    public void removeTopic(int i) {
        topicList.remove(i);
    }

    // EFFECTS: returns size of the list
    public int size() {
        return topicList.size();
    }

    // EFFECTS: returns true if given topic id is in the list (zero based)
    public boolean isValidId(int id) {
        return id >= 0 && id < size();
    }

    // EFFECTS: returns true if given topic id is in the list (ones based)
    public boolean isValidId(String id) {
        if (!Util.isNum(id)) {
            return false;
        } else {
            int num = Integer.parseInt(id);
            return num >= 1 && num <= size();
        }
    }
}
