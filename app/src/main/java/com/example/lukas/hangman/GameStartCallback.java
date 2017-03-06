package com.example.lukas.hangman;

interface GameStartCallback {
    void gameStarted();

    void gameFailedToStart();
}
