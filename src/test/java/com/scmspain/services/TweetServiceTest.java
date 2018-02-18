package com.scmspain.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import com.scmspain.entities.Tweet;

/**
 * The Class TweetServiceTest.
 */
public class TweetServiceTest {

	/** The entity manager. */
	private EntityManager entityManager;

	/** The metric writer. */
	private MetricWriter metricWriter;

	/** The tweet service. */
	private TweetService tweetService;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		entityManager = mock(EntityManager.class);
		metricWriter = mock(MetricWriter.class);

		tweetService = new TweetService(entityManager, metricWriter);
	}

	/**
	 * Should insert A new tweet.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldInsertANewTweet() throws Exception {
		tweetService.publishTweet("Guybrush Threepwood", "I am Guybrush Threepwood, mighty pirate.");

		verify(entityManager).persist(any(Tweet.class));
	}

	/**
	 * Should throw an exception when tweet length is invalid.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnExceptionWhenTweetLengthIsInvalid() throws Exception {
		tweetService.publishTweet("Pirate",
		        "LeChuck? He's the guy that went to the Governor's for dinner and never wanted to leave. He fell for her in a big way, but she told him to drop dead. So he did. Then things really got ugly.");
	}
}
