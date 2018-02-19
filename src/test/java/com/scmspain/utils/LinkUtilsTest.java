package com.scmspain.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class LinkUtilsTest.
 */
@RunWith(SpringRunner.class)
public class LinkUtilsTest {

	/**
	 * Find link in first place.
	 */
	@Test
	public void findLinkInFirstPlace() {
		final String tweetText = "http://foogle.co Hey";
		final List<String> result = LinkUtils.getLinks(tweetText);

		Assert.assertTrue(result.size() == 1);
	}

	/**
	 * Find link in last place.
	 */
	@Test
	public void findLinkInLastPlace() {
		final String tweetText = "Hey http://foogle.co";
		final List<String> result = LinkUtils.getLinks(tweetText);

		Assert.assertTrue(result.size() == 1);
	}

	/**
	 * Find three link.
	 */
	@Test
	public void findThreeLink() {
		final String tweetText = "Hey http://foogle.co Hey http://foogle.co  test2 https://foogle.co";
		final List<String> result = LinkUtils.getLinks(tweetText);

		Assert.assertTrue(result.size() == 3);
	}
}
