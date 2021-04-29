package shoppingMall.ui.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import shoppingMall.dto.Product;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.exception.sqlException;
import shoppingMall.service.productService;
import shoppingMall.ui.cusframe.ProductInfoUI;
import shoppingMall.ui.cuspanel.productInfoTablePanel;
import shoppingMall.ui.manager.product_category.categoryManager;

@SuppressWarnings("serial")
public class ProductInfoManager extends JPanel implements ActionListener {
	private JButton btnAdd;
	private JPanel pProdInfoBtn;
	private productInfoTablePanel pProdInfoTable;

	private productService service;

	public ProductInfoManager() {
		service = new productService();
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		pProdInfoTable = new productInfoTablePanel();
		pProdInfoTable.loadData();
		add(pProdInfoTable, BorderLayout.CENTER);

		JPopupMenu popupProdMenu = createProdPopupMenu();
		pProdInfoTable.setPopupMenu(popupProdMenu);

		pProdInfoBtn = new JPanel();
		pProdInfoBtn.setBackground(Color.WHITE);
		add(pProdInfoBtn, BorderLayout.NORTH);

		btnAdd = new JButton("\uC81C\uD488 \uCD94\uAC00");
		btnAdd.setBackground(Color.GREEN);
		btnAdd.addActionListener(this);
		pProdInfoBtn.add(btnAdd);
		
		btnNewButton = new JButton("\uCE74\uD14C\uACE0\uB9AC \uCD94\uAC00");
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(Color.GREEN);
		pProdInfoBtn.add(btnNewButton);

	}

	private JPopupMenu createProdPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem modiItem = new JMenuItem("수정");
		JMenuItem delItem = new JMenuItem("삭제");
		JMenuItem profitChart = new JMenuItem("이익금액차트");
		JMenuItem amountChart = new JMenuItem("판매량차트");
		modiItem.addActionListener(popupProdMenuListner);
		delItem.addActionListener(popupProdMenuListner);

		popMenu.add(delItem);
		popMenu.add(modiItem);
		
		return popMenu;
	}

	ActionListener popupProdMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand() == "삭제") {
					Product product = pProdInfoTable.getItem();

					service.delProd(product.getProcode());
					pProdInfoTable.loadData();
				} else if (e.getActionCommand() == "수정") {
					Product product = pProdInfoTable.getItem();
					Product prod = service.selectProductByProcode(product);
					ProductInfoUI frame = new ProductInfoUI();
					frame.setTitle("제품 수정");
					frame.getpInfo().getTfProdCode().setEditable(false);
					frame.setDetailItem(prod);
					frame.setVisible(true);
					frame.setTable(pProdInfoTable);
				}
			} catch (NotSelectedExeption e1) {
				JOptionPane.showMessageDialog(null, "목록을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			} catch (sqlException e1) {
				JOptionPane.showMessageDialog(null, "판매중인 상품입니다", "오류", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	};
	private JButton btnNewButton;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		
		ProductInfoUI frame = new ProductInfoUI();
		frame.setTitle("제품 추가");
		frame.getpInfo().getpRight().add(frame.getpInfo().getLblCategory());
		frame.getpInfo().getpRight().add(frame.getpInfo().getCmbCategory());
		frame.setVisible(true);
		frame.getBtnUpdateProd().setText("추가");

		frame.setTable(pProdInfoTable);
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		categoryManager frame = new categoryManager();
		frame.setVisible(true);
	}
}
