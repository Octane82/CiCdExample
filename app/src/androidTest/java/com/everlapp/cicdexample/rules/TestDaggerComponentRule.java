package com.everlapp.cicdexample.rules;

import android.support.v7.app.AppCompatActivity;

import com.everlapp.cicdexample.App;
import com.everlapp.cicdexample.di.ApplicationComponent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;



class TestDaggerComponentRule<A extends AppCompatActivity> implements TestRule {
    private final ActivityTestRule<A> activityRule;
    private final ApplicationComponent component;

    TestDaggerComponentRule(
            ActivityTestRule<A> activityRule, ApplicationComponent component) {
        this.activityRule = activityRule;
        this.component = component;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                App application =
                        ((App) activityRule.getActivity().getApplication());
                ApplicationComponent originalComponent = application.getComponent();
                application.setComponentForTest(component);
                try {
                    statement.evaluate();
                } finally {
                    application.setComponentForTest(originalComponent);
                }
            }
        };
    }
}