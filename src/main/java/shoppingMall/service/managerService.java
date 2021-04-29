package shoppingMall.service;

import shoppingMall.dao.ManagerDao;
import shoppingMall.dao.Impl.ManagerDaoImpl;
import shoppingMall.dto.Manager;

public class managerService {
	private ManagerDao dao = ManagerDaoImpl.getInstance();
	
	public Manager loginManager(Manager manager) {
		return dao.LoginManager(manager);
	}
}
