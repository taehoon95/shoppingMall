package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import shoppingMall.ui.panel.ProductTopPanel;
import shoppingMall.ui.panel.ProductMidPanel;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.panel.ProductBottomPanel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ProductManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ProductTopPanel pTop;
	private ProductMidPanel pMid;
	private ProductBottomPanel pBottom;
	private saleService service;
	
	public ProductManager() {
		service = new saleService();
		initialize();
	}
	private void initialize() {
		setTitle("제품정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		productService service = new productService();
		
		pTop = new ProductTopPanel();
		pTop.getBtnSearch().addActionListener(this);
		pTop.setService(service);
		contentPane.add(pTop, BorderLayout.NORTH);
		
		pMid = new ProductMidPanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		pBottom = new ProductBottomPanel();
		contentPane.add(pBottom, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pTop.getBtnSearch()) {
			actionPerformedPTopBtnSearch(e);
		}
	}
	protected void actionPerformedPTopBtnSearch(ActionEvent e) {
		Product prodInfo = (Product) pTop.getCmbProduct().getSelectedItem();
		List<Sale> productByproInfoList = service.selectProductByProInfo(prodInfo);
		pMid.selectList(productByproInfoList);
	}
}
