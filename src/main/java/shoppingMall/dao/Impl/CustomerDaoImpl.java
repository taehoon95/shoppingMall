package shoppingMall.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import shoppingMall.dao.CustomerDao;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int insertCustomer(Customer customer) {
		String sql = "insert into customer values(?,?,'이태훈','19950304','01045105881',1)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, customer.getCusno());
			pstmt.setString(2, customer.getPassno());
			pstmt.setString(3, customer.getCusname());
			pstmt.setString(4, customer.getBirth());
			pstmt.setString(5, customer.getCallno());
			pstmt.setInt(6, customer.getSex());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCustomer(Customer customer) {
		String sql = "update customer set cusname =?, callno = ? where cusno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, customer.getCusname());
			pstmt.setString(2, customer.getCallno());
			pstmt.setString(3, customer.getCusno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteCustomer(String customer) {
		String sql = "delete from customer where cusno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, customer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
