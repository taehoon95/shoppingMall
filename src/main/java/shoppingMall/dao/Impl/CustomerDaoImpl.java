package shoppingMall.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import shoppingMall.dao.CustomerDao;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Customer;
import shoppingMall.exception.sqlException;

public class CustomerDaoImpl implements CustomerDao {

	private static final CustomerDaoImpl instance = new CustomerDaoImpl();
	
	private CustomerDaoImpl() {}
	
	public static CustomerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int insertCustomer(Customer customer) {
		String sql = "insert into customer (cusno,passno,cusname,birth,callno,sex) values(?,password(?),?,?,?,?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, customer.getCusno());
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
		String sql = "update customer set cusname = ?, birth = ?,passno = password(?) ,callno = ?,sex =? where cusno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, customer.getCusname());
			pstmt.setString(2, customer.getBirth());
			pstmt.setString(3, customer.getPassno());
			pstmt.setString(4, customer.getCallno());
			pstmt.setInt(5, customer.getSex());
			pstmt.setInt(6, customer.getCusno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new sqlException();
		}
	}

	@Override
	public int deleteCustomer(int customer) {
		String sql = "delete from customer where cusno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, customer);
			return pstmt.executeUpdate();
		}  catch (SQLException e) {
			throw new sqlException();
		}
	}

	@Override
	public List<Customer> selectCustomer() {
		String sql = "select cusno,cusname from customer";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Customer> list = new ArrayList<>();
				do {
					list.add(getCustomer(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Customer getCustomer(ResultSet rs){
		
		int cusno = 0;
		String cusname = null;
		String birth = null;
		String callno = null;
		int sex = 0;

		try {
			cusno = rs.getInt("cusno");
		}catch(SQLException e) {}
		try {cusname = rs.getString("cusname");
			birth = rs.getString("birth");
			callno = rs.getString("callno");
			sex = rs.getInt("sex");
		}catch (SQLException e) {
		}
		
		return new Customer(cusno, cusname, birth, callno, sex);
	}

	@Override
	public Customer LoginCustomer(Customer customer) {
		String sql = "select cusno,passno from customer where cusno = ? and passno = password(?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, customer.getCusno());
			pstmt.setString(2, customer.getPassno());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getCustomer(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer selectCusByCo(Customer customer) {
		String sql = "select cusno,cusname,birth,callno,sex from customer where cusno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, customer.getCusno());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getCustomer(rs);	
				}
			}
		} catch (SQLException e) {
			throw new sqlException();
		}
		return null;
	}

	@Override
	public List<Customer> selectCustomers() {
		String sql = "select cusno, cusname, birth, callno, sex from customer";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Customer> list = new ArrayList<>();
				do {
					list.add(getCustomer(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
