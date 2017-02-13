package com.example.lukas.hangman.ui;

import com.example.lukas.hangman.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter implements MainMvpPresenter {

    private final DataManager dataManager;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable, DataManager dataManager1, CompositeDisposable compositeDisposable1) {
        this.dataManager = dataManager1;
        this.compositeDisposable = compositeDisposable1;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }


    @Override
    public void guessLetter(char letter) {
        dataManager.addCorrectGuess(letter);
    }
}