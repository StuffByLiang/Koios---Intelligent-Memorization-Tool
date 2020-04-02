package model;

import exception.InvalidFlashCardException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FlashCardTest {
    FlashCard fc1;

    @BeforeEach
    public void setup() {
        try {
            fc1 = new FlashCard("1+1", "2");
        } catch (InvalidFlashCardException e) {
            fail("shouldn't fail");
        }
    }

    @Test
    public void testConstructorNoException() {
        try {
            fc1 = new FlashCard("What is the powerhouse of the cell?", "mitochondria");
        } catch (InvalidFlashCardException e) {
            fail("Shouldn't have thrown an exception");
        }
        assertEquals("What is the powerhouse of the cell?", fc1.getFrontSide());
        assertEquals("mitochondria", fc1.getBackSide());
    }

    @Test
    public void testConstructorWithException() {
        try {
            fc1 = new FlashCard("", "");
            fail("Should have thrown an exception");
        } catch (InvalidFlashCardException e) {
            // should throw the execption!
        }
    }

    @Test
    public void testConstructorWithTrimAndException() {
        try {
            fc1 = new FlashCard(" ", "     ");
            fail("Should have thrown an exception");
        } catch (InvalidFlashCardException e) {
            // should throw the execption!
        }
    }

    @Test
    public void testTrim() {
        try {
            fc1 = new FlashCard(" 1+1 ", "2       ");
        } catch (InvalidFlashCardException e) {
            fail("Shouldn't have thrown an exception");
        }
        assertEquals("1+1", fc1.getFrontSide());
        assertEquals("2", fc1.getBackSide());
    }

    @Test
    public void testSetFrontSide() {
        try {
            fc1.setFrontSide(" 2+2   ");
        } catch (InvalidFlashCardException e) {
            fail("shouldn't fail");
        }
        assertEquals("2+2", fc1.getFrontSide());
    }

    @Test
    public void testSetFrontSideException() {
        try {
            fc1.setFrontSide("");
            fail("should fail");
        } catch (InvalidFlashCardException e) {
            // this should fail
        }
        assertEquals("1+1", fc1.getFrontSide());
    }

    @Test
    public void testSetBackSide() {
        try {
            fc1.setBackSide("4");
        } catch (InvalidFlashCardException e) {
            fail("shouldn't fail");
        }
        assertEquals("4", fc1.getBackSide());
    }

    @Test
    public void testSetBackSideException() {
        try {
            fc1.setBackSide("   ");
            fail("should fail");
        } catch (InvalidFlashCardException e) {
            // this should fail
        }
        assertEquals("2", fc1.getBackSide());
    }
}