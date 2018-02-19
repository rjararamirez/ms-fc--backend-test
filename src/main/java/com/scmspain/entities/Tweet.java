package com.scmspain.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class Tweet.
 */
@Entity
public class Tweet implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3665728846322043267L;

	/** The id. */
	@Id
	@GeneratedValue
	private Long id;

	/** The publisher. */
	@Column(nullable = false)
	private String publisher;

	/** The tweet. */
	@Column(nullable = false, length = 1000)
	private String tweet;

	/** The pre 2015 migration status. */
	@Column(nullable = true)
	private Long pre2015MigrationStatus = 0L;

	@Column(nullable = false)
	private Calendar publishDate;

	/** The discarted. */
	@Column
	private boolean discarted;

	/**
	 * Instantiates a new tweet.
	 */
	public Tweet() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

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

	/**
	 * Gets the pre 2015 migration status.
	 *
	 * @return the pre 2015 migration status
	 */
	public Long getPre2015MigrationStatus() {
		return pre2015MigrationStatus;
	}

	/**
	 * Sets the pre 2015 migration status.
	 *
	 * @param pre2015MigrationStatus the new pre 2015 migration status
	 */
	public void setPre2015MigrationStatus(final Long pre2015MigrationStatus) {
		this.pre2015MigrationStatus = pre2015MigrationStatus;
	}

	/**
	 * Sets the publish date.
	 *
	 * @param publishDate the new publish date
	 */
	public void setPublishDate(final Calendar publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * Sets the discarted.
	 *
	 * @param discarted the new discarted
	 */
	public void setDiscarted(final boolean discarted) {
		this.discarted = discarted;
	}

}
