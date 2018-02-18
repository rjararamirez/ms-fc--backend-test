package com.scmspain.services;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.scmspain.metrics.ApplicationMetrics;

/**
 * The Class CommonService.
 */
public class CommonService extends ApplicationMetrics {

	/** The entity manager. */
	@Autowired
	private transient EntityManager entityManager;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
