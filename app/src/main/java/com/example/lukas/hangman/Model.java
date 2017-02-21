package com.example.lukas.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model implements MvpModel {
    private static final String[] WORDS = {"PENKTADIENIS", "KOMPIUTERIS", "SAVAITGALIS", "SODININKAS", "KLAVIATURA", "MIKROFONAS", "ZOOLOGIJA", "NOSINAITE", "VAIZDUOKLIS", "ZALIUZES", "SKAICIUOTUVAS"};
    private static final String WORD = WORDS[new Random().nextInt(10) + 1];
    private static char[] guessedLetters = new char[WORD.length()];
    private static int numberOfGuesses = 0; //number of wrong guesses
    private static char[] guesses = new char[32];
    private static int totalNumberOfGuesses = 0;

    @Override
    public String getWord() {
        return WORD;
    }

    @Override
    public char[] getGuessedLetters() {
        for (int i = 0; i < WORD.length(); i++) {
            if (guessedLetters[i] == 0) {
                guessedLetters[i] = '*';
            }
        }
        return guessedLetters;
    }


    public List<Integer> getAllIndexesOfLetter(char letter) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = -1; (i = getWord().indexOf(Character.toUpperCase(letter), i + 1)) != -1; )
            indexes.add(i);
        return indexes;
    }

    @Override
    public void addCorrectGuess(char letter) {
        List<Integer> indexes = getAllIndexesOfLetter(letter);
        if (indexes.size() > 0)
            for (int i = 0; i < indexes.size(); i++)
                guessedLetters[indexes.get(i)] = Character.toUpperCase(letter);
        else {
            increaseNumberOfGuesses();

        }
    }

    @Override
    public boolean wordCompleted() {
        if (WORD.equals(String.valueOf(getGuessedLetters())))
            return true;
        return false;
    }

    @Override
    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void increaseNumberOfGuesses() {
        numberOfGuesses++;
    }

    @Override
    public boolean gameOver() {
        if (getNumberOfGuesses() >= 11)
            return true;
        else return false;
    }

    @Override
    public char[] getGuesses() {
        return guesses;
    }

    @Override
    public void addGuess(char letter) {
        guesses[getTotalNumberOfGuesses()] = letter;
        incTotalNumberOfGuesses();
    }

    public int getTotalNumberOfGuesses() {
        return totalNumberOfGuesses;
    }

    public void incTotalNumberOfGuesses() {
        this.totalNumberOfGuesses++;
    }
}