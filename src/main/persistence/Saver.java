package persistence;

import model.TopicManager;

import java.io.*;

// Represents a static class that can save and load the topicManager class
public class Saver {
    // REQUIRES: non-empty filename
    // EFFECTS: serializes topicManager Object to a filename in the /data folder
    public static void save(String fileName, TopicManager topicManager) throws FileNotFoundException, IOException {
        FileOutputStream f = new FileOutputStream(new File(String.format("data/%s", fileName)));
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(topicManager);

        o.close();
        f.close();
    }

    // REQUIRES: non-empty filename
    // EFFECTS: returns a TopicManager Class deserialized from given file name in the /data folder
    public static TopicManager load(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        TopicManager topicManager = new TopicManager();
        FileInputStream f = new FileInputStream(new File(String.format("data/%s", fileName)));
        ObjectInputStream o = new ObjectInputStream(f);

        topicManager = (TopicManager) o.readObject();

        o.close();
        f.close();
        return topicManager;
    }
}