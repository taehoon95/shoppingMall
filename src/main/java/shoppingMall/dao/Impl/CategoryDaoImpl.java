package shoppingMall.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoppingMall.dao.CategoryDao;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Category;

public class CategoryDaoImpl implements CategoryDao {

	private static CategoryDaoImpl instance = new CategoryDaoImpl();
	
	public static CategoryDaoImpl getInstance() {
		return instance;
	}

	public CategoryDaoImpl() {}

	public static void setInstance(CategoryDaoImpl instance) {
		CategoryDaoImpl.instance = instance;
	}
	
	private Category getCategory(ResultSet rs) {
		Category category = new Category();
		
		try {
			category.setCategoryCode(rs.getString("categorycode"));
		} catch (SQLException e) {}
		
		try {
			category.setCategoryName(rs.getString("categoryname"));
		} catch (SQLException e) {}
		
		return category;
	}


	@Override
	public List<Category> selectCategory() {
		String sql = "select categorycode, categoryname from category";
		try(Connection con = JdbcConn.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
				 List<Category> list = new ArrayList<Category>();
				 do {
					 list.add(getCategory(rs));
				 }while(rs.next());
				 return list;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	

}
