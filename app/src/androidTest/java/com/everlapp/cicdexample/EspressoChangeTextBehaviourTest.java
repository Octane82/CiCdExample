package com.everlapp.cicdexample;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * https://developer.android.com/training/testing/espresso/
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoChangeTextBehaviourTest {


    /**
     *
     *  Espresso — основной класс. Содержит в себе статические методы, такие как нажатия на системные кнопки (Back, Home), вызвать/спрятать клавиатуру, открыть меню, обратится к компоненту.
        ViewMatchers — позволяет найти компонент на экране в текущей иерархии.
        ViewActions — позволяет взаимодействовать с компонентом (click, longClick, doubleClick, swipe, scroll и т.д.).
        ViewAssertions — позволяет проверить состояние компонента.
     *
     */




    private String mStringToBeTyped;

    /**
     * Запускает Activity перед каждым тестом и заврывает его после
     */
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
