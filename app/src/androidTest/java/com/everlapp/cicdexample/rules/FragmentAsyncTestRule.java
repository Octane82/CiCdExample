package com.everlapp.cicdexample.rules;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.everlapp.cicdexample.R;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FragmentAsyncTestRule<A extends AppCompatActivity> implements TestRule {

    private final android.support.test.rule.ActivityTestRule<A> activityRule;
    private final Fragment fragment;

    public FragmentAsyncTestRule(Class<A> activityClass, Fragment fragment) {
        this.activityRule = new android.support.test.rule.ActivityTestRule<>(activityClass);
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
