package shoppingMall.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import shoppingMall.dao.Impl.ProductDaoImpl;
import shoppingMall.dto.Category;
import shoppingMall.dto.Product;

public class ProductDaoTest {

	private static ProductDaoImpl dao = ProductDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectLikeprocode() {
		System.out.println("testSelectLikeprocode");
		
		List<Product> p = dao.selectLikeprocode(new Category("HA"));
		Assert.assertNotNull(p);
		System.out.println("p2  " + p);
	
	}

}
