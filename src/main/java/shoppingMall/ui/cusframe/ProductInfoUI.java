package shoppingMall.ui.cusframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Product;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.exception.sqlException;
import shoppingMall.service.productService;
import shoppingMall.ui.cuspanel.productInfoTablePanel;
import shoppingMall.ui.cuspanel.productInfoPanel;

@SuppressWarnings("serial")
public class ProductInfoUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private productInfoPanel pInfo;
	private JButton btnCancelProd;
	private productInfoTablePanel table;
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
		if (e.getSource() == btnCancelProd) {
			actionPerformedBtnDelProd(e);
		}
		try {
			if (e.getSource() == btnUpdateProd) {
				actionPerformedBtnUpdateProd(e);
			}
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, "공란존재","오류",JOptionPane.ERROR_MESSAGE);
		}catch(sqlException e1) {
			JOptionPane.showMessageDialog(null, "제품코드 중복", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void actionPerformedBtnDelProd(ActionEvent e) {
		pInfo.tfClear();
	}
	
	protected void actionPerformedBtnUpdateProd(ActionEvent e) {
		if(btnUpdateProd.getText().equals("수정")) {
			Product modiProd = pInfo.getProd();
			pService.modiProduct(modiProd);
			table.loadData();
			dispose();
		}else {
			Product prod = pInfo.getProd();
			pService.addProduct(prod);
			table.loadData();
			dispose();
		}
		
	}
	
	
	public JButton getBtnUpdateProd() {
		return btnUpdateProd;
	}
	public void setBtnUpdateProd(JButton btnUpdateProd) {
		this.btnUpdateProd = btnUpdateProd;
	}
	public productInfoPanel getpInfo() {
		return pInfo;
	}
	
	public void setTable(productInfoTablePanel table) {
		this.table = table;
	}
}
