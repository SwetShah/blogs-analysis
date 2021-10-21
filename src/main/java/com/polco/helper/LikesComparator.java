package com.polco.helper;

import java.util.Comparator;

import com.polco.dto.BlogPostEntity;

public class LikesComparator implements Comparator<BlogPostEntity> {

	@Override
	public int compare(BlogPostEntity post1, BlogPostEntity post2) {
		if (post1.getLikes() > post2.getLikes())
			return 1;
		else if (post1.getLikes() < post2.getLikes())
			return -1;
		return 0;
	} 
}
