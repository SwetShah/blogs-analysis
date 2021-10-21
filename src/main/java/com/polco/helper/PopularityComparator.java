package com.polco.helper;

import java.util.Comparator;

import com.polco.dto.BlogPostEntity;

public class PopularityComparator implements Comparator<BlogPostEntity> {

	@Override
	public int compare(BlogPostEntity post1, BlogPostEntity post2) {
		if (post1.getPopularity() > post2.getPopularity())
			return 1;
		else if (post1.getPopularity() < post2.getPopularity())
			return -1;
		return 0;
	}
}