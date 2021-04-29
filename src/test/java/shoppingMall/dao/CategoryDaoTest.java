package shoppingMall.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import shoppingMall.dao.Impl.CategoryDaoImpl;
import shoppingMall.dto.Category;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDaoTest {

	private CategoryDaoImpl dao = CategoryDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test1() {
		System.out.println("selectTest");
		List<Category> list = dao.selectCategory();
		Assert.assertNotNull(list);
		
		for(Category c : list) {
			System.out.println(c);
		}
	}
	
	@Test
	public void test2() {
		System.out.println("insertTest");
		
		Category category = new Category("CM", "캠핑");
		int res = dao.insertCategory(category);
		
		Assert.assertNotEquals(1, res);
		System.out.println(category);
	}
	
	@Test
	public void test4() {
		System.out.println("deleteTest");
		
		int res = dao.deleteCategory("CM");
		Assert.assertNotEquals(1, res);
	}
	
	@Test
	public void test3() {
		System.out.println("updateTest");
		
		Category category = new Category("CM", "핑핑");
		int res = dao.updateCategory(category);
		Assert.assertNotEquals(1, res);
		System.out.println(category);
	}

}
