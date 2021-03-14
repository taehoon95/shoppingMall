package shoppingMall.dao;

import shoppingMall.dto.Product;

public interface ProductDao {
	int insertProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(int product);
}
