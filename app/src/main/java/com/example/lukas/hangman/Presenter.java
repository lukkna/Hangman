package com.example.lukas.hangman;

public class Presenter implements MvpPresenter {

    private MvpModel mvpModel;
    private MainView mainView;

    public Presenter(MvpModel mvpModel, MainView mainView) {
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
        }
    }

    private void checkGameOver() {
        if (getMvpModel().gameOver()) {
            mainView.showMessage("Defeat! Correct word is " + getCorrectWord() + ".");
            mainView.disableInput();
        }
    }

    private char stringToChar(String letter) {
        if (letter.length() > 0)
            return letter.charAt(0);
        else return 0;
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
            }
        });
    }
}