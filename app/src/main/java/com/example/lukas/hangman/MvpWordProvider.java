package com.example.lukas.hangman;

public interface MvpWordProvider {
    void getWordFromApi();

    String getWord();
}
