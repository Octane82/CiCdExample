package com.everlapp.cicdexample;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


// https://developer.android.com/training/testing/unit-testing/

/**
 *
 * Mockito API reference
 * https://static.javadoc.io/org.mockito/mockito-core/2.21.0/org/mockito/Mockito.html
 *
 * https://github.com/googlesamples/android-testing/tree/master/unit/BasicSample
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTestSample {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mMockContext;


    @Test
    public void readString() {
        when(mMockContext.getString(R.string.hello_world)).thenReturn(FAKE_STRING);

        ClassForTest classForTest = new ClassForTest(mMockContext);

        String getString = classForTest.getHelloWorld();

        assertThat(getString, is(FAKE_STRING));
    }

}
