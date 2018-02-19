/*
 *
 */
package com.scmspain.services.interfaces;

import java.util.List;

import com.scmspain.entities.Tweet;

/**
 * The Interface TweetService.
 */
public interface TweetService {

	/**
	 * Publish tweet.
	 *
	 * @param publisher the publisher
	 * @param text the text
	 */
	void publishTweet(final String publisher, final String text);

	/**
	 * Gets the tweet.
	 *
	 * @param id the id
	 * @return the tweet
	 */
	Tweet getTweet(final Long id);

	/**
	 * List all tweets.
	 *
	 * @return the list
	 */
	List<Tweet> listAllTweets();

}