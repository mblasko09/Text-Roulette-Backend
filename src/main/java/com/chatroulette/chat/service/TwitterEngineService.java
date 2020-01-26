package com.chatroulette.chat.service;

import com.chatroulette.chat.engine.TwitterScraper;
import com.chatroulette.chat.entities.TweetData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterEngineService {

    private TweetData tweetData;

    private TwitterScraper twitterScraper;

    public TwitterEngineService() {
        tweetData = new TweetData();
        twitterScraper = new TwitterScraper();
    }

    public TwitterEngineService(String tweet, String handle) {
        tweetData = new TweetData(tweet, handle);
    }

    public List<String> getTweetData() {
        return twitterScraper.StartScrape();
        //return this.tweetData;
    }

    public void setTweetData(String tweet, String handle) {
        this.tweetData.setTweet(tweet);
        this.tweetData.setHandle(handle);
    }

}
