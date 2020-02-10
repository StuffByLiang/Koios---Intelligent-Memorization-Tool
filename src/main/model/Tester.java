package model;

public class Tester {
    private FlashCardManager fcm;
    private int currentFlashCard;
    private int numRightAnswers;
    private int totalCards;

    // EFFECTS: constructs a new tester with a specified set of flash cards
    public Tester(FlashCardManager fcm) {
        this.fcm = fcm;
        currentFlashCard = 0;
        numRightAnswers = 0;
        totalCards = fcm.size();
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
        return currentFlashCard == fcm.size() - 1;
    }

    // EFFECTS: returns the backSide of the current flash card
    public String getBackSide() {
        return fcm.getFlashCard(currentFlashCard).getBackSide();
    }

    // EFFECTS: returns the frontSide of the current flash card
    public String getFrontSide() {
        return fcm.getFlashCard(currentFlashCard).getFrontSide();
    }

    public int getNumRightAnswers() {
        return numRightAnswers;
    }

    public int getTotalCards() {
        return totalCards;
    }

    public int getCurrentFlashCard() {
        return currentFlashCard;
    }
}
