package com.example.lukas.hangman;

import android.os.Bundle;

import java.util.logging.Level;
import java.util.logging.Logger;

class StateKeeper {
    private final static Logger LOGGER = Logger.getLogger(Model.class.getName());
    private MvpModel model;

    StateKeeper(MvpModel model) {
        this.model = model;
    }

    void saveState(Bundle stateStorage) {
        stateStorage.putString("word", model.getWord());
        stateStorage.putString("guesses", String.valueOf(model.getGuesses()));
        LOGGER.log(Level.INFO, "State saved.");
    }

    void restoreState(Bundle stateStorage) {
        model.restoreState(stateStorage.getString("word"),
                stateStorage.getString("guesses"));
        LOGGER.log(Level.INFO, "State restored.");
    }
}