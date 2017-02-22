package com.example.lukas.hangman;

import java.util.ArrayList;
import java.util.List;

public class Model implements MvpModel {
    //private static final String[] WORDS = {"PENKTADIENIS", "KOMPIUTERIS", "SAVAITGALIS", "SODININKAS", "KLAVIATURA", "MIKROFONAS", "ZOOLOGIJA", "NOSINAITE", "VAIZDUOKLIS", "ZALIUZES", "SKAICIUOTUVAS"};
    //private static final String word = WORDS[new Random().nextInt(10) + 1];

    private MvpWordProvider wordProvider = new WordProvider();
    private static String word = "";
    private char[] guessedLetters;// = new char[getWordProvider().getWord().length()];
    private int numberOfGuesses = 0; //number of wrong guesses
    private char[] guesses = new char[32];
    private int totalNumberOfGuesses = 0;

    public MvpWordProvider getWordProvider() {
        return wordProvider;
    }

    @Override
    public void getNewWord() {
        getWordProvider().getWordFromApi();
        setWord(getWordProvider().getWord());
        guessedLetters = new char[getWordProvider().getWord().length()];
        resetVariables();
    }

    @Override
    public String getWord() {
        System.out.println("get word " + word);
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
    public void addCorrectGuess(char letter) {
        List<Integer> indexes = getAllIndexesOfLetter(letter);
        if (indexes.size() > 0)
            for (int i = 0; i < indexes.size(); i++)
                guessedLetters[indexes.get(i)] = Character.toUpperCase(letter);
        else increaseNumberOfGuesses();
    }

    @Override
    public boolean wordCompleted() {
        if (word.equals(String.valueOf(getGuessedLetters())))
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
        guesses[getTotalNumberOfGuesses()] = Character.toUpperCase(letter);
        incTotalNumberOfGuesses();
    }

    public int getTotalNumberOfGuesses() {
        return totalNumberOfGuesses;
    }

    public void incTotalNumberOfGuesses() {
        this.totalNumberOfGuesses++;
    }

    public void setWord(String str) {
        word = str.toUpperCase();
    }

    public void resetVariables() {
        numberOfGuesses = 0;
        guesses = new char[32];
        totalNumberOfGuesses = 0;
    }
}