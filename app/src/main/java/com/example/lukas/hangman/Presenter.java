package com.example.lukas.hangman;

public class Presenter implements MvpPresenter {

    private MvpModel mvpModel;
    private MainView mainView;

    public Presenter(MvpModel mvpModel, MainView mainView) {
        this.mvpModel = mvpModel;
        this.mainView = mainView;
    }

    public MvpModel getMvpModel() {
        return mvpModel;
    }

    public void guessLetter(char letter) {
        getMvpModel().addCorrectGuess(letter);
    }

    public String getCorrectWord() {
        return getMvpModel().getWord();
    }

    public String getGuessedLettersAsString() {
        return String.valueOf(getMvpModel().getGuessedLetters());
    }

    public String getGuesses() {
        return String.valueOf(getMvpModel().getGuesses());
    }

    public void wordCompleted() {
        if (getMvpModel().wordCompleted()) {
            mainView.showMessage("Victory!" + getCorrectWord() + ".");
            mainView.disableInput();
        }
    }

    public void gameOver() {
        if (getMvpModel().gameOver()) {
            mainView.showMessage("Defeat! Correct word is " + getCorrectWord() + ".");
            mainView.disableInput();
        }
    }

    public char stringToChar(String letter) {
        if (letter.length() > 0)
            return letter.charAt(0);
        else return 0;
    }

    public void showGuesses() {
        mainView.showMessage(getGuesses());
    }

    public void addGuess(char letter) {
        getMvpModel().addGuess(letter);
    }

    @Override
    public void onButtonClick(String letter) {
        if (letter.length() != 0) {
            guessLetter(stringToChar(letter));
            addGuess(stringToChar(letter));
            showGuesses();
            wordCompleted();
            gameOver();
            mainView.changeImage(getMvpModel().getNumberOfGuesses());
            mainView.printGuessedLetters(getGuessedLettersAsString());

            System.out.println(getMvpModel().getWord());
        }
    }

    @Override
    public void playGame() {
        mainView.changeImage(0);
        showGuesses();
        getMvpModel().getNewWord();
        System.out.println("playgame presenter" + getMvpModel().getWord());
        mainView.enableInput();
    }
}