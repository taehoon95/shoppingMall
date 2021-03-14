package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Sale;

public interface SaleDao {
	List<Sale> selectMain();
	List<Sale> selectProduct();
	List<Sale> selectDetailInfo();
	List<Sale> selectMainByDate(Sale sale);
	
	int insertSale(Sale sale);
	int updateSale(Sale sale);
	int deleteSale(Sale sale);
}
