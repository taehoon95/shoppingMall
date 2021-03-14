package shoppingMall.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoppingMall.dao.SaleDao;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;


public class SaleDaoImpl implements SaleDao {

	private static final SaleDaoImpl instance = new SaleDaoImpl();
	
	private SaleDaoImpl() {};
	
	public static SaleDaoImpl getInstance() {
		return instance;
	}
	

	private Sale getSale(ResultSet rs) throws SQLException {
		int profit = 0;
		String date = rs.getString("date");
		Customer cusno = new Customer(rs.getString("cusno"));
		
		try{
			cusno.setCusname(rs.getString("cusName"));	
		}catch (SQLException e) {}
		try{
			cusno.setCallno(rs.getString("callno"));
		}catch (SQLException e) {}
		Product procode = new Product(rs.getString("procode"));
		try {
			procode.setProname(rs.getString("proName"));
			procode.setProprice(rs.getInt("proPrice"));
		}catch (SQLException e) {}
		int saleamount = rs.getInt("saleamount");
		int sales = rs.getInt("sales");
		try {
			profit = rs.getInt("profit");
		}catch (SQLException e) {}
		return new Sale(date, cusno, procode, saleamount, sales, profit);
	}
	

	@Override
	public List<Sale> selectMainByDate(Sale sale) {
		String sql = "select date,cusNo,cusName,callNo,procode,saleamount,sales from vw_all where date = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, sale.getDate());
			try(ResultSet rs = pstmt.executeQuery()){
				ArrayList<Sale> list = new ArrayList<Sale>();
				if(rs.next()) {
					do {
						list.add(getSale(rs));
					}while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Sale> selectMain() {
		String sql = "select date,cusNo,cusName,callNo,procode,saleamount,sales from vw_all";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Sale> list = new ArrayList<Sale>();
				do {
					list.add(getSale(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Sale> selectProduct() {
		String sql = "select cusNo,date,procode,proName,saleamount,proprice,sales,profit from vw_all;";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Sale> list = new ArrayList<Sale>();
				do {
					list.add(getSale(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sale> selectDetailInfo() {
		String sql = "select date,procode,proName,cusName,saleamount,proPrice,sales,profit,cusno from vw_all";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Sale> arrList = new ArrayList<Sale>();
				do {
					arrList.add(getSale(rs));
				}while(rs.next());
				return arrList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertSale(Sale sale) {
		String sql = "insert into sale (date,cusno ,procode ,saleamount)\r\n" + 
				"	 values (?,?,?,?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, sale.getDate());
			pstmt.setString(2, sale.getCusno().getCusno());
			pstmt.setString(3, sale.getProcode().getProcode());
			pstmt.setInt(4, sale.getSaleamount());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateSale(Sale sale) {
		String sql = "update sale set date = ? ,procode = ?,saleamount = ? where cusno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, sale.getDate());
			pstmt.setString(2, sale.getProcode().getProcode());
			pstmt.setInt(3, sale.getSaleamount());
			pstmt.setString(4, sale.getCusno().getCusno());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteSale(Sale sale) {
		String sql = "delete from sale where date = ? and cusno = ? and procode = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, sale.getDate());
			pstmt.setString(2, sale.getCusno().getCusno());
			pstmt.setString(3, sale.getProcode().getProcode());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
