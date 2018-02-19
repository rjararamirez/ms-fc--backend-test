package com.scmspain.controller.command;

/**
 * The Class PublishTweetCommand.
 */
public class PublishTweetCommand extends AbstractCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3995355578813237218L;

	/** The publisher. */
	private String publisher;

	/** The tweet. */
	private String tweet;

	/**
	 * Gets the publisher.
	 *
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher.
	 *
	 * @param publisher the new publisher
	 */
	public void setPublisher(final String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets the tweet.
	 *
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}

	/**
	 * Sets the tweet.
	 *
	 * @param tweet the new tweet
	 */
	public void setTweet(final String tweet) {
		this.tweet = tweet;
	}
}
