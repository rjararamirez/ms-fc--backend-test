package com.scmspain.functions;

import org.springframework.stereotype.Component;

import com.scmspain.entities.Tweet;
import com.scmspain.response.dto.TweetDto;
import com.scmspain.utils.AbstractRestFunction;

/**
 * The Class TweetDtoFunction.
 */
@Component
public class TweetDtoFunction extends AbstractRestFunction<Tweet, TweetDto> {

	@Override
	public TweetDto wrapData(final Tweet tweet) {
		final TweetDto dto = new TweetDto();
		dto.setId(tweet.getId());
		dto.setPre2015MigrationStatus(tweet.getPre2015MigrationStatus());
		dto.setPublisher(tweet.getPublisher());
		dto.setTweet(tweet.getTweet());

		return dto;
	}

}
