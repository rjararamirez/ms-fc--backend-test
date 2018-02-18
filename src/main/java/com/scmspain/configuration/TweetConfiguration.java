package com.scmspain.configuration;

import javax.persistence.EntityManager;

import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scmspain.controller.TweetController;
import com.scmspain.services.TweetService;

/**
 * The Class TweetConfiguration.
 */
@Configuration
public class TweetConfiguration {

	/**
	 * Gets the tweet service.
	 *
	 * @param entityManager the entity manager
	 * @param metricWriter the metric writer
	 * @return the tweet service
	 */
	@Bean
	public TweetService getTweetService(final EntityManager entityManager, final MetricWriter metricWriter) {
		return new TweetService(entityManager, metricWriter);
	}

	/**
	 * Gets the tweet configuration.
	 *
	 * @param tweetService the tweet service
	 * @return the tweet configuration
	 */
	@Bean
	public TweetController getTweetConfiguration(final TweetService tweetService) {
		return new TweetController(tweetService);
	}
}
