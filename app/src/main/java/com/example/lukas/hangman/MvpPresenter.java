package com.example.lukas.hangman;

public interface MvpPresenter {
    void onGuessLetter(String letter);

    void onStartNewGame();

    String getCorrectWord();

    String getGuesses();

    void restoreState();
}