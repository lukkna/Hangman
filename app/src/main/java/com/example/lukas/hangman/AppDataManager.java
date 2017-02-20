package com.example.lukas.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppDataManager implements DataManager {
    private static final String[] WORDS = {"PENKTADIENIS", "KOMPIUTERIS", "SAVAITGALIS", "SODININKAS", "KLAVIATŪRA", "MIKROFONAS", "ZOOLOGIJA", "NOSINAITĖ", "VAIZDUOKLIS", "ŽALIUZĖS", "SKAIČIUOTUVAS"};
    private static final String WORD = WORDS[new Random().nextInt(10) + 1];
    private static char[] guessedLetters = new char[WORD.length()];
    private static int numberOfGuesses = 0;

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

    @Override
    public String getGuessedLettersAsString() {
        return String.valueOf(getGuessedLetters());
    }

    @Override
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
        else increaseNumberOfGuesses();
    }

    @Override
    public boolean wordCompleted() {
        if (WORD.equals(getGuessedLettersAsString()))
            return true;
        return false;
    }

    @Override
    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    @Override
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
    public char stringToChar(String letter) {
        if (letter.length() > 0)
            return letter.charAt(0);
        else return 0;  //padaryt praenšimą, kad neįvesta raidė
    }
}
