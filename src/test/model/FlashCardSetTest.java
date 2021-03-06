package model;

import exception.InvalidFlashCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashCardSetTest {
    FlashCardSet fcl;

    private FlashCard createFlashCard(String frontSide, String backSide) {
        try {
            return new FlashCard(frontSide, backSide);
        } catch (InvalidFlashCardException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkSetup() {
        assertEquals(3, fcl.size());
    }

    private void checkFlashCard(FlashCard fc, String frontSide, String backSide) {
        assertEquals(frontSide, fc.getFrontSide());
        assertEquals(backSide, fc.getBackSide());
    }

    @BeforeEach
    public void setup() {
        fcl = new FlashCardSet();
        fcl.addFlashCard(createFlashCard("1+1", "2"));
        fcl.addFlashCard(createFlashCard("2+2", "3"));
        fcl.addFlashCard(createFlashCard("4+4", "8"));
    }

    @Test
    public void testConstructor() {
        fcl = new FlashCardSet();
        assertEquals(0, fcl.size());
    }

    @Test
    public void TestGetFlashCardList() {
        assertEquals(3, fcl.getFlashCardList().size());
    }

    @Test
    public void testAddFlashCard() {
        checkSetup();
        FlashCard fc = createFlashCard("8+8", "16");
        fcl.addFlashCard(fc);
        assertEquals(4, fcl.size());
        assertEquals(fc, fcl.getFlashCard(3));
    }

    @Test
    public void testUpdateFlashCard() {
        checkSetup();
        try {
            fcl.updateFlashCard(1, "2+2", "4");
        } catch (InvalidFlashCardException e) {
            e.printStackTrace();
        }
        assertEquals(3, fcl.size());
        checkFlashCard(fcl.getFlashCard(1), "2+2", "4");
    }

    @Test
    public void testDeleteFlashCardById() {
        checkSetup();
        fcl.removeFlashCard(0);
        assertEquals(2, fcl.size());
    }

    @Test
    public void testDeleteFlashCard() {
        checkSetup();
        FlashCard fc = createFlashCard("1", "2");
        fcl.addFlashCard(fc);
        assertEquals(4, fcl.size());
        fcl.removeFlashCard(fc);
        assertEquals(3, fcl.size());

    }

    @Test
    public void testIsValidId() {
        checkSetup();
        assertTrue(fcl.isValidId(0));
        assertTrue(fcl.isValidId(1));
        assertTrue(fcl.isValidId(2));
        assertFalse(fcl.isValidId(-1));
        assertFalse(fcl.isValidId(3));
    }

    @Test
    public void testIsValidIdString() {
        checkSetup();
        assertTrue(fcl.isValidId("1"));
        assertTrue(fcl.isValidId("2"));
        assertTrue(fcl.isValidId("3"));
        assertFalse(fcl.isValidId("0"));
        assertFalse(fcl.isValidId("4"));
        assertFalse(fcl.isValidId("yo moma"));
    }
}
