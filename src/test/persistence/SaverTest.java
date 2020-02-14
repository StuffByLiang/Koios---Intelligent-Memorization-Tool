package persistence;

import model.FlashCard;
import model.FlashCardSet;
import model.Topic;
import model.TopicManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SaverTest {
    private TopicManager topicManager;

    @BeforeEach
    public void setup() {
        topicManager = new TopicManager();
        topicManager = new TopicManager();
        Topic biology = new Topic("Biology");
        Topic math = new Topic("Math");
        FlashCardSet mathFCS = math.getFlashCardSet();
        FlashCardSet bioFCS = biology.getFlashCardSet();

        bioFCS.addFlashCard(new FlashCard("What is the powerhouse of the cell?", "mitochondria"));
        bioFCS.addFlashCard(new FlashCard("What is the scientific name of humans", "homo sapiens"));

        mathFCS.addFlashCard(new FlashCard("1+1", "2"));
        mathFCS.addFlashCard(new FlashCard("2+2", "4"));
        mathFCS.addFlashCard(new FlashCard("4+4", "8"));

        topicManager.addTopic(math);
        topicManager.addTopic(biology);
    }

    @Test
    public void testSaveLoad() {
        TopicManager topicManagerTest = new TopicManager();
        try {
            Saver.save("test.txt", topicManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            topicManagerTest = Saver.load("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(topicManagerTest.size(), topicManager.size());
        assertEquals(topicManagerTest.getTopicList().size(), topicManager.getTopicList().size());
        assertEquals(topicManagerTest.getTopic(0).getName(), topicManagerTest.getTopic(0).getName());
        assertEquals(topicManagerTest.getTopic(1).getName(), topicManagerTest.getTopic(1).getName());
    }
}
