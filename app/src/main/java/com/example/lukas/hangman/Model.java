package com.example.lukas.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class Model implements MvpModel {
    //private static final String[] WORDS = {"PENKTADIENIS", "KOMPIUTERIS", "SAVAITGALIS", "SODININKAS", "KLAVIATURA", "MIKROFONAS", "ZOOLOGIJA", "NOSINAITE", "VAIZDUOKLIS", "ZALIUZES", "SKAICIUOTUVAS"};
    //private static final String word = WORDS[new Random().nextInt(10) + 1];
    private static final Logger LOGGER = Logger.getLogger(Model.class.getName());

    private MvpWordProvider wordProvider = new WordProvider();
    private static String word = "";
    private char[] guessedLetters;
    private int numberOfGuesses = 0; //number of wrong guesses
    private char[] guesses = new char[32];
    private int totalNumberOfGuesses = 0;

    private MvpWordProvider getWordProvider() {
        return wordProvider;
    }

    @Override
    public void startNewGame(GameStartCallback callback) {
        getWordProvider().getWordFromApi(word2 -> {
            if (word2.equals("500")) {
                callback.gameFailedToStart();
                LOGGER.log(Level.WARNING, "Failed to retrieve word.");
            } else {
                word = word2.toUpperCase();
                LOGGER.log(Level.INFO, "Word (" + word + ") received, starting new game.");
                resetVariables();
                callback.gameStarted();
            }
        });
    }

    @Override
    public void restoreState(String word, String guesses) {
        resetVariables();
        setWord(word);
        guessedLetters = new char[word.length()];
        for (int i = 0; i < guesses.length() - 1; i++)
            if (guesses.charAt(i) != 0)
                doGuessLetter(guesses.charAt(i));
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

    private List<Integer> getAllIndexesOfLetter(char letter) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = -1; (i = getWord().indexOf(Character.toUpperCase(letter), i + 1)) != -1; )
            indexes.add(i);
        return indexes;
    }

    @Override
    public void doGuessLetter(char letter) {
        LOGGER.log(Level.INFO, "Letter received, checking if guess valid.");
        char uppedLetter = Character.toUpperCase(letter);
        List<Integer> indexes = getAllIndexesOfLetter(uppedLetter);
        if (indexes.size() > 0)
            for (int i = 0; i < indexes.size(); i++)
                guessedLetters[indexes.get(i)] = uppedLetter;
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

    private void increaseNumberOfGuesses() {
        numberOfGuesses++;
    }

    @Override
    public boolean gameOver() {
        return (getNumberOfGuesses() >= 11);
    }

    @Override
    public char[] getGuesses() {
        return guesses;
    }

    private int getTotalNumberOfGuesses() {
        return totalNumberOfGuesses;
    }

    private void incTotalNumberOfGuesses() {
        this.totalNumberOfGuesses++;
    }

    private void resetVariables() {
        numberOfGuesses = 0;
        guesses = new char[32];
        totalNumberOfGuesses = 0;
        guessedLetters = new char[getWord().length()];
        LOGGER.log(Level.INFO, "Variables has been reset.");
    }

    private void setWord(String str) {
        word = str;
    }
}