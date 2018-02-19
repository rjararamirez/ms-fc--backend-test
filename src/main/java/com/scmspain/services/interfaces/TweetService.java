/*
 *
 */
package com.scmspain.services.interfaces;

import java.util.List;

import com.scmspain.entities.Tweet;
import com.scmspain.response.dto.TweetDto;

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
	void publishTweet(String publisher, String text);

	/**
	 * Discard tweet.
	 *
	 * @param tweetId the tweet id
	 */
	void discardTweet(Long tweetId);

	/**
	 * Gets the tweet.
	 *
	 * @param id the id
	 * @return the tweet
	 */
	Tweet getTweet(Long id);

	/**
	 * List all tweets.
	 *
	 * @return the list
	 */
	List<TweetDto> listAllTweets();

	/**
	 * Gets the tweets by user.
	 *
	 * @param publisher the publisher
	 * @return the tweets by user
	 */
	List<TweetDto> getTweetsByUser(String publisher);

	/**
	 * List all discarded tweets.
	 *
	 * @return the list
	 */
	List<TweetDto> listAllDiscardedTweets();

}
