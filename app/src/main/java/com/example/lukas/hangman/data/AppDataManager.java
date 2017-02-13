package com.example.lukas.hangman.data;

import com.example.lukas.hangman.data.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataManager implements DataManager {
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    private static final String WORD = "PENKTADIENIS";

    private static char[] guessedLetters = new char[WORD.length()];

    public String getWord() {
        return WORD;
    }

    public char[] getGuessedLetters() {
        return guessedLetters;
    }

    public List<Integer> getAllIndexesOfLetter(char letter) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = -1; (i = getWord().indexOf(letter, i + 1)) != -1; )
            indexes.add(i);
        return indexes;
    }

    public void addCorrectGuess(char letter) {
        List<Integer> indexes = getAllIndexesOfLetter(letter);
        for (int i = 0; i < indexes.size(); i++) {
            System.out.println(letter);
            guessedLetters[indexes.get(i)] = letter;
        }
    }
}
