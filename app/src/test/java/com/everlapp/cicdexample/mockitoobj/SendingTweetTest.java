package com.everlapp.cicdexample.mockitoobj;

import org.junit.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SendingTweetTest {


    @Test
    public void testSendingTweet() {
        TwitterClient client = new TwitterClient();

        ITweet tweet = mock(ITweet.class);

        when(tweet.getMessage()).thenReturn("Using Mockito is great");

        client.sendTweet(tweet);

        verify(tweet, atLeastOnce()).getMessage();
    }

}