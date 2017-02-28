package com.example.lukas.hangman;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;



@RunWith(AndroidJUnit4.class)
public class test {

//    private MainActivity activity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class);

//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void emptyTextBoxError() {
        onView(withId(R.id.button))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void printAGuess() throws InterruptedException {
        onView(withId(R.id.editText))
                .perform(typeText("a"), closeSoftKeyboard());

        onView(withId(R.id.button))
                .perform(click());

        //Thread.sleep(2000);
        Matcher<View> viewMatcher = withId(R.id.textView2);

        ViewInteraction viewInteraction = onView(viewMatcher);

        viewInteraction.check(matches(withText(new CustomMatcher<String>("kazkas") {
            @Override
            public boolean matches(Object item) {
                return ((String) item).matches("^A\\W*");
            }

        })));
    }

    @Test
    public void disabledInputAfterLoss() {
        for (int i = 0; i < 11; i++) {
            onView(withId(R.id.editText))
                    .perform(typeText("."));

            onView(withId(R.id.button))
                    .perform(click());
        }

        onView(withId(R.id.editText))
                .check(matches(not(isEnabled())));

        onView(withId(R.id.button))
                .check(matches(not(isEnabled())));
    }
//
//    @Test
//    public void sampleGameplay() {
//
//
//
//    }
}