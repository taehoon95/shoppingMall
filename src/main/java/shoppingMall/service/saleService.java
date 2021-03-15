package shoppingMall.service;

import java.util.List;

import shoppingMall.dao.SaleDao;
import shoppingMall.dao.Impl.SaleDaoImpl;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;

public class saleService {
	private SaleDao dao = SaleDaoImpl.getInstance();
	
	/////////////////////////////////////// 메인
	
	public List<Sale> showMain(){
		return dao.selectMain();
	}
	
	public List<Sale> selectMainByDate(Sale sale){
		return dao.selectMainByDate(sale);
	}
	
	/////////////////////////////////////// 제품별 조회
	
	public List<Sale> showProduct(){
		return dao.selectProduct();
	}
	
	public List<Sale> selectProductByProInfo(Product prod){
		return dao.selectProductByProInfo(prod);
	}
	
}
