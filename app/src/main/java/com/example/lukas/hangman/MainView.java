package com.example.lukas.hangman;

interface MainView {
    void showMessage(String message);

    void changeImage(int id);

    void disableInput();

    void printGuessedLetters(String guessedLetters);

    void enableInput();

    void enableProgressBar(boolean enabled);

    void changePlayGameButtonText();
}
