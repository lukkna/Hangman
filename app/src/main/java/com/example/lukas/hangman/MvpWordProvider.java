package com.example.lukas.hangman;

public interface MvpWordProvider {
    void getWordFromApi(WordReceived callback);

    interface WordReceived {
        void onWordReceived(String word);
    }
}
