package shoppingMall.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoppingMall.dao.ProductDao;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Product;

public class ProductDaoImpl implements ProductDao {
	
	private static final ProductDaoImpl instance = new ProductDaoImpl();
	
	private ProductDaoImpl() {}
	
	public static ProductDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int insertProduct(Product product) {
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		String sql = "update product set stock = ? where procode = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product.getStock()+"");
			pstmt.setString(2, product.getProcode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteProduct(String product) {
		String sql = "delete from product where procode = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Product> selectProductInfo() {
		String sql = "select procode,proname,proprice,proprice*1.1 as salePrice,stock from product";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Product> list = new ArrayList<>();
				do {
					list.add(getProduct(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		String procode = null;
		String proname = null;
		int proprice = 0;
		int stock = 0;
		int salePrice = 0;
		String prodpic = null;
		
		try {
			procode = rs.getString("procode");
		}catch(SQLException e) {}
		try {
			proname = rs.getString("proname");
		}catch(SQLException e) {}
		try {
			salePrice = rs.getInt("saleprice");
		}catch(SQLException e) {}
		try {
			proprice = rs.getInt("proprice");
		}catch(SQLException e) {}
		try {
			stock = rs.getInt("stock");	
		}catch(SQLException e) {}
		try {
			prodpic = rs.getString("prodpic");
		}catch(SQLException e) {}
		return new Product(procode, proname, proprice, stock, salePrice, prodpic);
	}

	@Override
	public Product selectProductByProCode(Product product) {
		String sql = "select procode,proname,proprice,stock,prodPic from product where procode = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product.getProcode());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> selectProduct() {
		String sql = "select procode,proname from product";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				ArrayList<Product> list = new ArrayList<>();
				do {
					list.add(getProduct(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
