package com.everlapp.cicdexample;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviourTest {

    private String mStringToBeTyped;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void initValidString() {
        mStringToBeTyped = "Espresso";
    }


    /**
     * onView() - find view  ( onView(withText("Sign-in")); onView(withId(R.id.button_signin));  )
     *
     * Call the ViewInteraction.perform() or DataInteraction.perform() methods to simulate user interactions on the UI component.
     *
     * The ViewActions class provides a list of helper methods for specifying common actions
     *
     */

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button
        onView(withId(R.id.editTextUserInput)).perform(typeText(mStringToBeTyped), closeSoftKeyboard());
        // Click button (when button clicked textToBeChanged)
        onView(withId(R.id.btnChangeText)).perform(click());

        // Check that the text was changed (this function should be realized in MainActivity)
        onView(withId(R.id.textToBeChanged)).check(matches(withText(mStringToBeTyped)));

    }


}
