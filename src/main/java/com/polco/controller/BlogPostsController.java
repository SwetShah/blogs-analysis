package com.polco.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polco.dto.BlogPostEntity;
import com.polco.service.BlogPostService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@AllArgsConstructor(onConstructor={@Autowired})
public class BlogPostsController {

	private BlogPostService blogPostService;

	@Autowired
	public BlogPostsController(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}

	@GetMapping("/ping")
	public ResponseEntity<Map<String, Boolean>> ping() {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("success", true);
		return ResponseEntity.ok(map);
	}

	@GetMapping("/posts")
	public ResponseEntity<Map> getPosts(@RequestParam(name = "tags") String tags,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc", required = false) String direction) {

		Map<String, String> errors = new HashMap<String, String>();
		List<String> validSortByValues = new ArrayList<String>(Arrays.asList("id", "reads", "likes", "popularity"));
		List<String> validDirectionValues = new ArrayList<String>(Arrays.asList("asc", "desc"));
		Map<String, List<BlogPostEntity>> response = new HashMap<String, List<BlogPostEntity>>();

		try {
			if (!validSortByValues.contains(sortBy)) {
				throw new Exception("sortBy parameter is invalid");
			} else if (!validDirectionValues.contains(direction)) {
				throw new Exception("direction parameter is invalid");
			}
			response = blogPostService.getBlogPosts(tags, sortBy, direction);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			errors.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(errors);
		}
	}
}
