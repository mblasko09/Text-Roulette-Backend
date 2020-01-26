package com.chatroulette.chat.service;

import com.chatroulette.chat.entities.TweetData;
import org.springframework.stereotype.Service;

@Service
public class TwitterEngineService {

    private TweetData tweetData;

    public TwitterEngineService() {
        tweetData = new TweetData();
    }

    public TwitterEngineService(String tweet, String handle) {
        tweetData = new TweetData(tweet, handle);
    }

    public TweetData getTweetData() {
        return this.tweetData;
    }

    public void setTweetData(String tweet, String handle) {
        this.tweetData.setTweet(tweet);
        this.tweetData.setHandle(handle);
    }

}
