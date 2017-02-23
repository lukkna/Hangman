package com.example.lukas.hangman;

import java.util.ArrayList;
import java.util.List;


public class Model implements MvpModel {
    //private static final String[] WORDS = {"PENKTADIENIS", "KOMPIUTERIS", "SAVAITGALIS", "SODININKAS", "KLAVIATURA", "MIKROFONAS", "ZOOLOGIJA", "NOSINAITE", "VAIZDUOKLIS", "ZALIUZES", "SKAICIUOTUVAS"};
    //private static final String word = WORDS[new Random().nextInt(10) + 1];

    private MvpWordProvider wordProvider = new WordProvider();
    private static String word = "";
    private char[] guessedLetters;
    private int numberOfGuesses = 0; //number of wrong guesses
    private char[] guesses = new char[32];
    private int totalNumberOfGuesses = 0;


    public MvpWordProvider getWordProvider() {
        return wordProvider;
    }

    @Override
    public void startNewGame(GameStartCallback callback) {
        getWordProvider().getWordFromApi(new MvpWordProvider.WordReceived() {
            @Override
            public void onWordReceived(String word2) {
                word = word2.toUpperCase();
                resetVariables();
                callback.gameStarted();
            }
        });
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public char[] getGuessedLetters() {
        for (int i = 0; i < word.length(); i++)
            if (guessedLetters[i] == 0)
                guessedLetters[i] = '*';
        return guessedLetters;
    }


    public List<Integer> getAllIndexesOfLetter(char letter) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = -1; (i = getWord().indexOf(Character.toUpperCase(letter), i + 1)) != -1; )
            indexes.add(i);
        return indexes;
    }

    @Override
    public void doGuessLetter(char letter) {
        char uppedLetter = Character.toUpperCase(letter);
        List<Integer> indexes = getAllIndexesOfLetter(uppedLetter);
        if (indexes.size() > 0)
            for (int i = 0; i < indexes.size(); i++) {
                guessedLetters[indexes.get(i)] = uppedLetter;
            }
        else increaseNumberOfGuesses();

        guesses[getTotalNumberOfGuesses()] = uppedLetter;
        incTotalNumberOfGuesses();
    }

    @Override
    public boolean wordCompleted() {
        return word.equals(String.valueOf(getGuessedLetters()));
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

    public int getTotalNumberOfGuesses() {
        return totalNumberOfGuesses;
    }

    public void incTotalNumberOfGuesses() {
        this.totalNumberOfGuesses++;
    }

    private void resetVariables() {
        numberOfGuesses = 0;
        guesses = new char[32];
        totalNumberOfGuesses = 0;
        guessedLetters = new char[getWord().length()];
    }
}