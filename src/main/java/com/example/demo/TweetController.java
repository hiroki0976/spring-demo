package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private TweetService tweetService;

    @GetMapping
    public String welcome(
            @ModelAttribute Tweet tweet,
            @AuthenticationPrincipal User user,
            Model model) {
        Iterable<Tweet> tweets = tweetRepository.findAll();

        model.addAttribute("user",user);
        model.addAttribute("tweets", tweets);
        return "welcome";
    }

    @GetMapping("/json")
    public @ResponseBody Iterable<Tweet> json() {
        Iterable<Tweet> tweets = tweetRepository.findAll();
        return tweets;
    }

    @PostMapping
    public String tweet(
            @ModelAttribute @Validated Tweet tweet,
            BindingResult result,
            @AuthenticationPrincipal User user,
            Model model) {
        if (result.hasErrors()) {
            return welcome(tweet, user, model);
        }

        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUser(user);
        tweetRepository.save(tweet);

        return "redirect:/";
    }

    @GetMapping("{id}")
    public String delete(@PathVariable int id) {
        Tweet tweet = tweetRepository.findOne(id);
        tweetService.deleteTweet(tweet);
        return "redirect:/";
    }
}
