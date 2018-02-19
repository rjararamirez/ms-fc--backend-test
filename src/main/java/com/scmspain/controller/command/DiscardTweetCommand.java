package com.scmspain.controller.command;

/**
 * The Class DiscardTweetCommand.
 */
public class DiscardTweetCommand extends AbstractCommand {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4880993667495254272L;

	/** The tweet. */
	private Long tweet;

	/**
	 * Gets the tweet.
	 *
	 * @return the tweet
	 */
	public Long getTweet() {
		return tweet;
	}

	/**
	 * Sets the tweet.
	 *
	 * @param tweet the new tweet
	 */
	public void setTweet(final Long tweet) {
		this.tweet = tweet;
	}

}
