package com.polco.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.polco.dto.BlogPostEntity;

@Repository
public class BlogPostDao {

	private RestTemplate restTemplate;
	private final String URL = "https://api.hatchways.io/assessment/blog/posts";

	@Autowired
	public BlogPostDao(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Map<String, List<BlogPostEntity>> getBlogPost(String tag) throws Exception {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL).queryParam("tag", tag);
			return restTemplate.getForObject(builder.toUriString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
