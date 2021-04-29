package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Category;

public interface CategoryDao {
	
	List<Category> selectCategory();
	
	int insertCategory(Category category);
	int deleteCategory(String code);
	int updateCategory(Category category);
}
