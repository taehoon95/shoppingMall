package shoppingMall.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import shoppingMall.dao.Impl.CategoryDaoImpl;
import shoppingMall.dto.Category;

public class CategoryDaoTest {

	private CategoryDaoImpl dao = CategoryDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test() {
		System.out.println("selectTest");
		List<Category> list = dao.selectCategory();
		Assert.assertNotNull(list);
		
		for(Category c : list) {
			System.out.println(c);
		}
	}

}
