package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Transactional
    @PreAuthorize("#tweet.user.id == principal.id")
    public void deleteTweet(Tweet tweet) {
        tweetRepository.delete(tweet);
    }
}
