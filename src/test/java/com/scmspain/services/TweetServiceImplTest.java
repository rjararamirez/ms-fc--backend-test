package com.scmspain.services;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.scmspain.entities.Tweet;
import com.scmspain.functions.TweetDtoFunction;
import com.scmspain.services.impl.TweetServiceImpl;
import com.scmspain.services.validators.TweetServiceValidator;

/**
 * The Class TweetServiceTest.
 */
@RunWith(SpringRunner.class)
public class TweetServiceImplTest {

	/** The tweet service. */
	@InjectMocks
	private TweetServiceImpl tweetService;

	/** The entity manager. */
	@MockBean
	private EntityManager entityManager;

	/** The metric writer. */
	@MockBean
	private MetricWriter metricWriter;

	@MockBean
	private TweetDtoFunction tweetDtoFunction;

	/** The tweet service validator. */
	@MockBean
	private TweetServiceValidator tweetServiceValidator;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
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

		doThrow(new IllegalArgumentException()).when(tweetServiceValidator).validateTextLength(anyString());
		tweetService.publishTweet("Pirate",
		        "LeChuck? He's the guy that went to the Governor's for dinner and never wanted to leave. He fell for her in a big way, but she told him to drop dead. So he did. Then things really got ugly.");

	}
}
