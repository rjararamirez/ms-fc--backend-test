package com.scmspain.configuration;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.export.MBeanExporter;

import com.scmspain.MsFcTechTestApplication;

/**
 * The Class TestConfiguration.
 */
@org.springframework.boot.test.context.TestConfiguration
@Import({ MsFcTechTestApplication.class })
public class TestConfiguration {

	@MockBean
	private MBeanExporter mockExporter;
}
