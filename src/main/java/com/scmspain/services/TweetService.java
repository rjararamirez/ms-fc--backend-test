package com.scmspain.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.stereotype.Service;

import com.scmspain.entities.Tweet;

/**
 * The Class TweetService.
 */
@Service
@Transactional
public class TweetService {

	/** The entity manager. */
	private final EntityManager entityManager;

	/** The metric writer. */
	private final MetricWriter metricWriter;

	/**
	 * Instantiates a new tweet service.
	 *
	 * @param entityManager the entity manager
	 * @param metricWriter the metric writer
	 */
	public TweetService(final EntityManager entityManager, final MetricWriter metricWriter) {
		this.entityManager = entityManager;
		this.metricWriter = metricWriter;
	}

	/**
	 * Push tweet to repository.
	 *
	 * @param publisher --> creator of the Tweet
	 * @param text --> Content of the Tweet
	 */
	public void publishTweet(final String publisher, final String text) {
		if (publisher != null && publisher.length() > 0 && text != null && text.length() > 0 && text.length() < 140) {
			final Tweet tweet = new Tweet();
			tweet.setTweet(text);
			tweet.setPublisher(publisher);

			metricWriter.increment(new Delta<Number>("published-tweets", 1));
			entityManager.persist(tweet);
		} else {
			throw new IllegalArgumentException("Tweet must not be greater than 140 characters");
		}
	}

	/**
	 * Recover tweet from repository Parameter.
	 *
	 * @param id --> id of the Tweet to retrieve
	 * @return the tweet
	 */
	public Tweet getTweet(final Long id) {
		return entityManager.find(Tweet.class, id);
	}

	/**
	 * Recover tweet from repository.
	 *
	 * @return retrieved Tweet
	 */
	public List<Tweet> listAllTweets() {
		final List<Tweet> result = new ArrayList<>();
		metricWriter.increment(new Delta<Number>("times-queried-tweets", 1));
		final TypedQuery<Long> query = entityManager
		        .createQuery("SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 ORDER BY id DESC", Long.class);
		final List<Long> ids = query.getResultList();
		for (final Long id : ids) {
			result.add(getTweet(id));
		}
		return result;
	}
}
