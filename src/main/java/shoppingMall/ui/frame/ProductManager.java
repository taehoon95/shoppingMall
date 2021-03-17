package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.ui.panel.product.ProductBottomPanel;
import shoppingMall.ui.panel.product.ProductMidPanel;
import shoppingMall.ui.panel.product.ProductTopPanel;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class ProductManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ProductTopPanel pTop;
	private ProductMidPanel pMid;
	private ProductBottomPanel pBottom;
	private saleService service;
	
	private List<Sale> list;
	private DecimalFormat df = new DecimalFormat("0,000");
	
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
		pTop.getBtnAllsearch().addActionListener(this);
		pTop.setService(service);
		contentPane.add(pTop, BorderLayout.NORTH);
		
		pMid = new ProductMidPanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		pBottom = new ProductBottomPanel();
		contentPane.add(pBottom, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pTop.getBtnAllsearch()) {
			actionPerformedPTopBtnAllsearch(e);
		}
		try {
			if (e.getSource() == pTop.getBtnSearch()) {
				actionPerformedPTopBtnSearch(e);
			}	
		}catch(InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}
	private void actionPerformedPTopBtnAllsearch(ActionEvent e) {
		pMid.loadData();
		pTop.tfClear();
		pBottom.setDataTotalOrder();
		pBottom.setDataTotalProfit();
		
	}
	protected void actionPerformedPTopBtnSearch(ActionEvent e) {
		Product prodInfo = (Product) pTop.getCmbProduct().getSelectedItem();
		List<Sale> productByproInfoList = service.selectProductByProInfo(prodInfo);
		pMid.selectList(productByproInfoList);
		
		int totalProfit = productByproInfoList.parallelStream().mapToInt(Sale::getProfit).sum();
		pBottom.getTfTotalProfit().setText(df.format(totalProfit));
		
		int totalOrder = productByproInfoList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pBottom.getTfTotalOrder().setText(totalOrder+"");
		
	}

	
	
}
