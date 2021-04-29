package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Category;
import shoppingMall.dto.Product;

public interface ProductDao {
	int insertProduct(Product product);
	
	int deleteProduct(String product);
	
///////////////////////////// 제품검색(고객 구매 테이블)
	List<Product> selectProductInfo();
	
//////////////////////////// 제품 정보(고객 구매 테이블)
	Product selectProductByProCode(Product product);
	
//////////////////////////// 구매용
	int buyProduct(Product product);
	
//////////////////////////// 제품 검색용
	List<Product> selectProduct();
	
/////////////////////////// 제품 수정용
	int modiProduct(Product product);
	
/////////////////////////// 제품 추가 할때 코드 검색용
	List<Product> selectLikeprocode(Category code);
}
