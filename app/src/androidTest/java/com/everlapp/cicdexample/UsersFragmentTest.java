package com.everlapp.cicdexample;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.everlapp.cicdexample.rules.CreateFileRule;
import com.everlapp.cicdexample.rules.FragmentTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;




@RunWith(AndroidJUnit4.class)
public class UsersFragmentTest {

    @Rule
    public final RuleChain ruleChain = RuleChain
            .outerRule(new CreateFileRule(getTestFile(), "{name: Dima}"))
            .around(new FragmentTestRule<>(MainActivity.class, new UsersFragment()));


    @Test
    public void nameDisplayedTest() {
        onView(withText("Dima")).check(matches(isDisplayed()));
    }



    private File getTestFile() {
        return new File(InstrumentationRegistry.getTargetContext()
                .getFilesDir().getAbsoluteFile()
                + File.separator
                + "test_file");
    }

}