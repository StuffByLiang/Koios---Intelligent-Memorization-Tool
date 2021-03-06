package model;

// represents a class that can contains information needed to remember a testing session
public class Tester {
    private FlashCardSet fcs;
    private int currentFlashCard;
    private int numRightAnswers;
    private int totalCards;

    // EFFECTS: constructs a new tester with a specified set of flash cards
    public Tester(FlashCardSet fcs) {
        this.fcs = fcs;
        currentFlashCard = 0;
        numRightAnswers = 0;
        totalCards = fcs.size();
    }

    // REQUIRES: currentFlashCard < totalCards
    // MODIFIES: this
    // EFFECTS: returns true if answer matches the backside of current card,
    //          goes to the next card, and increments the num of right answer by one
    public boolean isRightAnswer(String answer) {
        answer = answer.toLowerCase();
        if (answer.equals(getBackSide().toLowerCase())) {
            currentFlashCard++;
            numRightAnswers++;
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if current FlashCard is more than or equal to totalCards
    public boolean isFinished() {
        return currentFlashCard >= totalCards;
    }

    // MODIFIES: this
    // EFFECTS: skips to the next flash card if testing is not finished
    public void skip() {
        if (!isFinished()) {
            currentFlashCard++;
        }
    }

    // EFFECTS: returns true if the current flash card is the last card.
    public boolean isLastCard() {
        return currentFlashCard == fcs.size() - 1;
    }

    // EFFECTS: returns the backSide of the current flash card
    public String getBackSide() {
        return fcs.getFlashCard(currentFlashCard).getBackSide();
    }

    // EFFECTS: returns the frontSide of the current flash card
    public String getFrontSide() {
        return fcs.getFlashCard(currentFlashCard).getFrontSide();
    }

    public int getNumRightAnswers() {
        return numRightAnswers;
    }

    public int getTotalCards() {
        return totalCards;
    }

    public int getNumSkipped() {
        return currentFlashCard - numRightAnswers;
    }

    public int getNumRemaining() {
        return totalCards - currentFlashCard;
    }

    public int getCurrentFlashCard() {
        return currentFlashCard;
    }
}
