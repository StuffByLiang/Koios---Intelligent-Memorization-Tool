package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TopicManagerTest {
    TopicManager topicManager;

    private void checkSetup() {
        assertEquals(3, topicManager.size());
    }

    @BeforeEach
    public void setup() {
        topicManager = new TopicManager();
        topicManager.addTopic(new Topic("Biology"));
        topicManager.addTopic(new Topic("Chemistry"));
        topicManager.addTopic(new Topic("idek"));
    }

    @Test
    public void testConstructor() {
        topicManager = new TopicManager();
        assertEquals(0, topicManager.size());
    }

    @Test
    public void TestGetTopicList() {
        assertEquals(3, topicManager.getTopicList().size());
    }

    @Test
    public void testAddTopic() {
        checkSetup();
        topicManager.addTopic(new Topic("Math"));
        assertEquals(4, topicManager.size());
    }

    @Test
    public void testGetTopic() {
        checkSetup();
        assertEquals("Chemistry", topicManager.getTopic(1).getName());
    }

    @Test
    public void testUpdateTopic() {
        checkSetup();
        topicManager.updateTopic(0, "lmao");
        assertEquals("lmao", topicManager.getTopic(0).getName());
    }

    @Test
    public void testRemove() {
        checkSetup();
        topicManager.removeTopic(0);
        assertEquals(2, topicManager.size());
    }
}
