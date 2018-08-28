package com.everlapp.cicdexample;


import com.everlapp.cicdexample.mockitoobj.MockMyClass;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * http://www.vogella.com/tutorials/Mockito/article.html
 *
 * https://github.com/jeroenmols/mockitoexample
 *
 */
public class MockitoTest {

    @Test
    public void test1() {
        // Create mock
        MockMyClass myClass = mock(MockMyClass.class);

        // define return value for method getUniqueId()
        when(myClass.getUniqueId()).thenReturn(43);

        // Use mock in test
        assertEquals(myClass.getUniqueId(), 43);
    }


    @Test
    public void testLinkedListSpy() {
        // Lets mock a linked list
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // when(spy.get(0)).thenReturn("foo");            -- Incorrect for SPY

        // doReturn() for stubbing
        doReturn("foo").when(spy).get(0);


        assertEquals("foo", spy.get(0));
    }


}
