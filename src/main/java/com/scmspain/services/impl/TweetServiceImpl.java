package com.scmspain.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.scmspain.entities.Tweet;
import com.scmspain.services.CommonService;
import com.scmspain.services.interfaces.TweetService;
import com.scmspain.utils.LinkUtils;

/**
 * The Class TweetServiceImpl.
 */
@Service(value = "tweetService")
@Transactional
public class TweetServiceImpl extends CommonService implements TweetService {

	/** The Constant GET_ALL_TWEETS. */
	private static final String GET_ALL_TWEETS = "SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 ORDER BY publishDate DESC";

	/** The Constant MAX_TWEET_LENGTH. */
	private static final int MAX_TWEET_LENGTH = 140;

	/** {@inheritDoc} **/
	@Override
	public void publishTweet(final String publisher, final String text) {
		publisherHasBeenInformed(publisher);
		textHasBeenInformed(text);
		validateTextLength(text);

		final Tweet tweet = new Tweet();
		tweet.setTweet(text);
		tweet.setPublisher(publisher);
		tweet.setPublishDate(Calendar.getInstance());

		metricIncrement("published-tweets");
		getEntityManager().persist(tweet);
	}

	/**
	 * Publisher has been informed.
	 *
	 * @param publisher the publisher
	 */
	private void publisherHasBeenInformed(final String publisher) {
		if (!StringUtils.hasText(publisher)) {
			throw new IllegalArgumentException("Tweet publisher must not be empty or null");
		}
	}

	/**
	 * Text has been informed.
	 *
	 * @param text the text
	 */
	private void textHasBeenInformed(final String text) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException("Tweet must not be empty or null");
		}
	}

	/**
	 * Validate text length.
	 *
	 * @param text the text
	 */
	private void validateTextLength(final String text) {
		final List<String> links = LinkUtils.getLinks(text);

		int ignoreChars = 0;
		if (links.size() > 0) {
			for (final String link : links) {
				ignoreChars = ignoreChars + link.length();
			}
		}

		final int finalLenght = text.length() - ignoreChars;
		if (finalLenght > MAX_TWEET_LENGTH) {
			throw new IllegalArgumentException("Tweet must not be greater than 140 characters");
		}

	}

	/** {@inheritDoc} **/
	@Override
	public Tweet getTweet(final Long id) {
		return getEntityManager().find(Tweet.class, id);
	}

	/** {@inheritDoc} **/
	@Override
	public List<Tweet> listAllTweets() {
		final List<Tweet> result = new ArrayList<>();
		metricIncrement("times-queried-tweets");
		final TypedQuery<Long> query = getEntityManager().createQuery(GET_ALL_TWEETS, Long.class);
		final List<Long> ids = query.getResultList();
		for (final Long id : ids) {
			result.add(getTweet(id));
		}
		return result;
	}
}
