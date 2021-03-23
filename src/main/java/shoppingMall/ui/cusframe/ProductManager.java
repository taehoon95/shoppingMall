package shoppingMall.ui.cusframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import shoppingMall.dto.Product;
import shoppingMall.service.productService;
import shoppingMall.ui.cuspanel.procutBuyTablePanel;
import java.awt.Color;
import shoppingMall.ui.cuspanel.productInfoDetailPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ProductManager extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private productService service;
	
	public ProductManager() {
		service = new productService();
		initialize();
	}
	private void initialize() {
		setTitle("제품 목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 608);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pMid = new procutBuyTablePanel();
		pMid.getTable().addMouseListener(this);
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.WHITE);
		contentPane.add(pBottom, BorderLayout.SOUTH);
		
		btnBuy = new JButton("구입하기");
		btnBuy.setBackground(Color.GREEN);
		btnBuy.addActionListener(this);
		pBottom.add(btnBuy);
		
		JPopupMenu popupMenu = createPopupMenu();
		pMid.setPopupMenu(popupMenu);
		
		pTop = new productInfoDetailPanel();
		pTop.setBackground(Color.WHITE);
		contentPane.add(pTop, BorderLayout.NORTH);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem buyItem = new JMenuItem("상세정보");
		buyItem.addActionListener(popupMenuListner);
		popMenu.add(buyItem);
		return popMenu;
	}

	ActionListener popupMenuListner = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Product prod = pMid.getItem();
			Product prodDetail = service.selectProductByProcode(prod);
			ProductDetailUI frame;
			if(prodDetail == null) {
				frame = new ProductDetailUI();
			}else {
				frame = new ProductDetailUI();
				frame.setDetailItem(prodDetail);
				frame.prohibitionBtn();
			}
			
			frame.setVisible(true);
		}
	};
	
	private procutBuyTablePanel pMid;	
	private JButton btnBuy;
	private productInfoDetailPanel pTop;public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btnBuy) {
		actionPerformedBtnBuy(e);
	}
	}
	protected void actionPerformedBtnBuy(ActionEvent e) {
		// 제품 선택하고 구입하기 버튼 누르면 자동으로 제품코드와 이름 설정
		Product selectProd = pMid.getItem();
		Product select = service.selectProductByProcode(selectProd);
		ProductBuyUI frame = new ProductBuyUI();
		frame.setProd(select);
		frame.setVisible(true);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == pMid.getTable()) {
			mouseClickedPMidTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedPMidTable(MouseEvent e) {
		Product prod = pMid.getItem();
		Product prodDetail = service.selectProductByProcode(prod);
		pTop.prohibitionBtn();
		pTop.setItem(prodDetail);
	}
}
