package shoppingMall.ui.manager;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import shoppingMall.dto.Customer;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.exception.sqlException;
import shoppingMall.service.customerService;
import shoppingMall.ui.cuspanel.CustomerInfoTablePanel;
import shoppingMall.ui.frame.JoinMembershipManager;
import java.awt.Color;

@SuppressWarnings("serial")
public class CusInfoManager extends JPanel implements ActionListener {
	private CustomerInfoTablePanel pCusTable;
	private JPanel pCusBtn;
	private JButton btnCusAdd;

	private customerService service;

	public CusInfoManager() {
		service = new customerService();
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		pCusTable = new CustomerInfoTablePanel();
		pCusTable.setBackground(Color.WHITE);
		pCusTable.loadData();
		JPopupMenu CusPopupMenu = createCusPopupMenu();
		pCusTable.setPopupMenu(CusPopupMenu);
		add(pCusTable, BorderLayout.CENTER);

		pCusBtn = new JPanel();
		pCusBtn.setBackground(Color.WHITE);
		add(pCusBtn, BorderLayout.NORTH);

		btnCusAdd = new JButton("추가");
		btnCusAdd.setBackground(Color.GREEN);
		btnCusAdd.addActionListener(this);
		pCusBtn.add(btnCusAdd);
	}

	private JPopupMenu createCusPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem delSale = new JMenuItem("삭제");
		JMenuItem upSale = new JMenuItem("수정");

		delSale.addActionListener(popupCusMenuListner);
		upSale.addActionListener(popupCusMenuListner);
		
		popMenu.add(upSale);
		popMenu.add(delSale);
		
		return popMenu;
	}

	ActionListener popupCusMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand() == "삭제") {
					Customer customer = pCusTable.getItem();
					service.delCustomer(customer.getCusno());
					pCusTable.loadData();
				} else if (e.getActionCommand() == "수정") {
					Customer customer = pCusTable.getItem();

					JoinMembershipManager frame = new JoinMembershipManager();
					frame.setTitle("회원 수정");
					frame.getBtnAdd().setText("수정");
					frame.getpMid().setCusItem(customer);
					frame.setVisible(true);
					frame.setTable(pCusTable);
				}
			}catch (NotSelectedExeption e1) {
				JOptionPane.showMessageDialog(null, "목록을 선택하세요.","오류",JOptionPane.ERROR_MESSAGE);
			}catch (sqlException e1) {
				JOptionPane.showMessageDialog(null, "배송중입니다.","오류",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	};

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCusAdd) {
			actionPerformedBtnCusAdd(e);
		}
	}
	protected void actionPerformedBtnCusAdd(ActionEvent e) {
		JoinMembershipManager frame = new JoinMembershipManager();
		frame.setTitle("회원 추가");
		frame.getBtnAdd().setText("추가");
		frame.setVisible(true);
		frame.setTable(pCusTable);
	}
}
