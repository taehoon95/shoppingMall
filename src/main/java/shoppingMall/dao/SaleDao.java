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
	
	
	int insertSale(Sale sale);
	int updateSale(Sale sale);
	int deleteSale(Sale sale);
}
