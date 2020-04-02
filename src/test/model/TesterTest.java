package model;

import exception.InvalidFlashCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesterTest {
    Tester tester;

    private FlashCard createFlashCard(String frontSide, String backSide) {
        try {
            return new FlashCard(frontSide, backSide);
        } catch (InvalidFlashCardException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setup() {
        FlashCardSet fcm = new FlashCardSet();
        fcm.addFlashCard(createFlashCard("1+1", "2"));
        fcm.addFlashCard(createFlashCard("2+2", "3"));
        fcm.addFlashCard(createFlashCard("4+4", "8"));

        tester = new Tester(fcm);
    }

    @Test
    public void testConstructor() {
        assertEquals(3, tester.getTotalCards());
        assertEquals(0, tester.getNumRightAnswers());
        assertEquals(0, tester.getCurrentFlashCard());
    }

    @Test
    public void testCheckFalse() {
        assertFalse(tester.isRightAnswer("1"));
        assertEquals(0, tester.getNumRightAnswers());
        assertEquals(0, tester.getCurrentFlashCard());
    }

    @Test
    public void testCheckTrue() {
        assertTrue(tester.isRightAnswer("2"));
        assertEquals(1, tester.getNumRightAnswers());
        assertEquals(1, tester.getCurrentFlashCard());
    }

    @Test
    public void testSkip() {
        tester.skip();
        assertEquals(0, tester.getNumRightAnswers());
        assertEquals(1, tester.getCurrentFlashCard());
    }

    @Test
    public void testSkipWhenFinished() {
        tester.skip();
        tester.skip();
        assertTrue(tester.isLastCard());
        assertEquals(0, tester.getNumRightAnswers());
        assertEquals(2, tester.getCurrentFlashCard());
        tester.skip();
        assertEquals(0, tester.getNumRightAnswers());
        assertEquals(3, tester.getCurrentFlashCard());
        tester.skip();
        assertEquals(0, tester.getNumRightAnswers());
        assertEquals(3, tester.getCurrentFlashCard());
    }

    @Test
    public void testIsLastCardFalse() {
        assertFalse(tester.isLastCard());
    }

    @Test
    public void testIsLastCardTrue() {
        tester.skip();
        tester.skip();
        assertTrue(tester.isLastCard());
    }

    @Test
    public void testGetFrontSide() {
        assertEquals("1+1", tester.getFrontSide());
    }

    @Test
    public void testGetBackSide() {
        assertEquals("2", tester.getBackSide());
    }

    @Test
    public void testGetNumSkipped() {
        assertEquals(0, tester.getNumSkipped());
        tester.skip();
        assertEquals(1, tester.getNumSkipped());
        assertTrue(tester.isRightAnswer("3"));
        tester.skip();
        assertEquals(2, tester.getNumSkipped());
    }

    @Test
    public void testGetNumRemaining() {
        assertEquals(3, tester.getNumRemaining());
    }

    @Test
    public void testIsFinishedFalse() {
        assertFalse(tester.isFinished());
    }

    @Test
    public void testIsFinishedTrue() {
        tester.skip();
        tester.skip();
        tester.skip();
        assertTrue(tester.isFinished());
    }
}
