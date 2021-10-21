package com.polco.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polco.dao.BlogPostDao;
import com.polco.dto.BlogPostEntity;
import com.polco.helper.IdComparator;
import com.polco.helper.LikesComparator;
import com.polco.helper.PopularityComparator;
import com.polco.helper.ReadsComparator;

@Service
public class BlogPostService {

	private BlogPostDao blogPostDao;

	@Autowired
	public BlogPostService(BlogPostDao blogPostDao) {
		this.blogPostDao = blogPostDao;
	}

	@Cacheable(value = "blog")
	public Map<String, List<BlogPostEntity>> getBlogPosts(String tags, String sortBy, String direction)
			throws Exception {

		String[] tagsList = tags.split(",");
		Set<BlogPostEntity> blogPosts = new HashSet<BlogPostEntity>();
		Map<String, List<BlogPostEntity>> blogPostMap = new HashMap<String, List<BlogPostEntity>>();
		List<BlogPostEntity> listOfBlogPosts;
		try {
			
			for (String tag : tagsList) {
				// future is the map storing the List of BlogPostEntity
				CompletableFuture<Map<String, List<BlogPostEntity>>> future = getEachBlogPost(tag);
				
				List<BlogPostEntity> blogPostList = future.get().get("posts");
				if (!CollectionUtils.isEmpty(blogPostList)) {
					blogPosts.addAll(blogPostList);
				}
			}
			listOfBlogPosts = new ArrayList<BlogPostEntity>(blogPosts);
			listOfBlogPosts = sort(sortBy, direction, listOfBlogPosts);
			blogPostMap.put("posts", listOfBlogPosts);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return blogPostMap;
	}

	@Async
	public CompletableFuture<Map<String, List<BlogPostEntity>>> getEachBlogPost(String tag) throws Exception {
		return CompletableFuture.completedFuture(blogPostDao.getBlogPost(tag));
	}

	private List<BlogPostEntity> sort(String sortBy, String direction, List<BlogPostEntity> listOfBlogPosts)
			throws Exception {

		// Type casting it to ArrayList<BlogPostEntity> using TypeReference to avoid
		// Class cast exception while sorting
		ObjectMapper mapper = new ObjectMapper();
		listOfBlogPosts = mapper.readValue(mapper.writeValueAsString(listOfBlogPosts),
				new TypeReference<List<BlogPostEntity>>() {
				});

		switch (sortBy) {
		case "id":
			if (direction.equals("asc")) {
				listOfBlogPosts.sort(new IdComparator());
			} else {
				listOfBlogPosts.sort(Collections.reverseOrder(new IdComparator()));
			}
			break;
		case "reads":
			if (direction.equals("asc")) {
				listOfBlogPosts.sort(new ReadsComparator());
			} else {
				listOfBlogPosts.sort(Collections.reverseOrder(new ReadsComparator()));
			}
			break;
		case "likes":
			if (direction.equals("asc")) {
				listOfBlogPosts.sort(new LikesComparator());
			} else {
				listOfBlogPosts.sort(Collections.reverseOrder(new LikesComparator()));
			}
			break;
		case "popularity":
			if (direction.equals("asc")) {
				listOfBlogPosts.sort(new PopularityComparator());
			} else {
				listOfBlogPosts.sort(Collections.reverseOrder(new PopularityComparator()));
			}
			break;
		}

		return listOfBlogPosts;
	}
}
