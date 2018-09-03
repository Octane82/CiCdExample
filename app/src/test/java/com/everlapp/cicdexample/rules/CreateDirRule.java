package com.everlapp.cicdexample.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;

public class CreateDirRule implements TestRule {

    private final File dir;

    public CreateDirRule(File dir) {
        this.dir = dir;
    }

    @Override
    public Statement apply(final Statement s, Description d) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                dir.mkdir();
                try {
                    s.evaluate();
                } finally {
                    dir.delete();
                }
            }
        };
    }

}
