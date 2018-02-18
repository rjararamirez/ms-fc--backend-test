package com.scmspain.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.entities.Tweet;
import com.scmspain.services.TweetService;

/**
 * The Class TweetController.
 */
@RestController
public class TweetController {

	/** The Constant TWEET_PATH. */
	private static final String TWEET_PATH = "/tweet";

	/** The tweet service. */
	@Resource(name = "tweetService")
	private transient TweetService tweetService;

	/**
	 * List all tweets.
	 *
	 * @return the list
	 */
	@GetMapping(TWEET_PATH)
	public List<Tweet> listAllTweets() {
		return tweetService.listAllTweets();
	}

	/**
	 * Publish tweet.
	 *
	 * @param publishTweetCommand the publish tweet command
	 */
	@PostMapping(TWEET_PATH)
	@ResponseStatus(CREATED)
	public void publishTweet(@RequestBody final PublishTweetCommand publishTweetCommand) {
		tweetService.publishTweet(publishTweetCommand.getPublisher(), publishTweetCommand.getTweet());
	}

	/**
	 * Invalid argument exception.
	 *
	 * @param ex the ex
	 * @return the object
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public Object invalidArgumentException(final IllegalArgumentException ex) {
		return new Object() {
			public String message = ex.getMessage();
			public String exceptionClass = ex.getClass().getSimpleName();
		};
	}
}
