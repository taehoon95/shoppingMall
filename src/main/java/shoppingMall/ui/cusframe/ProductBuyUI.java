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

import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.cuspanel.productBuyCusInfoPanel;

@SuppressWarnings("serial")
public class ProductBuyUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnExit;
	private productBuyCusInfoPanel pTop;
	private JButton btnBuy;
	private JButton btnCancel;
	private saleService sService;
	private productService pService;
	
	public ProductBuyUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 412);
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
		if (e.getSource() == btnBuy) {
			actionPerformedBtnBuy(e);
		}
		if (e.getSource() == btnExit) {
			actionPerformedBtnNewButton_2(e);
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
		Sale sale = pTop.getBuyProd();
		Product product = pService.selectProductByProcode(sale.getProcode());
		
		pService.buyProductTransaction(sale, product);
///////// 수정된 테이블정보를 다시 프레임을 나오게 함
		dispose();		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pTop.clearTf();
	}
}
