package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Product;

public interface ProductDao {
	int insertProduct(Product product);
	
	int deleteProduct(int product);
	
///////////////////////////// 제품검색(고객 구매 테이블)
	List<Product> selectProductInfo();
	
//////////////////////////// 제품 정보(고객 구매 테이블)
	Product selectProductByProCode(Product product);
	
//////////////////////////// 구매용
	int updateProduct(Product product);
	
//////////////////////////// 제품 검색용
	List<Product> selectProduct();
}
