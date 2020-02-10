package model;

// represents a topic that contains flash cards
public class Topic {
    private String name;
    private FlashCardManager flashCardManager;

    // EFFECTS: creates a new topic with a name and empty list of flashcards
    public Topic(String name) {
        this.name = name;
        flashCardManager = new FlashCardManager();
    }

    public FlashCardManager getFlashCardManager() {
        return flashCardManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
