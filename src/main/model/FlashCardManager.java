package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of flash cards
public class FlashCardManager {
    private List<FlashCard> flashCardList;

    // EFFECTS: creates an empty list of flash cards
    public FlashCardManager() {
        flashCardList = new ArrayList<>();
    }

    public List<FlashCard> getFlashCardList() {
        return flashCardList;
    }

    /* MODIFIES: this
     * EFFECTS: adds flashcard to the list
     */
    public void addFlashCard(FlashCard flashCard) {
        flashCardList.add(flashCard);
    }

    // REQUIRES: the list of flash card must contain the flash card
    // EFFECTS: get a flash card at pos i from the list
    public FlashCard getFlashCard(int i) {
        return flashCardList.get(i);
    }

    /* REQUIRES: the list of flash card must contain the flash card,
                 and frontSide and backSide must not be empty strings
     * MODIFIES: this ???
     * EFFECTS: updates a flash card in the list at pos i
     */
    public void updateFlashCard(int i, String frontSide, String backSide) {
        FlashCard flashCard = getFlashCard(i);
        flashCard.setFrontSide(frontSide);
        flashCard.setBackSide(backSide);
    }

    /* REQUIRES: the list of flash card must contain the flash card
     * MODIFIES: this
     * EFFECTS: removes a flash card from the list at pos i
     */
    public void removeFlashCard(int i) {
        flashCardList.remove(i);
    }

    // EFFECTS: returns size of the list
    public int size() {
        return flashCardList.size();
    }
}
