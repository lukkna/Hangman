package com.example.lukas.hangman;

import timber.log.Timber;

class Presenter implements MvpPresenter {
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
        mainView.changeImage(getMvpModel().getNumberOfWrongGuesses());
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
        if (getMvpModel().victory()) {
            mainView.showMessage("Victory!");
            mainView.disableInput();
            Timber.i("Victory. Input has been disabled.");
        }
    }

    private void checkGameOver() {
        if (getMvpModel().gameOver()) {
            mainView.showMessage("Defeat! Correct word is " + getCorrectWord() + ".");
            mainView.disableInput();
            Timber.i("Defeat. Input has been disabled.");
        }
    }

    private char stringToChar(String letter) {
        return (letter.length() > 0) ? letter.charAt(0) : 0;
    }

    private void showGuesses() {
        mainView.showMessage(getGuesses());
        mainView.changeImage(getMvpModel().getNumberOfWrongGuesses());
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

    private void onGameStarted() {
        showGuesses();//reset guesses
        mainView.printGuessedLetters(getGuessedLettersAsString());
        mainView.enableInput();
        mainView.enableProgressBar(false);
        Timber.i("New game started.");
    }

    private void onGameFailedToStart() {
        mainView.enableProgressBar(false);
        mainView.changeImage(500);
        mainView.changePlayGameButtonText("Try again.");
        mainView.showMessage("Failed to retrieve word. Press try again.");
        Timber.e("Failed to retrieve word.");
    }

    @Override
    public void onStartNewGame() {
        mainView.enableProgressBar(true);
        mainView.disableInput();
        mainView.changeImage(0);
        getMvpModel().startNewGame(new GameStartCallback() {
            @Override
            public void gameStarted() {
                onGameStarted();
            }

            @Override
            public void gameFailedToStart() {
                onGameFailedToStart();
            }
        });
    }
}