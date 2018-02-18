package com.scmspain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.scmspain.configuration.InfrastructureConfiguration;
import com.scmspain.configuration.TweetConfiguration;

/**
 * The Class MsFcTechTestApplication.
 */
@Configuration
@EnableAutoConfiguration
@Import({ TweetConfiguration.class, InfrastructureConfiguration.class })
public class MsFcTechTestApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(MsFcTechTestApplication.class, args);
	}
}
