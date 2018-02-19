/*
 *
 */
package com.scmspain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class LinkUtils.
 */
public final class LinkUtils {

	/** The Constant WORD_SEPARATOR. */
	private static final String WORD_SEPARATOR = " ";

	/** The Constant HTTPS. */
	private static final String HTTPS = "https://";

	/** The Constant HTTP. */
	private static final String HTTP = "http://";

	/**
	 * Private constructor to prevent instantiation of the class.
	 */
	private LinkUtils() {

	}

	/**
	 * Gets the links.
	 *
	 * @param text the text
	 * @return the links
	 */
	public static List<String> getLinks(final String text) {
		final List<String> linksInText = new ArrayList<>();

		final String[] words = text.split(WORD_SEPARATOR);
		final List<String> stringList = Arrays.asList(words);

		stringList.forEach(word -> {
			word = word.trim();
			if (isLink(word)) {
				linksInText.add(word);
			}
		});

		return linksInText;
	}

	/**
	 * Checks if is link.
	 *
	 * @param word the word
	 * @return true, if is link
	 */
	private static boolean isLink(final String word) {
		return word.startsWith(HTTP) || word.startsWith(HTTPS);
	}

}
