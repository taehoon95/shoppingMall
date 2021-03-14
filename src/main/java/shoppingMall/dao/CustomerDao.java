package shoppingMall.dao;

import shoppingMall.dto.Customer;

public interface CustomerDao {
	int insertCustomer(Customer customer);
	int updateCustomer(Customer customer);
	int deleteCustomer(String customer);
}
