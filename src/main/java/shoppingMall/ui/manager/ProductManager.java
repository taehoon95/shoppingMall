package shoppingMall.ui.manager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import shoppingMall.ui.panel.product.ProductTopPanel;
import shoppingMall.ui.panel.product.ProductMidPanel;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;
import shoppingMall.ui.panel.product.ProductBottomPanel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ProductManager extends JPanel implements ActionListener {
	private ProductTopPanel pProdBtns;
	private ProductMidPanel pProdTable;
	private ProductBottomPanel pProdTotal;

	private DecimalFormat df = new DecimalFormat("#,###");
	
	private productService productService;
	
	List<Sale> nullList = new ArrayList<>();
	public ProductManager() {
		
		productService = new productService();
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
		} catch (NullPointerException e1) {
			pProdTable.selectList(nullList);
			pProdTotal.tfClear();
		}
	}
	protected void actionPerformedPProdBtnsBtnSearch(ActionEvent e) {
		Product prodInfo = (Product) pProdBtns.getCmbProduct().getSelectedItem();
		List<Sale> productByproInfoList = productService.selectProductByProInfo(prodInfo);
		pProdTable.selectList(productByproInfoList);

		int totalProfit = productByproInfoList.parallelStream().mapToInt(Sale::getProfit).sum();
		pProdTotal.getTfTotalProfit().setText(df.format(totalProfit));

		int totalOrder = productByproInfoList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pProdTotal.getTfTotalOrder().setText(totalOrder + "");
	}
	protected void actionPerformedPProdBtnsBtnAll(ActionEvent e) {
		pProdTable.loadData();
		pProdBtns.tfClear();
		pProdTotal.setDataTotalOrder();
		pProdTotal.setDataTotalProfit();
	}
}
