package com.example.lukas.hangman;

import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
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
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class UItest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("WORD")));
    }

    @Test
    public void testEmptyEditBox() {
        onView(withId(R.id.button))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    @Test
    public void textGuessPrinting() throws InterruptedException {
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
    public void testDefeat() {
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

    @Test
    public void testVictory() {
//        onView(withId(R.id.button2))
//                .perform(click());
//
//        SystemClock.sleep(3000);
//
//        char[] word = new char[]{'W', 'O', 'R', 'D'};
        char[] word = new char[]{'C', 'O', 'M', 'P', 'U', 'T', 'E', 'R'};
        for (char aWord : word) {
            onView(withId(R.id.editText))
                    .perform(typeText(String.valueOf(aWord)));

            onView(withId(R.id.button))
                    .perform(click());
        }

        onView(withId(R.id.editText))
                .check(matches(not(isEnabled())));

        onView(withId(R.id.button))
                .check(matches(not(isEnabled())));
    }

    @Test
    public void testRotation() {
        onView(withId(R.id.editText))
                .perform(typeText("A"));
        onView(withId(R.id.button))
                .perform(click());

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        SystemClock.sleep(3000);

        onView(withId(R.id.editText))
                .perform(typeText("B"), closeSoftKeyboard());
        onView(withId(R.id.button))
                .perform(click());

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SystemClock.sleep(3000);
    }
}