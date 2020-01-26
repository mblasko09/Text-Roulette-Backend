package com.chatroulette.chat.engine;

import com.jaunt.*;
import java.util.*;
import java.util.List;

public class TwitterScraper {
    private StringBuffer text;
    //public static void main(String[] args) {
    public List<String> StartScrape() {
     /*   String htmlTweets = "";
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit("https://twitter.com/realdonaldtrump?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor");
            //System.out.println(userAgent.doc.innerHTML());
            htmlTweets = userAgent.doc.innerHTML();
        } catch (JauntException e) {
            System.err.println(e);
        }
        while (htmlTweets.contains("<div class=\"js-tweet-text-container\"")) {
            int index = htmlTweets.indexOf("<div class=\"js-tweet-text-container\"");
            htmlTweets = htmlTweets.substring(index);
            htmlTweets = htmlTweets.substring(htmlTweets.indexOf("aria-label-part"));
            String tweet = htmlTweets.substring(20, htmlTweets.indexOf("/p"));
            htmlTweets = htmlTweets.substring(htmlTweets.indexOf("/p"));
            StringBuffer text = new StringBuffer(tweet);
            //if (tweet.contains("<a href=\"")) {
                tweet = tweet.replaceAll("\\s*<a.*</a>\\s*", "");
                //tweet = text.toString();
                tweet.replace("</a>", "");
                System.out.println(tweet + "\n\n\n\n");
         //   }
            //System.out.println(tweet+"\n\n\n\n");
        }
            */
        List<String> v = new ArrayList<>();
        v=getRandomTweets("n_stephens518");
        for (int i=0;i<v.size();i++)
            System.out.println(v.get(i)+"-------------------------------------------------------------------");
        return v;
        // System.out.println(getRandomTweets("n_stephens518"));
    }
    public static List<String> getRandomTweets(String userHandle) {
        String htmlTweets = "";
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit("https://twitter.com/" + userHandle + "?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor");
            //System.out.println(userAgent.doc.innerHTML());
            htmlTweets = userAgent.doc.innerHTML();
        } catch (JauntException e) {
            System.err.println(e);
        }
        Vector v = new Vector();
        while (htmlTweets.contains("<div class=\"js-tweet-text-container\"")) {
            int index = htmlTweets.indexOf("<div class=\"js-tweet-text-container\"");
            htmlTweets = htmlTweets.substring(index);
            htmlTweets = htmlTweets.substring(htmlTweets.indexOf("aria-label-part"));
            String tweet = htmlTweets.substring(20, htmlTweets.indexOf("/p"));
            htmlTweets = htmlTweets.substring(htmlTweets.indexOf("/p"));
            //if (tweet.contains("<a href=\"")) {
            //tweet = tweet.replaceAll("\\s*<a.*</a>\\s*", "");
            //tweet = text.toString();
            tweet.replace("</a>", "");
            StringBuffer text = new StringBuffer(tweet);
            tweet = text.toString();
            tweet = tweet.replaceAll("\\<.*?\\>", "");
            if (v.size()<10)
                v.add(tweet);
            //System.out.println(tweet);
            //   }
            //System.out.println(tweet+"\n\n\n\n");
        }
        return v;
    }
}