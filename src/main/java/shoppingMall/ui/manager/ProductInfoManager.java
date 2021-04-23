package shoppingMall.ui.manager;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import shoppingMall.dto.Product;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.service.productService;
import shoppingMall.ui.cusframe.ProductInfoUI;
import shoppingMall.ui.cuspanel.productInfoTablePanel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
		add(pProdInfoBtn, BorderLayout.NORTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pProdInfoBtn.add(btnAdd);

	}

	private JPopupMenu createProdPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem modiItem = new JMenuItem("수정");
		JMenuItem delItem = new JMenuItem("삭제");
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
					frame.getpInfo().getTfProdCode().setEditable(false);
					frame.setDetailItem(prod);
					frame.setVisible(true);
					frame.setTable(pProdInfoTable);
				}
			} catch (NotSelectedExeption e1) {
				JOptionPane.showMessageDialog(null, "목록을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			}

		}
	};

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		ProductInfoUI frame = new ProductInfoUI();
		frame.setVisible(true);
		frame.getBtnUpdateProd().setText("추가");

		frame.setTable(pProdInfoTable);
	}
}
