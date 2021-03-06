package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;

public interface SaleDao {
	///Main화면
	List<Sale> selectMain();
	List<Sale> selectMainByDate(Sale sale);
	
	//제품별 조회 화면
	List<Sale> selectProduct();
	List<Sale> selectProductByProInfo(Product prod);
	
	//상세 조회 화면
	List<Sale> selectDetailInfo();
	List<Sale> selectDetailByCus(Customer cus);
	List<Sale> selectDetailByProdAndCus(Customer cus,Product prod);
	
	// 제품 구입용
	int insertSale(Sale sale);
	
	// detail 정보 수정
	int updateSale(Sale sale);
	
	
	int deleteSale(Sale sale);
	
	
	
}
