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

public class ProductManager extends JFrame {

	private JPanel contentPane;
	private productService service;
	
	public ProductManager() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pMid = new procutBuyTablePanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		JPanel pTop = new JPanel();
		contentPane.add(pTop, BorderLayout.NORTH);
		
		JButton btnBuy = new JButton("구입하기");
		pTop.add(btnBuy);
		
		JPopupMenu popupMenu = createPopupMenu();
		pMid.setPopupMenu(popupMenu);
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
			ProductDetailUI frame = new ProductDetailUI();
			frame.setTitle(prod+"");
			
			
			JOptionPane.showMessageDialog(null, "감사합니다.");
		}
	};
	

	private procutBuyTablePanel pMid;}
