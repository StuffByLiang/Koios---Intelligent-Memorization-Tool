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
    public void testRemoveById() {
        checkSetup();
        topicManager.removeTopic(0);
        assertEquals(2, topicManager.size());
    }

    @Test
    public void testRemoveByTopic() {
        checkSetup();
        Topic newTopic = new Topic("new topic");
        topicManager.addTopic(newTopic);
        assertEquals(4, topicManager.size());
        topicManager.removeTopic(newTopic);
        assertEquals(3, topicManager.size());
    }

    @Test
    public void testIsValidId() {
        checkSetup();
        assertTrue(topicManager.isValidId(0));
        assertTrue(topicManager.isValidId(1));
        assertTrue(topicManager.isValidId(2));
        assertFalse(topicManager.isValidId(-1));
        assertFalse(topicManager.isValidId(3));
    }

    @Test
    public void testIsValidIdString() {
        checkSetup();
        assertTrue(topicManager.isValidId("1"));
        assertTrue(topicManager.isValidId("2"));
        assertTrue(topicManager.isValidId("3"));
        assertFalse(topicManager.isValidId("0"));
        assertFalse(topicManager.isValidId("4"));
        assertFalse(topicManager.isValidId("yo moma"));
    }

    @Test
    public void testGetFlashCardManager() {
        assertEquals(0, topicManager.getFlashCardManager(0).size());
    }
}
