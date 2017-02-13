package com.example.lukas.hangman.data;

import com.example.lukas.hangman.data.db.DbHelper;

import java.util.List;

public interface DataManager extends DbHelper {
    String getWord();
    char[] getGuessedLetters();
    List<Integer> getAllIndexesOfLetter(char letter);
    void addCorrectGuess(char letter);
}
