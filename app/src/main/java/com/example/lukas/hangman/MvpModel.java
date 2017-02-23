package com.example.lukas.hangman;

public interface MvpModel {
    String getWord();

    char[] getGuessedLetters();

    void doGuessLetter(char letter);

    int getNumberOfGuesses();

    boolean wordCompleted();

    boolean gameOver();

    char[] getGuesses();

    void startNewGame(GameStartCallback callback);
}