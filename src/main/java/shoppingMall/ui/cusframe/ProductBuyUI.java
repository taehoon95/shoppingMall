package shoppingMall.ui.cusframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.shoppingMallMain;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.exception.sqlException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.cuspanel.productBuyTablePanel;
import shoppingMall.ui.cuspanel.productBuyCusInfoPanel;

@SuppressWarnings("serial")
public class ProductBuyUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnExit;
	private productBuyCusInfoPanel pTop;
	private JButton btnBuy;
	private JButton btnCancel;
	private productService pService;
	private customerService cService;
	
	private productBuyTablePanel table;
	
	public ProductBuyUI() {
		initialize();
	}
	private void initialize() {
		setTitle("구매하기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pTop = new productBuyCusInfoPanel();
		contentPane.add(pTop, BorderLayout.CENTER);
		
		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.WHITE);
		contentPane.add(pBottom, BorderLayout.SOUTH);
		
		btnBuy = new JButton("구입하기");
		btnBuy.addActionListener(this);
		btnBuy.setBackground(Color.GREEN);
		pBottom.add(btnBuy);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setBackground(Color.GREEN);
		pBottom.add(btnCancel);
		
		btnExit = new JButton("나가기");
		btnExit.setBackground(Color.GREEN);
		btnExit.addActionListener(this);
		pBottom.add(btnExit);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		
		if (e.getSource() == btnExit) {
			actionPerformedBtnNewButton_2(e);
		}
		try {
			if (e.getSource() == btnBuy) {
				actionPerformedBtnBuy(e);
			}
		}catch (sqlException e1) {
			pTop.clearTf();
			JOptionPane.showMessageDialog(null, "존재하지않는 회원 번호입니다.","오류",JOptionPane.ERROR_MESSAGE);
		}catch (InvaildCheckException eq) {
			JOptionPane.showMessageDialog(null, "공란 존재","오류",JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void actionPerformedBtnNewButton_2(ActionEvent e) {
		dispose();
	}
	
	// 제품 선택하고 구입하기 버튼 누르면 자동으로 제품코드와 이름 설정	
	public void setProd(Product selectProd) {
		pTop.setBuyProd(selectProd);
	}
	
	protected void actionPerformedBtnBuy(ActionEvent e) {
		pService = new productService();
		cService = new customerService();
		
		Sale sale = pTop.getBuyProd();
		Product product = pService.selectProductByProcode(sale.getProcode());
		
		int cusno = Integer.parseInt(pTop.getTfCusno().getText());
		Customer cus = cService.showCustomerByNo(new Customer(cusno));
		if(cus == null) {
			throw new sqlException();
		}
		pService.buyProductTransaction(sale, product);
		ProductManager frame = new ProductManager();
		frame.tableLoadData();
		table.loadData();
		dispose();		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pTop.clearTf();
	}
	
	public void setTable(productBuyTablePanel table) {
		this.table = table;
	}
	
	
}
