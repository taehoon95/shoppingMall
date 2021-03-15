package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Product;

public interface ProductDao {
	int insertProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(int product);
	
	List<Product> selectProduct();
}
