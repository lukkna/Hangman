package com.example.lukas.hangman;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

class Model implements MvpModel {
    //private static final String[] WORDS = {"PENKTADIENIS", "KOMPIUTERIS", "SAVAITGALIS", "SODININKAS", "KLAVIATURA", "MIKROFONAS", "ZOOLOGIJA", "NOSINAITE", "VAIZDUOKLIS", "ZALIUZES", "SKAICIUOTUVAS"};
    //private static final String word = WORDS[new Random().nextInt(10) + 1];
    private MvpWordProvider wordProvider = new WordProvider();
    private static String word = "";
    private char[] guessedLetters;
    private int numberOfWrongGuesses = 0; //number of wrong guesses
    private char[] guesses = new char[32];
    private int numberOfGuesses = 0;

    private MvpWordProvider getWordProvider() {
        return wordProvider;
    }

    @Override
    public void startNewGame(GameStartCallback callback) {
        getWordProvider().getWordFromApi(word -> {
            if (word.equals("500")) {
                callback.gameFailedToStart();
                Timber.e("Failed to retrieve word.");
            } else {
                setWord(word.toUpperCase());
                resetVariables();
                callback.gameStarted();
                Timber.i("Word (" + word + ") received, starting new game.");
            }
        });
    }

    @Override
    public void restoreState(String word, String guesses) {
        resetVariables();
        setWord(word);
        for (int i = 0; i < guesses.length(); i++)
            if (guesses.charAt(i) != 0)
                doGuessLetter(guesses.charAt(i));
    }

    private void setWord(String str) {
        word = str;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public char[] getGuessedLetters() {
        for (int i = 0; i < getWord().length(); i++)
            if (guessedLetters != null && guessedLetters[i] == 0)
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
        Timber.i("Letter (" + letter + ") received, checking if guess valid.");
        checkGuess(Character.toUpperCase(letter));
        guesses[getNumberOfGuesses()] = Character.toUpperCase(letter);
        increaseNumberOfGuesses();
    }

    private void checkGuess(char letter) {
        List<Integer> indexes = getAllIndexesOfLetter(letter);
        if (indexes.size() > 0)
            for (int i = 0; i < indexes.size(); i++)
                guessedLetters[indexes.get(i)] = letter;
        else checkIfGuessRecurrent(letter);
    }

    private void checkIfGuessRecurrent(char letter) {
        if (!String.valueOf(getGuesses()).contains("" + letter))
            increaseNumberOfWrongGuesses();
    }

    @Override
    public boolean victory() {
        return getGuessedLetters() != null && getWord().equals(String.valueOf(getGuessedLetters()));
    }

    @Override
    public boolean gameOver() {
        return (getNumberOfWrongGuesses() >= 11);
    }

    public int getNumberOfWrongGuesses() {
        return numberOfWrongGuesses;
    }

    private void increaseNumberOfWrongGuesses() {
        numberOfWrongGuesses++;
    }

    @Override
    public char[] getGuesses() {
        return guesses;
    }

    private int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    private void increaseNumberOfGuesses() {
        this.numberOfGuesses++;
    }

    private void resetVariables() {
        numberOfWrongGuesses = 0;
        guesses = new char[32];
        numberOfGuesses = 0;
        guessedLetters = new char[getWord().length()];
        Timber.i("Variables have been reset.");
    }
}