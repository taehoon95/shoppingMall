package shoppingMall.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import shoppingMall.dao.ProductDao;
import shoppingMall.dao.SaleDao;
import shoppingMall.dao.Impl.ProductDaoImpl;
import shoppingMall.dao.Impl.SaleDaoImpl;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;

public class productService {
	private ProductDao dao = ProductDaoImpl.getInstance();
	private SaleDao sDao = SaleDaoImpl.getInstance();
	
	/////////////////////////////////////// 제품별 조회
	public List<Product> showProInfo(){
		return dao.selectProduct();
	}
	
	public List<Sale> selectProductByProInfo(Product prod){
		return sDao.selectProductByProInfo(prod);
	}
	
	// 제품 목록
	public Product selectProductByProcode(Product prod) {
		return dao.selectProductByProCode(prod);
	}
	
	////////////////// 구매
	
	public int buyProductTransaction(Sale sale,Product product) {
		String saleInsert = "insert into sale (date,cusno ,procode ,saleamount)\r\n" + 
				"	 values (?,?,?,?)";
		String prodUpdate = "update product set stock = ? where procode = ?";
		
		Connection con = null;
		PreparedStatement sPstmt = null;
		PreparedStatement pPstmt = null;		
		int res = 0;
		try {
			con = JdbcConn.getConnection();
			con.setAutoCommit(false);
			
			sPstmt.setString(1, sale.getDate());
			sPstmt.setString(2, sale.getCusno().getCusno());
			sPstmt.setString(3, sale.getProcode().getProcode());
			sPstmt.setInt(4, sale.getSaleamount());
			res = res + sPstmt.executeUpdate();
			System.out.println("res "+ res);
			
			pPstmt.setString(1, product.getStock()+"");
			pPstmt.setString(2, product.getProcode());
			res = res + pPstmt.executeUpdate();
			System.out.println("res "+ res);
			
			if(res == 2) {
				con.commit();								
			}else {
				throw new SQLException();
			}
		}catch(SQLException e) {			
			rollbackUtil(con);
		}finally {
			closeUtil(con, sPstmt, pPstmt);
		} 
		return res;
	}

	private void closeUtil(Connection con, PreparedStatement sPstmt, PreparedStatement pPstmt) {
		try {
			con.setAutoCommit(true);
			if(sPstmt != null) sPstmt.close();
			if(pPstmt != null) pPstmt.close();
			if(con != null) con.close();
						
		} catch (SQLException e) {
		}
	}

	private void rollbackUtil(Connection con) {
		try {	
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
