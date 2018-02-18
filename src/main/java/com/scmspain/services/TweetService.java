package com.scmspain.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.scmspain.entities.Tweet;

/**
 * The Class TweetService.
 */
@Service
@Transactional
public class TweetService extends CommonService {

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

			metricIncrement("published-tweets");
			getEntityManager().persist(tweet);
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
		return getEntityManager().find(Tweet.class, id);
	}

	/**
	 * Recover tweet from repository.
	 *
	 * @return retrieved Tweet
	 */
	public List<Tweet> listAllTweets() {
		final List<Tweet> result = new ArrayList<>();
		metricIncrement("times-queried-tweets");
		final TypedQuery<Long> query = getEntityManager()
		        .createQuery("SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 ORDER BY id DESC", Long.class);
		final List<Long> ids = query.getResultList();
		for (final Long id : ids) {
			result.add(getTweet(id));
		}
		return result;
	}
}
