package com.scmspain.utils;

import org.springframework.util.StringUtils;

/**
 * The Class ParamUtils.
 */
public final class ParamUtils {

	/**
	 * Private constructor to prevent instantiation of the class.
	 */
	private ParamUtils() {

	}

	/**
	 * Param is informed.
	 *
	 * @param param the param
	 * @param errorMessage the error message
	 */
	public static void paramIsInformed(final String param, final String errorMessage) {
		if (!StringUtils.hasText(param)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

}
