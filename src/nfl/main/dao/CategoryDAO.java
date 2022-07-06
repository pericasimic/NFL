package nfl.main.dao;

import java.util.List;
import nfl.main.entity.Category;

public interface CategoryDAO {

	public List<Category> getCategories();
	
	public List<Category> getCategoriesPerNumOrder();
	
	public List<Category> getFourCategoriesPerNumOrder();
	
	public void saveCategory(Category category);
	
	public Category getCategory(int id);
	
	public Category getCategoryByName(String name);
	
	public void deleteCategory(int id);
	
	public List<Category> getCategoriesWithBlogs();
}
