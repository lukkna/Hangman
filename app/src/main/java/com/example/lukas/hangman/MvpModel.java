package com.example.lukas.hangman;

interface MvpModel {
    String getWord();

    char[] getGuessedLetters();

    void doGuessLetter(char letter);

    int getNumberOfGuesses();

    boolean wordCompleted();

    boolean gameOver();

    char[] getGuesses();

    void startNewGame(GameStartCallback callback);

    void restoreState(String word, String guesses);
}