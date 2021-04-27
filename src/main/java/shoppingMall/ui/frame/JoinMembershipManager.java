package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

import shoppingMall.dto.Customer;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.ui.cuspanel.CustomerInfoTablePanel;
import shoppingMall.ui.panel.JoinItemPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JoinMembershipManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCancel;
	private JoinItemPanel pMid;
	private JButton btnAdd;
	private customerService service;
	private JButton btnClose;
	private CustomerInfoTablePanel table;

	public JoinMembershipManager() {
		initialize();
	}

	private void initialize() {
		setTitle("회원 가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 514);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.WHITE);
		contentPane.add(pBottom, BorderLayout.SOUTH);

		btnAdd = new JButton("가입하기");
		btnAdd.addActionListener(this);
		btnAdd.setBackground(Color.GREEN);
		pBottom.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setBackground(Color.GREEN);
		pBottom.add(btnCancel);

		btnClose = new JButton("나가기");
		btnClose.addActionListener(this);
		btnClose.setBackground(Color.GREEN);
		pBottom.add(btnClose);

		pMid = new JoinItemPanel();
		contentPane.add(pMid, BorderLayout.CENTER);
	}

	public JoinItemPanel getpMid() {
		return pMid;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose) {
			actionPerformedBtnClose(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
		} catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, "공백이나 비밀번호가 일치한지 확인해주세요", "회원가입 오류", JOptionPane.CANCEL_OPTION);
			pMid.tfClear();
		}

	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMid.tfClear();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Customer newCustomer = pMid.getJoinItem();
		service = new customerService();
		if (btnAdd.getText().equals("가입하기")) {
			service.insertCustomer(newCustomer);
			JOptionPane.showMessageDialog(null, "감사드립니다. 가입 완료 되었습니다.", "회원가입완료", JOptionPane.PLAIN_MESSAGE);
		} else if (btnAdd.getText().equals("추가")) {
			service.insertCustomer(newCustomer);
			table.loadData();
		} else if (btnAdd.getText().equals("수정")) {
			
			pMid.setCusItem(newCustomer);
			service.modiCustomer(newCustomer);
			table.loadData();
		}
		dispose();
	}

	protected void actionPerformedBtnClose(ActionEvent e) {
		dispose();
	}

	public void setTable(CustomerInfoTablePanel table) {
		this.table = table;
	}
}
