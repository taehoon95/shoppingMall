package shoppingMall.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import shoppingMall.dao.CustomerDao;
import shoppingMall.dao.ProductDao;
import shoppingMall.dao.SaleDao;
import shoppingMall.dao.Impl.CustomerDaoImpl;
import shoppingMall.dao.Impl.ProductDaoImpl;
import shoppingMall.dao.Impl.SaleDaoImpl;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.ui.cusframe.ProductManager;

public class productService {
	private ProductDao dao = ProductDaoImpl.getInstance();
	private SaleDao sDao = SaleDaoImpl.getInstance();
	
	/////////////////////////////////////// 제품정보별 조회
	public List<Product> showProInfo(){
		return dao.selectProductInfo();
	}
	////////////////////////////////////// 제품코드,명만 조회
	public List<Product> showProd(){
		return dao.selectProduct();
	}
	////////////////////////////////////// 제품 수정
	public void modiProduct(Product product) {
		dao.modiProduct(product);
	}
	
	public List<Sale> selectProductByProInfo(Product prod){
		return sDao.selectProductByProInfo(prod);
	}

	///////////////////////////////////////// 제품 삭제
	public void delProd(String prod) {
		dao.deleteProduct(prod);
	}
	
	// 제품 목록
	public Product selectProductByProcode(Product prod) {
		return dao.selectProductByProCode(prod);
	}
	// 제품 추가
	public void addProduct(Product product) {
		dao.insertProduct(product);
	}
	////////////////// 구매
	
	public int buyProductTransaction(Sale sale,Product product) {
		
		String saleInsert = "insert into sale (date,cusno ,procode ,saleamount) values (?,?,?,?)";
		String prodUpdate = "update product set stock = ? where procode = ?";
		
		Connection con = null;
		PreparedStatement sPstmt = null;
		PreparedStatement pPstmt = null;		
		int res = 0;
		try {
			con = JdbcConn.getConnection();
			con.setAutoCommit(false);
			
			sPstmt = con.prepareStatement(saleInsert);
			sPstmt.setString(1, sale.getDate());
			sPstmt.setInt(2, sale.getCusno().getCusno());
			sPstmt.setString(3, sale.getProcode().getProcode());
			sPstmt.setInt(4, sale.getSaleamount());
			res = res + sPstmt.executeUpdate();
			
			pPstmt = con.prepareStatement(prodUpdate);
			pPstmt.setInt(1, product.getStock()-sale.getSaleamount());
			pPstmt.setString(2, product.getProcode());
			
			if((product.getStock()-sale.getSaleamount()) < 0) {
				JOptionPane.showMessageDialog(null, "죄송합니다. " + product.getStock() +" 만큼 남았습니다.","재고 오류",JOptionPane.CANCEL_OPTION);
				res = 0;
			}else {
				JOptionPane.showMessageDialog(null, product.getProname() + "을(를) " +sale.getSaleamount()+"개 주문하셨습니다.");
				ProductManager frame = new ProductManager();
				frame.tableLoadData();
			}
			res = res + pPstmt.executeUpdate();
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
