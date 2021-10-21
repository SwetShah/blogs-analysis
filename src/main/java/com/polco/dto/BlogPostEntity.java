package com.polco.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BlogPostEntity {

	private long id;
	private String author;
	private long authorId;
	private long likes;
	private double popularity;
	private long reads;
	List<String> tags;

	public BlogPostEntity() {

	}

	public BlogPostEntity(long id, String author, long authorId, long likes, double popularity, long reads,
			List<String> tags) {
		super();
		this.id = id;
		this.author = author;
		this.authorId = authorId;
		this.likes = likes;
		this.popularity = popularity;
		this.reads = reads;
		this.tags = tags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public long getReads() {
		return reads;
	}

	public void setReads(long reads) {
		this.reads = reads;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + (int) (authorId ^ (authorId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (likes ^ (likes >>> 32));
		long temp;
		temp = Double.doubleToLongBits(popularity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (reads ^ (reads >>> 32));
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPostEntity other = (BlogPostEntity) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (authorId != other.authorId)
			return false;
		if (id != other.id)
			return false;
		if (likes != other.likes)
			return false;
		if (Double.doubleToLongBits(popularity) != Double.doubleToLongBits(other.popularity))
			return false;
		if (reads != other.reads)
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

}
