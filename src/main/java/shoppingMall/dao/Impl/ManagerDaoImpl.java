package shoppingMall.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shoppingMall.dao.ManagerDao;
import shoppingMall.database.JdbcConn;
import shoppingMall.dto.Manager;

public class ManagerDaoImpl implements ManagerDao {
	
	private static final ManagerDaoImpl instance = new ManagerDaoImpl();

	private ManagerDaoImpl() {
	}

	public static ManagerDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Manager LoginManager(Manager manager) {
		String sql = "select managerid,passno from manager where managerid = ? and passno = password(?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, manager.getManagerId());
			pstmt.setString(2, manager.getManagerPass());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getManager(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Manager getManager(ResultSet rs) {
		
		String managerId = null;
		String managerPass = null;
		
		try {
			managerId = rs.getString("managerid");
		} catch (SQLException e1) {
		}
		
		try {
			managerPass = rs.getString("passno");
		} catch (SQLException e) {
		}
		
		return new Manager(managerId, managerPass);
	}

}
