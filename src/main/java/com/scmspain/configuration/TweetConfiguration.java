package com.scmspain.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The Class TweetConfiguration.
 */
@Configuration
@ComponentScan({ "com.scmspain.controller", "com.scmspain.services" })
public class TweetConfiguration {

}
