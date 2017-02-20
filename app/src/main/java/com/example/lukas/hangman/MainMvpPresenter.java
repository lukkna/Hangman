package com.example.lukas.hangman;

public interface MainMvpPresenter {

    void guessLetter(char letter);

    char[] getGuessedLetters();

    String getCorrectWord();

    String getGuessedLettersAsString();

    void wordCompleted();

    void gameOver();

    String changeImage();

    char stringToChar(String letter);
}
