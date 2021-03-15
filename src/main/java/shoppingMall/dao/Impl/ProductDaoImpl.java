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
	
	public ProductDaoImpl() {}
	
	public static ProductDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int insertProduct(Product product) {
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		return 0;
	}

	@Override
	public int deleteProduct(int product) {
		return 0;
	}

	@Override
	public List<Product> selectProduct() {
		String sql = "select procode, proname from product";
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
		try {
			procode = rs.getString("procode");
			proname = rs.getString("proname");
			proprice = rs.getInt("proprice");
			stock = rs.getInt("stock");	
		}catch(SQLException e) {
			
		}
		return new Product(procode, proname, proprice, stock);
	}

}
