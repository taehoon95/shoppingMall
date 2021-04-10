package shoppingMall.service;

import java.util.List;

import shoppingMall.dao.CustomerDao;
import shoppingMall.dao.Impl.CustomerDaoImpl;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Sale;

public class customerService {
	private CustomerDao dao = CustomerDaoImpl.getInstance();
	
	public List<Customer> showCustomer(){
		return dao.selectCustomer();
	}
	
	public void insertCustomer(Customer customer) {
		dao.insertCustomer(customer);
	}

	public Customer loginCustomer(Customer customer) {
		return dao.LoginCustomer(customer);
	}

	public Customer showCustomerByNo(Customer customer) {
		return dao.selectCusByCo(customer);
	}
}
