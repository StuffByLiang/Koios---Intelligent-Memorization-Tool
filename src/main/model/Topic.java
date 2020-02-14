package model;

import java.io.Serializable;

// represents a topic that contains flash cards
public class Topic implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private FlashCardSet flashCardSet;

    // EFFECTS: creates a new topic with a name and empty list of flashcards
    public Topic(String name) {
        this.name = name;
        flashCardSet = new FlashCardSet();
    }

    public FlashCardSet getFlashCardSet() {
        return flashCardSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
