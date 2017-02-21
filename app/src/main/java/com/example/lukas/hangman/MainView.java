package com.example.lukas.hangman;

public interface MainView {
    void showMessage(String message);

    void changeImage(int id);

    void disableInput();

    void printGuessedLetters(String guessedLetters);
}
