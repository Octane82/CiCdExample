package com.everlapp.cicdexample.rules;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.everlapp.cicdexample.R;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FragmentAsyncTestRule<A extends AppCompatActivity> implements TestRule {

    private final androidx.test.rule.ActivityTestRule<A> activityRule;
    private final Fragment fragment;

    public FragmentAsyncTestRule(Class<A> activityClass, Fragment fragment) {
        this.activityRule = new androidx.test.rule.ActivityTestRule<>(activityClass);
        this.fragment = fragment;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } finally {
                    activityRule.launchActivity(new Intent());
                    openFragment(activityRule.getActivity(), fragment);
                    openFragment(activityRule.getActivity(), new Fragment());
                }
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
