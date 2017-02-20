package com.example.lukas.hangman;

import java.util.List;

public interface DataManager {
    String getWord();

    char[] getGuessedLetters();

    String getGuessedLettersAsString();

    List<Integer> getAllIndexesOfLetter(char letter);

    void addCorrectGuess(char letter);

    boolean wordCompleted();

    int getNumberOfGuesses();

    void increaseNumberOfGuesses();

    boolean gameOver();

    char stringToChar(String letter);
}
