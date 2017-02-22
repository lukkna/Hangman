package com.example.lukas.hangman;

public interface MvpModel {
    String getWord();

    char[] getGuessedLetters();

    void addCorrectGuess(char letter);

    int getNumberOfGuesses();

    boolean wordCompleted();

    boolean gameOver();

    char[] getGuesses();

    void addGuess(char letter);

    void getNewWord();
}