package shoppingMall.service;

import java.util.List;

import shoppingMall.dao.CategoryDao;
import shoppingMall.dao.Impl.CategoryDaoImpl;
import shoppingMall.dto.Category;

public class categoryService {
	private CategoryDao dao = CategoryDaoImpl.getInstance();
	
	public List<Category> showCategory(){
		return dao.selectCategory();
	}
	
	public void addCategory(Category category) {
		dao.insertCategory(category);
	}
	
	public void removeCategory(String code) {
		dao.deleteCategory(code);
	}
	
	public void modiCategory(Category category) {
		dao.updateCategory(category);
	}
}
