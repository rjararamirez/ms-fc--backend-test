package com.scmspain.services.validators;

import java.util.List;

import org.springframework.stereotype.Component;

import com.scmspain.utils.LinkUtils;
import com.scmspain.utils.ParamUtils;

/**
 * The Class TweetServiceValidator.
 */
@Component
public class TweetServiceValidator {

	/** The Constant MAX_TWEET_LENGTH. */
	private static final int MAX_TWEET_LENGTH = 140;

	/**
	 * Publisher has been informed.
	 *
	 * @param publisher the publisher
	 */
	public void publisherHasBeenInformed(final String publisher) {
		ParamUtils.paramIsInformed(publisher, "Tweet publisher must not be empty or null");
	}

	/**
	 * Text has been informed.
	 *
	 * @param text the text
	 */
	public void textHasBeenInformed(final String text) {
		ParamUtils.paramIsInformed(text, "Tweet must not be empty or null");
	}

	/**
	 * Validate text length.
	 *
	 * @param text the text
	 */
	public void validateTextLength(final String text) {
		final List<String> links = LinkUtils.getLinks(text);

		int ignoreChars = 0;
		if (links.size() > 0) {
			for (final String link : links) {
				ignoreChars = ignoreChars + link.length();
			}
		}

		final int finalLenght = text.length() - ignoreChars;
		if (finalLenght > MAX_TWEET_LENGTH) {
			throw new IllegalArgumentException(String.format("Tweet must not be greater than %s characters", MAX_TWEET_LENGTH));
		}

	}

}
