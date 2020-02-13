package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TopicTest {
    private Topic topic;

    @Test
    public void testConstructor() {
        topic = new Topic("Biology");
        assertEquals("Biology", topic.getName());
        assertEquals(0, topic.getFlashCardSet().size());
    }
}
