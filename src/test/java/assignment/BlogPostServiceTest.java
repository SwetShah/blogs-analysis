package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.polco.dao.BlogPostDao;
import com.polco.dto.BlogPostEntity;
import com.polco.service.BlogPostService;

@RunWith(MockitoJUnitRunner.class)
public class BlogPostServiceTest {

	@Mock
	BlogPostDao blogPostDao;

	@InjectMocks
	BlogPostService blogPostService;

	@Test
	public void getBlogPostsTestWithTechTagAndSortByIdASC() throws Exception {
		String tags = "tech";
		String sortBy = "id";
		String direction = "asc";

		Map<String, List<BlogPostEntity>> map = new HashMap<>();
		map = prepareDataInAscendingOrderOfIdAndTechTag();
		Mockito.when(blogPostDao.getBlogPost(tags)).thenReturn(map);

		Map<String, List<BlogPostEntity>> response = blogPostService.getBlogPosts(tags, sortBy, direction);
		Assert.assertTrue(map.equals(response));
	}

	@Test
	public void getBlogPostsTestWithTechTagAndSortByIdDESC() throws Exception {
		String tags = "tech";
		String sortBy = "id";
		String direction = "desc";

		Map<String, List<BlogPostEntity>> map = new HashMap<>();
		map = prepareDataInDescendingOrderOfIdAndTechTag();
		Mockito.when(blogPostDao.getBlogPost(tags)).thenReturn(map);

		Map<String, List<BlogPostEntity>> response = blogPostService.getBlogPosts(tags, sortBy, direction);
		Assert.assertTrue(map.equals(response));
	}

	@Test
	public void getBlogPostsTestWithMultipleTags() throws Exception {
		String tags = "health,history";
		String sortBy = "id";
		String direction = "asc";

		List<Map<String, List<BlogPostEntity>>> list;
		list = prepareDataForMultipleTags();

		Mockito.when(blogPostDao.getBlogPost("health")).thenReturn(list.get(0));
		Mockito.when(blogPostDao.getBlogPost("history")).thenReturn(list.get(1));

		Map<String, List<BlogPostEntity>> response = blogPostService.getBlogPosts(tags, sortBy, direction);
		Assert.assertTrue(list.get(1).equals(response));
	}

	private Map<String, List<BlogPostEntity>> prepareDataInAscendingOrderOfIdAndTechTag() {

		Map<String, List<BlogPostEntity>> map = new HashMap<>();
		BlogPostEntity bp1 = new BlogPostEntity(1, "Rylee Paul", 9, 960, 0.13, 50361,
				new ArrayList<>(Arrays.asList("tech", "history", "health")));
		BlogPostEntity bp2 = new BlogPostEntity(2, "Zackery Turner", 12, 469, 0.68, 90406,
				new ArrayList<>(Arrays.asList("tech", "history")));
		BlogPostEntity bp3 = new BlogPostEntity(3, "Trevon Rodriguez", 5, 425, 0.88, 19645,
				new ArrayList<>(Arrays.asList("tech")));
		map.put("posts", new ArrayList<>(Arrays.asList(bp1, bp2, bp3)));

		return map;
	}

	private Map<String, List<BlogPostEntity>> prepareDataInDescendingOrderOfIdAndTechTag() {

		Map<String, List<BlogPostEntity>> map = new HashMap<>();
		BlogPostEntity bp3 = new BlogPostEntity(3, "Trevon Rodriguez", 5, 425, 0.88, 19645,
				new ArrayList<>(Arrays.asList("tech")));
		BlogPostEntity bp2 = new BlogPostEntity(2, "Zackery Turner", 12, 469, 0.68, 90406,
				new ArrayList<>(Arrays.asList("tech", "history")));
		BlogPostEntity bp1 = new BlogPostEntity(1, "Rylee Paul", 9, 960, 0.13, 50361,
				new ArrayList<>(Arrays.asList("tech", "history", "health")));
		map.put("posts", new ArrayList<>(Arrays.asList(bp3, bp2, bp1)));

		return map;
	}

	private List<Map<String, List<BlogPostEntity>>> prepareDataForMultipleTags() {
		List<Map<String, List<BlogPostEntity>>> list = new ArrayList<>();
		Map<String, List<BlogPostEntity>> map1 = new HashMap<>();
		Map<String, List<BlogPostEntity>> map2 = new HashMap<>();
		BlogPostEntity bp2 = new BlogPostEntity(2, "Zackery Turner", 12, 469, 0.68, 90406,
				new ArrayList<>(Arrays.asList("tech", "history")));
		BlogPostEntity bp1 = new BlogPostEntity(1, "Rylee Paul", 9, 960, 0.13, 50361,
				new ArrayList<>(Arrays.asList("tech", "history", "health")));
		map1.put("posts", new ArrayList<>(Arrays.asList(bp1)));
		map2.put("posts", new ArrayList<>(Arrays.asList(bp1, bp2)));
		list.add(map1);
		list.add(map2);
		return list;
	}
}
