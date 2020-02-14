package model;

import ui.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// represents a flash card manager that contains a list of flash cards
public class FlashCardSet implements Serializable {
    private static final long serialVersionUID = 3L;
    private List<FlashCard> flashCardList;

    // EFFECTS: creates an empty list of flash cards
    public FlashCardSet() {
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

    // EFFECTS: returns true if given flashcard id is in the list (zeros based)
    public boolean isValidId(int id) {
        return id >= 0 && id < size();
    }

    // EFFECTS: returns true if given flashcard id is in the list (ones based)
    public boolean isValidId(String id) {
        if (!Util.isNum(id)) {
            return false;
        } else {
            int num = Integer.parseInt(id);
            return num >= 1 && num <= size();
        }
    }
}
