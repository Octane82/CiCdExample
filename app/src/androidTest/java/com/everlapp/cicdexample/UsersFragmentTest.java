package com.everlapp.cicdexample;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.everlapp.cicdexample.rules.CreateFileRule;
import com.everlapp.cicdexample.rules.FragmentAsyncTestRule;
import com.everlapp.cicdexample.rules.FragmentTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.*;




@RunWith(AndroidJUnit4.class)
public class UsersFragmentTest {

    @Rule
    public final RuleChain ruleChain = RuleChain
            .outerRule(new CreateFileRule(getTestFile(), "{name: Dima}"))
            .around(new FragmentTestRule<>(MainActivity.class, new UsersFragment()));

    /**
     * Правило необходимо для того чтобы тест упал
     * если асинхронные действия будут обращаться к полям фрагмента после его завершения.
     */
    @ClassRule
    public static TestRule asyncRule =
            new FragmentAsyncTestRule<>(MainActivity.class, new UsersFragment());


    @Test
    public void nameDisplayedTest() {
        // onView(withText("Dima")).check(matches(isDisplayed()));

        // Rx test
        await()
            .atMost(5, TimeUnit.SECONDS)
            .ignoreExceptions()
            .untilAsserted(()
                    -> onView(withText("Dima"))
                    .check(matches(isDisplayed())));
    }



    private File getTestFile() {
        return new File(InstrumentationRegistry.getTargetContext()
                .getFilesDir().getAbsoluteFile()
                + File.separator
                + "test_file");
    }

}