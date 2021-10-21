package com.polco.helper;

import java.util.Comparator;

import com.polco.dto.BlogPostEntity;

public class IdComparator implements Comparator<BlogPostEntity> {

	@Override
	public int compare(BlogPostEntity post1, BlogPostEntity post2) {
		if (post1.getId() > post2.getId())
			return 1;
		else if (post1.getId() < post2.getId())
			return -1;
		return 0;
	}

}
