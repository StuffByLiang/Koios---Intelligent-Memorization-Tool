package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FlashCardTest {
    FlashCard fc1;

    @Test
    public void testConstructor() {
        fc1 = new FlashCard("What is the powerhouse of the cell?", "mitochondria");
        assertEquals("What is the powerhouse of the cell?", fc1.getFrontSide());
        assertEquals("mitochondria", fc1.getBackSide());
    }
}