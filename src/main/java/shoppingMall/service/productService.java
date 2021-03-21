package shoppingMall.service;

import java.util.List;

import shoppingMall.dao.ProductDao;
import shoppingMall.dao.SaleDao;
import shoppingMall.dao.Impl.ProductDaoImpl;
import shoppingMall.dao.Impl.SaleDaoImpl;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;

public class productService {
	private ProductDao dao = ProductDaoImpl.getInstance();
	private SaleDao sDao = SaleDaoImpl.getInstance();
	
	/////////////////////////////////////// 제품별 조회
	public List<Product> showProInfo(){
		return dao.selectProduct();
	}
	
	public List<Sale> selectProductByProInfo(Product prod){
		return sDao.selectProductByProInfo(prod);
	}
	
	public Product selectProductByProcode(Product prod) {
		return dao.selectProductByProCode(prod);
	}
}
