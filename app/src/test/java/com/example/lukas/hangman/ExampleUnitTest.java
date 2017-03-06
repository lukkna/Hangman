package com.example.lukas.hangman;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExampleUnitTest {
    private Model model = new Model();

    @Test
    public void getGuessedLettersTest() {
        assertEquals(null, model.getGuessedLetters());
    }

    @Test
    public void getGuessesTest() {
        assertArrayEquals(new char[32], model.getGuesses());
    }

    @Test
    public void gameOverTest() {
        assertEquals(false, model.gameOver());
    }

    @Test
    public void getNumberOfGuessesTest() {
        assertEquals(0, model.getNumberOfWrongGuesses());
    }

    @Test
    public void wordCompletedTest() {
        assertEquals(false, model.victory());
    }

    @Test
    public void getWordTest() {
        assertEquals("WORD", model.getWord());
    }

    @Test
    public void doGuessLetterTest() {
        model.doGuessLetter('A');
        assertEquals('A', model.getGuesses()[0]);
        assertEquals(1, model.getNumberOfWrongGuesses());
    }

    @Test
    public void restoreStateTest() {
        String word = "WORD", guesses = "ABCD";
        model.restoreState(word, guesses);
        assertEquals(3, model.getNumberOfWrongGuesses());
        for (int i = 0; i < guesses.length(); i++)
            assertEquals(guesses.charAt(i), model.getGuesses()[i]);
        assertArrayEquals(new char[]{'*', '*', '*', 'D'}, model.getGuessedLetters());
        assertEquals(word, model.getWord());
        assertEquals(guesses.charAt(3), model.getGuesses()[3]);
    }

//    @Test
//    public void startNewGameTest() {
//        GameStartCallback callback = () -> {};
//        model.startNewGame(callback);
//
//
//
//    }

}
