package com.scmspain.configuration;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

/**
 * The Class InfrastructureConfiguration.
 */
@Configuration
public class InfrastructureConfiguration {

	/**
	 * Gets the metric writer.
	 *
	 * @param exporter the exporter
	 * @return the metric writer
	 */
	@Bean
	@ExportMetricWriter
	public MetricWriter getMetricWriter(final MBeanExporter exporter) {
		return new JmxMetricWriter(exporter);
	}
}
