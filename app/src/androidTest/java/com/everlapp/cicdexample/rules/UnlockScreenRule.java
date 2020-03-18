package com.everlapp.cicdexample.rules;

import androidx.test.rule.ActivityTestRule;
import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class UnlockScreenRule<A extends AppCompatActivity> implements TestRule {

    ActivityTestRule<A> activityRule;

    UnlockScreenRule(ActivityTestRule<A> activityRule) {
        this.activityRule = activityRule;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                activityRule.runOnUiThread(() -> activityRule
                        .getActivity()
                        .getWindow()
                        .addFlags(
                                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                                        | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON));
                statement.evaluate();
            }
        };
    }


}
