package com.scmspain.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class ParamUtilsTest.
 */
@RunWith(SpringRunner.class)
public class ParamUtilsTest {

	/**
	 * Param is informed.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void paramIsInformed() throws Exception {
		ParamUtils.paramIsInformed("Hello", "I'm greeting");
	}

	/**
	 * Param not informed.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void paramNotInformed() throws Exception {
		ParamUtils.paramIsInformed(null, "I'm greeting");
	}

}
