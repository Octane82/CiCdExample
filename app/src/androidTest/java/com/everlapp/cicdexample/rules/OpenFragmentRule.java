package com.everlapp.cicdexample.rules;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.everlapp.cicdexample.R;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


/**
 * правило, запускающее фрагмент и ожидающее его запуска с помощью библиотеки Awaitility
 * @param <A>
 */
public class OpenFragmentRule<A extends AppCompatActivity> implements TestRule {

    private final ActivityTestRule<A> activityRule;
    private final Fragment fragment;
    private final int timeoutSec = 5;

    /**
     * Create fragment test rule, don't forget to add @Rule.
     *
     * @param activityRule - Activity Test Rule for starting fragment.
     * @param fragment - fragment for opening.
     */
    OpenFragmentRule(ActivityTestRule<A> activityRule, Fragment fragment) {
        this.activityRule = activityRule;
        this.fragment = fragment;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                openFragment(activityRule.getActivity(), fragment);

                // если в течении пяти секунд фрагмент не запустится, то тест не будет пройден
                await().atMost(timeoutSec, SECONDS).until(fragment::isResumed);
                statement.evaluate();
            }
        };
    }


    private static void openFragment(AppCompatActivity activity, Fragment newFragment) {
        activity
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, newFragment)
                .commitAllowingStateLoss();
    }



}
