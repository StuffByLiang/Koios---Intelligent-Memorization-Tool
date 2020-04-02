package model;

import exception.InvalidFlashCardException;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.io.Serializable;

import static jdk.nashorn.internal.objects.NativeString.trim;

// Represents a flashcard, which has a frontside and a backside
public class FlashCard implements Serializable {
    private static final long serialVersionUID = 4L;
    private String frontSide;
    private String backSide;

    // EFFECTS: creates a flash card with a front side and back side and removes trailing/leading whitespace.
    //          if any of the sides after the trim are empty or null, throw an InvalidFlashCardException
    public FlashCard(String frontSide, String backSide) throws InvalidFlashCardException {
        if (isInvalidString(frontSide) || isInvalidString(backSide)) {
            throw new InvalidFlashCardException();
        }
        this.frontSide = trim(frontSide);
        this.backSide = trim(backSide);
    }

    public String getFrontSide() {
        return frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    // EFFECTS: sets the frontside of the flashcard to the given string and removes trailing/leading whitespace.
    //          if any of the sides after the trim are empty or null, throw an InvalidFlashCardException
    public void setFrontSide(String frontSide) throws InvalidFlashCardException {
        if (isInvalidString(frontSide)) {
            throw new InvalidFlashCardException();
        }
        this.frontSide = trim(frontSide);
    }

    // EFFECTS: sets the backside of the flashcard to the given string and removes trailing/leading whitespace.
    //          if any of the sides after the trim are empty or null, throw an InvalidFlashCardException
    public void setBackSide(String backSide) throws InvalidFlashCardException {
        if (isInvalidString(backSide)) {
            throw new InvalidFlashCardException();
        }
        this.backSide = trim(backSide);
    }

    // EFFECTS: returns true if the given string is null or the string without trailing/leading whitespace is empty
    private boolean isInvalidString(String s) {
        return s == null || trim(s).isEmpty();
    }
}
