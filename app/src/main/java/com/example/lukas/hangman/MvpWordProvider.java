package com.example.lukas.hangman;

interface MvpWordProvider {
    void getWordFromApi(WordReceived callback);

    interface WordReceived {
        void onWordReceived(String word);
    }
}
