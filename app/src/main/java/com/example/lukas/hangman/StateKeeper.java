package com.example.lukas.hangman;

import android.os.Bundle;

import timber.log.Timber;

class StateKeeper {
    private MvpModel model;

    StateKeeper(MvpModel model) {
        this.model = model;
    }

    void saveState(Bundle stateStorage) {
        stateStorage.putString("word", model.getWord());
        stateStorage.putString("guesses", String.valueOf(model.getGuesses()));
        Timber.i("State (word - " + model.getWord() + ", guesses - " +
                String.valueOf(model.getGuesses()) + ") have been saved.");
    }

    void restoreState(Bundle stateStorage) {
        if (stateStorage == null)
            Timber.e("Bundle you trying to restore is null!");
        else {
            model.restoreState(stateStorage.getString("word"), stateStorage.getString("guesses"));
            Timber.i("State (word - " + stateStorage.getString("word") + ", guesses - " +
                    stateStorage.getString("guesses") + ") have been saved.");
        }
    }
}