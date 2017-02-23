package com.example.lukas.hangman;

public interface MvpPresenter {
    void onGuessLetter(String letter);

    void onStartNewGame();
}