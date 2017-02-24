package com.example.lukas.hangman;

import android.os.Bundle;

public class StateKeeper {

    private MvpModel model;

    public StateKeeper(MvpModel model) {
        this.model = model;
    }

    public void saveState(Bundle stateStorage) {
        stateStorage.putString("word", model.getWord());
        stateStorage.putString("guesses", String.valueOf(model.getGuesses()));
    }

    public void restoreState(Bundle stateStorage) {
        model.restoreState(stateStorage.getString("word"),
                stateStorage.getString("guesses"));
    }
}