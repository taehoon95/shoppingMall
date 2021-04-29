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
}
