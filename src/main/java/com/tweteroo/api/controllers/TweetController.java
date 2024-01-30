package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.services.TweetService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("tweets")
public class TweetController {
  private final TweetService tweetService;

  public TweetController(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  @PostMapping
  public ResponseEntity<Object> postTweet(@RequestBody @Valid TweetDTO tweetDTO) {
    Optional<TweetModel> tweet = tweetService.create(tweetDTO);

    if (!tweet.isPresent()) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(tweet.get());
  }

  @GetMapping
  public List<TweetModel> getAllTweets() {
    return tweetService.findAll();
  }

  @GetMapping("/user/{userId}")
  public List<TweetModel> getTweetsByUserId(@PathVariable("userId") Long userId) {
    return tweetService.findByUserId(userId);
  }
}
