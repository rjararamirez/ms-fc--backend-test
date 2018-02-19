package com.scmspain.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scmspain.entities.Tweet;
import com.scmspain.functions.TweetDtoFunction;
import com.scmspain.response.dto.TweetDto;
import com.scmspain.services.CommonService;
import com.scmspain.services.interfaces.TweetService;
import com.scmspain.services.validators.TweetServiceValidator;

/**
 * The Class TweetServiceImpl.
 */
@Service(value = "tweetService")
@Transactional
public class TweetServiceImpl extends CommonService implements TweetService {

	/** The Constant GET_ALL_TWEETS. */
	private static final String GET_ALL_TWEETS = "SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 AND discarted = false ORDER BY publishDate DESC";

	/** The Constant GET_DISCARDED_TWEETS. */
	private static final String GET_DISCARDED_TWEETS = "SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 AND discarted = true ORDER BY publishDate DESC";

	/** The Constant GET_TWEETS_BY_USER. */
	private static final String GET_TWEETS_BY_USER = "SELECT id FROM Tweet AS tweetId WHERE pre2015MigrationStatus<>99 AND discarted = false AND publisher = :publisherName ORDER BY publishDate DESC";

	/** The tweet dto function. */
	@Autowired
	private transient TweetDtoFunction tweetDtoFunction;

	/** The tweet service validator. */
	@Autowired
	private transient TweetServiceValidator tweetServiceValidator;

	/** {@inheritDoc} **/
	@Override
	public void publishTweet(final String publisher, final String text) {
		tweetServiceValidator.publisherHasBeenInformed(publisher);
		tweetServiceValidator.textHasBeenInformed(text);
		tweetServiceValidator.validateTextLength(text);

		final Tweet tweet = new Tweet();
		tweet.setTweet(text);
		tweet.setPublisher(publisher);
		tweet.setPublishDate(Calendar.getInstance());

		metricIncrement("published-tweets");
		getEntityManager().persist(tweet);
	}

	/** {@inheritDoc} **/
	@Override
	public void discardTweet(final Long tweetId) {
		final Tweet tweet = getTweet(tweetId);
		if (tweet == null) {
			throw new IllegalArgumentException(String.format("Tweet with id %s dont exist", tweetId));
		}
		tweet.setDiscarted(true);
		getEntityManager().merge(tweet);
	}

	/** {@inheritDoc} **/
	@Override
	public Tweet getTweet(final Long id) {
		return getEntityManager().find(Tweet.class, id);
	}

	/** {@inheritDoc} **/
	@Override
	public List<TweetDto> listAllTweets() {
		final TypedQuery<Long> query = getEntityManager().createQuery(GET_ALL_TWEETS, Long.class);
		return tweetDtoFunction.wrapDataList(getResult(query));
	}

	/** {@inheritDoc} **/
	@Override
	public List<TweetDto> getTweetsByUser(final String publisher) {
		tweetServiceValidator.publisherHasBeenInformed(publisher);

		final TypedQuery<Long> query = getEntityManager().createQuery(GET_TWEETS_BY_USER, Long.class);
		query.setParameter("publisherName", publisher);
		return tweetDtoFunction.wrapDataList(getResult(query));
	}

	/** {@inheritDoc} **/
	@Override
	public List<TweetDto> listAllDiscardedTweets() {
		final TypedQuery<Long> query = getEntityManager().createQuery(GET_DISCARDED_TWEETS, Long.class);
		return tweetDtoFunction.wrapDataList(getResult(query));
	}

	/**
	 * Gets the result.
	 *
	 * @param query the query
	 * @return the result
	 */
	private List<Tweet> getResult(final TypedQuery<Long> query) {
		metricIncrement("times-queried-tweets");
		final List<Tweet> result = new ArrayList<>();
		final List<Long> ids = query.getResultList();
		for (final Long id : ids) {
			result.add(getTweet(id));
		}

		return result;
	}

}
