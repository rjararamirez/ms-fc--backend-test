package com.scmspain.metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMetrics {

	/** The metric writer. */
	@Autowired
	private MetricWriter metricWriter;

	/**
	 * Metric Increment, the increment by default is 1.
	 *
	 * @param metricName metricName
	 */
	public void metricIncrement(final String metricName) {
		metricWriter.increment(new Delta<Number>(metricName, 1));
	}

}
