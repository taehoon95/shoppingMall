package shoppingMall.dao;

import java.util.List;

import shoppingMall.dto.Customer;

public interface CustomerDao {
	
/////////// 번호 ,이름 출력
	List<Customer> selectCustomer();
	
//////////회원 가입
	int insertCustomer(Customer customer);
	
//////////로그인
	Customer LoginCustomer(Customer customer);
	
	
	int updateCustomer(Customer customer);
	int deleteCustomer(int customer);

//////////고객코드로 검색
	Customer selectCusByCo(Customer customer);
	
//////////고객 정보 리스트 (비밀번호빼고)
	List<Customer> selectCustomers();
}
