package com.scmspain.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The Class AbstractRestFunction.
 *
 * @param <T> the generic type
 * @param <S> the generic type
 */
public class AbstractRestFunction<T, S> {

	/**
	 * Wrap data.
	 *
	 * @param data the data
	 * @return the s
	 * @throws SAIBException the SAIB exception
	 */
	public S wrapData(final T data) {
		return null;
	}

	/**
	 * Wrap data list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public List<S> wrapDataList(final List<T> list) {
		final List<S> resultList = new ArrayList<>();

		for (final T data : list) {
			resultList.add(wrapData(data));
		}

		return resultList;

	}

	/**
	 * Wrap data list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public List<S> wrapDataList(final Stream<T> list) {
		final List<S> resultList = new ArrayList<>();

		list.forEach(data -> {
			resultList.add(wrapData(data));
		});

		return resultList;

	}

}
