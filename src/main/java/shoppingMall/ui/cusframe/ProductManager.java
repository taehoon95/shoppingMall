package shoppingMall.ui.cusframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import shoppingMall.shoppingMallMain;
import shoppingMall.dto.Product;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;
import shoppingMall.ui.cuspanel.productBuyTablePanel;
import shoppingMall.ui.cuspanel.productInfoDetailPanel;

@SuppressWarnings("serial")
public class ProductManager extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private productService service;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
		
		pMid = new productBuyTablePanel();
		pMid.getTable().addMouseListener(this);
		tableLoadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.WHITE);
		contentPane.add(pBottom, BorderLayout.SOUTH);
		
		btnBuy = new JButton("구입하기");
		btnBuy.setBackground(Color.GREEN);
		btnBuy.addActionListener(this);
		pBottom.add(btnBuy);
		
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		btnLogout.setBackground(Color.GREEN);
		pBottom.add(btnLogout);
		
		JPopupMenu popupMenu = createPopupMenu();
		pMid.setPopupMenu(popupMenu);
		
		pTop = new productInfoDetailPanel();
		pTop.setBackground(Color.WHITE);
		contentPane.add(pTop, BorderLayout.NORTH);
	}
	
	

	public void tableLoadData() {
		pMid.loadData();
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
			}
			
			frame.setVisible(true);
		}
	};
	
	private productBuyTablePanel pMid;	
	private JButton btnBuy;
	private productInfoDetailPanel pTop;
	private JButton btnLogout;
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btnLogout) {
		actionPerformedBtnLogout(e);
	}
	try {
		if (e.getSource() == btnBuy) {
			actionPerformedBtnBuy(e);
		}
	}catch(InvaildCheckException e1){
		JOptionPane.showMessageDialog(null, "공란 존재", "오류", JOptionPane.WARNING_MESSAGE);
	}
	
	}
	protected void actionPerformedBtnBuy(ActionEvent e) {
		// 제품 선택하고 구입하기 버튼 누르면 자동으로 제품코드와 이름 설정
		System.out.println(id);
		Product selectProd = pMid.getItem();
		Product select = service.selectProductByProcode(selectProd);
		ProductBuyUI frame = new ProductBuyUI();
		frame.setProd(select,id);
		frame.setVisible(true);
		frame.setTable(pMid);
		if(select.getStock() < 0) {
			JOptionPane.showMessageDialog(null, "죄송합니다. " + select.getStock() +" 만큼 남았습니다.","재고 오류",JOptionPane.CANCEL_OPTION);
		}
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
		pTop.setItem(prodDetail);
	}
	
	protected void actionPerformedBtnLogout(ActionEvent e) {
		int res = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?",
				"로그아웃",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null);
		if(res == JOptionPane.YES_OPTION) {
			dispose();
			shoppingMallMain frame = new shoppingMallMain();
			frame.setVisible(true);
		}
		
	}
}
