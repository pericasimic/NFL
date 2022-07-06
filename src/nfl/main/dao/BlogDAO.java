package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Blog;

public interface BlogDAO {

	public List<Blog> getBlogs();
	
	public List<Blog> getBlogsWithComments();
	
	public List<Blog> getLastTwelveBlogs();
	
	public List<Blog> getThreeImportantBlogs();

	public void saveBlog(Blog blog);

	public Blog getBlog(int id);
	
	public List<Blog> getBlogsPerTitle(String title);

	public Blog getBlogWithTeam(int id);
	
	public Blog getBlogWithImages(int id);
	
	public Blog getBlogWithInitialize(int id);

	public void deleteBlog(int id);
	
	public void enableBlog(int id);
	
	public void importantBlog(int id);
	
	public List<Blog> getLastThreeBlogs();
	
	public List<Blog> getThreeBlogsViews();
	
	public long getBlogsSize();
	
	public List<Blog> getBlogsPerPage(int page);

	public List<Blog> getBlogsPerWord(String word, int page);
	
	public int getBlogsSizePerCategory(int idCategory);
	
	public int getBlogsSizePerAuthor(int idAuthor);
	
	public List<Blog> getBlogsPerCategory(int idCategory, long page);
	
	public List<Blog> getBlogsPerTeam(int idTeam, long page);
	
	public List<Blog> getBlogsPerAuthor(int idTeam, long page);
	
	public int getBlogsSizePerTerm(String term);
	
	public List<Blog> getBlogsPerStatus(boolean enabled);
	
	public List<Blog> getBlogsPerSearchTitle(String title);
	
	public List<Blog> getBlogsPerSearchAuthor(int id);
	
	public List<Blog> getBlogsPerSearchCategory(int id);
	
}
