package model;

// Represents a flashcard, which has a frontside and a backside
public class FlashCard {
    private String frontSide;
    private String backSide;

    // REQUIRES: front side and back side must not be empty
    // EFFECTS: creates a flash card with a front side and back side
    public FlashCard(String frontSide, String backSide) {
        this.frontSide = frontSide;
        this.backSide = backSide;
    }

    public String getFrontSide() {
        return frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    public void setFrontSide(String frontSide) {
        this.frontSide = frontSide;
    }

    public void setBackSide(String backSide) {
        this.backSide = backSide;
    }
}
