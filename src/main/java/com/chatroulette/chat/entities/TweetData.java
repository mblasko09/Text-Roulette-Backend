package com.chatroulette.chat.entities;

public class TweetData {

    private String tweet;
    private String handle;

    public TweetData() {}

    public TweetData(String tweet, String handle) {
        this.tweet = tweet;
        this.handle = handle;
    }

    public String getTweet() {
        return this.tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getHandle() {
        return this.handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

}
