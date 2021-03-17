package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Customer;

public interface CustomerDao {
	
	List<Customer> selectCustomer();
	
	int insertCustomer(Customer customer);
	int updateCustomer(Customer customer);
	int deleteCustomer(String customer);
}
