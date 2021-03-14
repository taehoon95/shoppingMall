package shoppingMall.service;

import java.util.List;

import shoppingMall.dao.SaleDao;
import shoppingMall.dao.Impl.SaleDaoImpl;
import shoppingMall.dto.Sale;

public class saleMainService {
	private SaleDao dao = SaleDaoImpl.getInstance();
	
	public List<Sale> showMain(){
		return dao.selectMain();
	}
	
	public List<Sale> selectMainByDate(Sale sale){
		return dao.selectMainByDate(sale);
	}
}
