package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Customer;

public interface CustomerDao {
	
	List<Customer> selectCustomer();
	
//////////회원 가입
	int insertCustomer(Customer customer);
	
//////////로그인
	Customer LoginCustomer(Customer customer);
	
	
	int updateCustomer(Customer customer);
	int deleteCustomer(String customer);
}
