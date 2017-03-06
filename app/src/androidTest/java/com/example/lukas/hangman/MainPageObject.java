package com.example.lukas.hangman;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

class MainPageObject {
    static void clickGuessLetterButton() {
        onView(withId(R.id.button))
                .perform(click());
    }

    static void putLetterIntoGuessLetterTextBox(char letter) {
        onView(withId(R.id.editText))
                .perform(typeText(String.valueOf(letter)));
    }

    static void closeKeyboard() {
        onView(withId(R.id.button))
                .perform(closeSoftKeyboard());
    }
}