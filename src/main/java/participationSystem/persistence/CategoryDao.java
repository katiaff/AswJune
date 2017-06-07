package participationSystem.persistence;

import java.util.List;

import participationSystem.model.Category;

public interface CategoryDao{
	
	void addCategory(Category category);
	Category findCategoryById(int id);
	List<Category> allCategories();
}
