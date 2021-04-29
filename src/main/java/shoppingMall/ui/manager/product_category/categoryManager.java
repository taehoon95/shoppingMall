package shoppingMall.ui.manager.product_category;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Category;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.exception.noCountException;
import shoppingMall.service.categoryService;
import shoppingMall.ui.frame.UpdateDetailManager;
import shoppingMall.ui.panel.product_category.productCategoryInfo;
import shoppingMall.ui.panel.product_category.productCategoryTable;

public class categoryManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCancel;
	private productCategoryInfo pCategoryItems;
	private JButton btnAdd;
	
	private categoryService service;
	private productCategoryTable pCategoryTable;


	public categoryManager() {
		service = new categoryService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pCategoryItems = new productCategoryInfo();
		pCategoryItems.setBackground(Color.WHITE);
		contentPane.add(pCategoryItems, BorderLayout.NORTH);
		
		JPanel pCategoryBtns = new JPanel();
		pCategoryBtns.setBackground(Color.WHITE);
		contentPane.add(pCategoryBtns, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.setBackground(Color.GREEN);
		btnAdd.addActionListener(this);
		pCategoryBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.setBackground(Color.GREEN);
		btnCancel.addActionListener(this);
		pCategoryBtns.add(btnCancel);
		
		pCategoryTable = new productCategoryTable();
		pCategoryTable.loadData();
		pCategoryTable.setBackground(Color.WHITE);
		contentPane.add(pCategoryTable, BorderLayout.CENTER);
		
		JPopupMenu popupMenu = createPopupMenu();
		pCategoryTable.setPopupMenu(popupMenu);
	}
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem delCate = new JMenuItem("삭제");
		JMenuItem upCate = new JMenuItem("수정");

		delCate.addActionListener(popupMenuListner);
		upCate.addActionListener(popupMenuListner);

		popMenu.add(delCate);
		popMenu.add(upCate);
		return popMenu;
	}
	
	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand() == "삭제") {
					Category category = pCategoryTable.getItem();
					service.removeCategory(category.getCategoryCode());
					pCategoryTable.loadData();
				} else if (e.getActionCommand() == "수정") {
					btnAdd.setText("수정");
					Category category = pCategoryTable.getItem();
					pCategoryItems.SetCategory(category);
					pCategoryItems.getTfCategoryCode().setEditable(false);
				}
			} catch (NotSelectedExeption e1) {
				JOptionPane.showMessageDialog(null, "목록을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnNewButton(e);
			}
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, "공란 존재", "오류", JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getSource() == btnCancel) {
			actionPerformedBtnNewButton_1(e);
		}
	}
	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		pCategoryItems.getTfCategoryCode().setEditable(true);
		pCategoryItems.clearTf();
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		if(btnAdd.getText().equals("추가")) {
			Category category = pCategoryItems.getCategory();
			service.addCategory(category);
			pCategoryTable.loadData();
		}else if(btnAdd.getText().equals("수정")) {
			pCategoryItems.getTfCategoryCode().setEditable(true);
			service.modiCategory(pCategoryItems.getCategory());
			pCategoryTable.loadData();
		}
	}
}
