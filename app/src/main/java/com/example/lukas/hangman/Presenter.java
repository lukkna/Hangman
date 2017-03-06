package com.example.lukas.hangman;

import java.util.logging.Level;
import java.util.logging.Logger;

class Presenter implements MvpPresenter {
    private static final Logger LOGGER = Logger.getLogger(Model.class.getName());

    private MvpModel mvpModel;
    private MainView mainView;

    Presenter(MvpModel mvpModel, MainView mainView) {
        this.mvpModel = mvpModel;
        this.mainView = mainView;
    }

    private MvpModel getMvpModel() {
        return mvpModel;
    }

    private void guessLetter(char letter) {
        getMvpModel().doGuessLetter(letter);
    }

    @Override
    public String getCorrectWord() {
        return getMvpModel().getWord();
    }

    private String getGuessedLettersAsString() {
        return String.valueOf(getMvpModel().getGuessedLetters());
    }

    @Override
    public void restoreState() {
        showGuesses();//reset guesses
        mainView.printGuessedLetters(getGuessedLettersAsString());
        mainView.changeImage(getMvpModel().getNumberOfGuesses());
        mainView.enableInput();
    }

    public String getGuesses() {
        return String.valueOf(getMvpModel().getGuesses());
    }

    private void checkIfGameEnded() {
        checkVictory();
        checkGameOver();
    }

    private void checkVictory() {
        if (getMvpModel().wordCompleted()) {
            mainView.showMessage("Victory!");
            mainView.disableInput();
            LOGGER.log(Level.INFO, "Victory. Input has been disabled.");
        }
    }

    private void checkGameOver() {
        if (getMvpModel().gameOver()) {
            mainView.showMessage("Defeat! Correct word is " + getCorrectWord() + ".");
            mainView.disableInput();
            LOGGER.log(Level.INFO, "Defeat. Input has been disabled.");
        }
    }

    private char stringToChar(String letter) {
        return (letter.length() > 0) ? letter.charAt(0) : 0;
    }

    private void showGuesses() {
        mainView.showMessage(getGuesses());
        mainView.changeImage(getMvpModel().getNumberOfGuesses());
        mainView.printGuessedLetters(getGuessedLettersAsString());
    }

    @Override
    public void onGuessLetter(String letter) {
        if (letter.length() != 0)
            guessSingleLetter(stringToChar(letter));
        else
            mainView.showMessage("Type in letter first!");
    }

    private void guessSingleLetter(char singleLetter) {
        guessLetter(singleLetter);
        showGuesses();
        checkIfGameEnded();
    }

    @Override
    public void onStartNewGame() {
        mainView.enableProgressBar(true);
        mainView.disableInput();
        mainView.changeImage(0);
        getMvpModel().startNewGame(new GameStartCallback() {
            @Override
            public void gameStarted() {
                showGuesses();//reset guesses
                mainView.printGuessedLetters(getGuessedLettersAsString());
                mainView.enableInput();
                mainView.enableProgressBar(false);
                LOGGER.log(Level.INFO, "New game started.");
            }

            @Override
            public void gameFailedToStart() {
                mainView.enableProgressBar(false);
                mainView.changePlayGameButtonText();
                mainView.showMessage("Failed to retrieve word. Press try again.");
                LOGGER.log(Level.WARNING, "Failed to retrieve word.");
            }
        });
    }
}