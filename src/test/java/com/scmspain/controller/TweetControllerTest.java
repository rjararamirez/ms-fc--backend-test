package com.scmspain.controller;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scmspain.configuration.TestConfiguration;

/**
 * The Class TweetControllerTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestConfiguration.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TweetControllerTest {

	/** The context. */
	@Autowired
	private WebApplicationContext context;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		mockMvc = webAppContextSetup(context).build();
	}

	/**
	 * Should return 200 when inserting A valid tweet.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldReturn200WhenInsertingAValidTweet() throws Exception {
		mockMvc.perform(newTweet("Prospect", "Breaking the law")).andExpect(status().is(201));
	}

	/**
	 * Should return 200 when inserting tweet with link.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldReturn200WhenInsertingTweetWithLink() throws Exception {
		mockMvc.perform(newTweet("Schibsted Spain",
		        "We are Schibsted Spain (look at our home page http://www.schibsted.es/), we own Vibbo, InfoJobs, fotocasa, coches.net and milanuncios. Welcome!"))
		        .andExpect(status().is(201));
	}

	/**
	 * Should return 400 when inserting an invalid tweet.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldReturn400WhenInsertingAnInvalidTweet() throws Exception {
		mockMvc.perform(newTweet("Schibsted Spain",
		        "We are Schibsted Spain (look at our home page isNotLinkhttp://www.schibsted.es/), we own Vibbo, InfoJobs, fotocasa, coches.net and milanuncios. Welcome!"))
		        .andExpect(status().is(400));
	}

	/**
	 * Should return all published tweets.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldReturnAllPublishedTweets() throws Exception {
		mockMvc.perform(newTweet("Yo", "How are you?")).andExpect(status().is(201));

		final MvcResult getResult = mockMvc.perform(get("/tweet")).andExpect(status().is(200)).andReturn();

		final String content = getResult.getResponse().getContentAsString();
		assertThat(new ObjectMapper().readValue(content, List.class).size()).isEqualTo(1);
	}

	/**
	 * New tweet.
	 *
	 * @param publisher the publisher
	 * @param tweet the tweet
	 * @return the mock http servlet request builder
	 */
	private MockHttpServletRequestBuilder newTweet(final String publisher, final String tweet) {
		return post("/tweet").contentType(MediaType.APPLICATION_JSON_UTF8)
		        .content(format("{\"publisher\": \"%s\", \"tweet\": \"%s\"}", publisher, tweet));
	}

}
