package shoppingMall.ui.cusframe;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Product;
import shoppingMall.service.productService;
import shoppingMall.ui.cuspanel.procutBuyTablePanel;
import shoppingMall.ui.cuspanel.productInfoPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ProductInfoUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private productInfoPanel pInfo;
	private JButton btnCancelProd;
	private procutBuyTablePanel table;
	private JButton btnUpdateProd;
	private productService pService;
	
	public ProductInfoUI() {
		pService = new productService();
		initialize();
	}
	private void initialize() {
		setTitle("제품상세정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 346);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pInfo = new productInfoPanel();
		contentPane.add(pInfo);
		pInfo.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnUpdateProd = new JButton("수정");
		btnUpdateProd.addActionListener(this);
		btnUpdateProd.setBackground(Color.GREEN);
		panel.add(btnUpdateProd);
		
		btnCancelProd = new JButton("취소");
		btnCancelProd.addActionListener(this);
		btnCancelProd.setBackground(Color.GREEN);
		panel.add(btnCancelProd);
	}

	
	public void setDetailItem(Product prod) {
		pInfo.setItem(prod);
	}
	
	public void prohibitionBtn() {
//		pInfo.prohibitionBtn();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdateProd) {
			actionPerformedBtnUpdateProd(e);
		}
		if (e.getSource() == btnCancelProd) {
			actionPerformedBtnDelProd(e);
		}
	}
	protected void actionPerformedBtnDelProd(ActionEvent e) {
		pInfo.tfClear();
	}
	
	protected void actionPerformedBtnUpdateProd(ActionEvent e) {
		Product modiProd = pInfo.getProd();
		System.out.println(modiProd);
		pService.modiProduct(modiProd);
		table.loadData();
	}
	
	public void setTable(procutBuyTablePanel table) {
		this.table = table;
	}
	
}
