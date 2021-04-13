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

import shoppingMall.dto.Customer;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.ui.cuspanel.CustomerInfoTablePanel;
import shoppingMall.ui.cuspanel.updateCusItemPanel;

@SuppressWarnings("serial")
public class CustomerManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private updateCusItemPanel pItems;
	private JButton btnUpdate;
	private customerService service;
	private CustomerInfoTablePanel table;
	
	public CustomerManagerUI() {
		service = new customerService();
		initialize();
	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 404);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pItems = new updateCusItemPanel();
		contentPane.add(pItems, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		pBtns.setBackground(Color.WHITE);
		contentPane.add(pBtns, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		pBtns.add(btnUpdate);
		
		JButton btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
	}
	public updateCusItemPanel getpItems() {
		return pItems;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnUpdate) {
				actionPerformedBtnUpdate(e);
			}	
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, "공란이나 형식을 확인해주세요","오류",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		
		Customer customer = pItems.getJoinItem();
		service.modiCustomer(customer);
		table.loadData();
		dispose();
	}
	
	public void setTable(CustomerInfoTablePanel table) {
		this.table = table;
	}
}
