package com.everlapp.cicdexample.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class CreateFileRule implements TestRule {

    private final File file;
    private final String text;


    public CreateFileRule(File file, String text) {
        this.file = file;
        this.text = text;
    }


    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                PrintWriter writer = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(file), "UTF-8")), true);
                writer.println(text);
                writer.close();

                try {
                    base.evaluate();
                } finally {
                    file.delete();
                }
            }
        };
    }
}
