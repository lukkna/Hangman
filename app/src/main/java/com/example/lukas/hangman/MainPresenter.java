package com.example.lukas.hangman;

public class MainPresenter implements MainMvpPresenter {

    private final DataManager dataManager;
    private MainMvpView mainMvpView;

    public MainPresenter(DataManager dataManager, MainMvpView mainMvpView) {
        this.dataManager = dataManager;
        this.mainMvpView = mainMvpView;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void guessLetter(char letter) {
        getDataManager().addCorrectGuess(letter);
    }

    @Override
    public char[] getGuessedLetters() {
        return getDataManager().getGuessedLetters();
    }

    @Override
    public String getCorrectWord() {
        return getDataManager().getWord();
    }

    @Override
    public String getGuessedLettersAsString() {
        return getDataManager().getGuessedLettersAsString();
    }

    @Override
    public void wordCompleted() {
        if (getDataManager().wordCompleted())
            mainMvpView.showMessage("Victory!");
    }

    @Override
    public void gameOver() {
        if (getDataManager().gameOver())
            mainMvpView.showMessage("Defeat!");
    }

    @Override
    public String changeImage() {
        return "hang" + getDataManager().getNumberOfGuesses();
    }

    @Override
    public char stringToChar(String letter) {
        return getDataManager().stringToChar(letter);
    }
}