package shoppingMall.ui.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.panel.product.ProductBottomPanel;
import shoppingMall.ui.panel.product.ProductMidPanel;
import shoppingMall.ui.panel.product.ProductTopPanel;

@SuppressWarnings("serial")
public class ProductManager extends JPanel implements ActionListener {
	private ProductTopPanel pProdBtns;
	private ProductMidPanel pProdTable;
	private ProductBottomPanel pProdTotal;
	
	private productService productService;
	private saleService saleService;
	
	List<Sale> nullList = new ArrayList<>();
	private List<Sale> productByproInfoList;
	
	public ProductManager() {
		productService = new productService();
		saleService = new saleService();
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		pProdBtns = new ProductTopPanel();
		pProdBtns.getBtnAll().addActionListener(this);
		pProdBtns.getBtnSearch().addActionListener(this);
		pProdBtns.setService(productService);
		add(pProdBtns, BorderLayout.NORTH);
		
		pProdTable = new ProductMidPanel();
		pProdTable.loadData();
		add(pProdTable, BorderLayout.CENTER);
		
		pProdTotal = new ProductBottomPanel();
		add(pProdTotal, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == pProdBtns.getBtnAll()) {
			actionPerformedPProdBtnsBtnAll(e);
		}
		try {
			if (e.getSource() == pProdBtns.getBtnSearch()) {
				actionPerformedPProdBtnsBtnSearch(e);
			}
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.WARNING_MESSAGE);
		}
	}
	protected void actionPerformedPProdBtnsBtnSearch(ActionEvent e) {
		Product prodInfo = (Product) pProdBtns.getCmbProduct().getSelectedItem();
		productByproInfoList = productService.selectProductByProInfo(prodInfo);
		if(productByproInfoList == null) {
			productByproInfoList = nullList;
		}
		pProdTable.selectList(productByproInfoList);

		pProdTotal.setDataTotalProd(productByproInfoList);
	}
	protected void actionPerformedPProdBtnsBtnAll(ActionEvent e) {
		pProdTable.loadData();
		pProdBtns.tfClear();
		productByproInfoList = saleService.showProduct();
		pProdTotal.setDataTotalProd(productByproInfoList);
	}
}
