package shoppingMall;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import shoppingMall.dao.SaleDao;
import shoppingMall.dao.Impl.SaleDaoImpl;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class saleDaoTest {

	private SaleDao dao = SaleDaoImpl.getInstance(); 
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectMain() {
		System.out.println("testSelectMain");
		List<Sale> list = dao.selectMain();
		Assert.assertNotNull(list);
		for(Sale a : list) {
			System.out.println(a);
		}
	}

	@Test
	public void test02SelectProduct() {
		System.out.println("testSelectProduct");
		List<Sale> list = dao.selectProduct();
		Assert.assertNotNull(list);
		for(Sale s : list) {
			System.out.println(s.toString2());
		}
	}

	@Test
	public void test03DetailInfo() {
		System.out.println("testDetailInfo");
		List<Sale> list = dao.selectDetailInfo();
		Assert.assertNotNull(list);
		for(Sale s : list) {
			System.out.println(s.toString3());
		}
	}

	
	
	@Test
	public void test05Insert() {
		System.out.println("testInsert");
		Sale newSale = new Sale("2021.03.14", new Customer(12004), new Product("PB"), 10);
		int res = dao.insertSale(newSale);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDetailInfo());
	}
	
	

}
