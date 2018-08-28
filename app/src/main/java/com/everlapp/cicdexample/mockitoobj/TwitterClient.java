package com.everlapp.cicdexample.mockitoobj;

public class TwitterClient {

    public void sendTweet(ITweet tweet) {
        String message = tweet.getMessage();

        // send the message to Twitter
    }

}
