package com.scmspain.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.scmspain.controller.command.DiscardTweetCommand;
import com.scmspain.controller.command.PublishTweetCommand;
import com.scmspain.response.dto.TweetDto;
import com.scmspain.services.interfaces.TweetService;

/**
 * The Class TweetController.
 */
@RestController
public class TweetController {

	/** The Constant TWEET_PATH. */
	private static final String TWEET_PATH = "/tweet";

	/** The Constant DISCARD_PATH. */
	private static final String DISCARD_PATH = "/discarded";

	/** The tweet service. */
	@Resource(name = "tweetService")
	private transient TweetService tweetService;

	/**
	 * List all tweets.
	 *
	 * @return the list
	 */
	@GetMapping(TWEET_PATH)
	@Transactional(readOnly = true)
	public List<TweetDto> listAllTweets() {
		return tweetService.listAllTweets();
	}

	/**
	 * Gets the tweets by user.
	 *
	 * @param publisher the publisher
	 * @return the tweets by user
	 */
	@GetMapping(TWEET_PATH + "/{publisher}")
	@Transactional(readOnly = true)
	public List<TweetDto> getTweetsByUser(@PathVariable final String publisher) {
		return tweetService.getTweetsByUser(publisher);
	}

	/**
	 * List all discarded tweets.
	 *
	 * @return the list
	 */
	@GetMapping(DISCARD_PATH)
	@Transactional(readOnly = true)
	public List<TweetDto> listAllDiscardedTweets() {
		return tweetService.listAllDiscardedTweets();
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
	 * Discard tweet.
	 *
	 * @param publishTweetCommand the publish tweet command
	 */
	@PostMapping(DISCARD_PATH)
	@ResponseStatus(ACCEPTED)
	public void discardTweet(@RequestBody final DiscardTweetCommand discardTweetCommand) {
		tweetService.discardTweet(discardTweetCommand.getTweet());
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
