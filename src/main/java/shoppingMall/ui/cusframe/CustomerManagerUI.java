package shoppingMall.ui.cusframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import shoppingMall.ui.cuspanel.updateCusItemPanel;

@SuppressWarnings("serial")
public class CustomerManagerUI extends JFrame {

	private JPanel contentPane;
	private updateCusItemPanel pItems;

	public CustomerManagerUI() {
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
		
		JButton btnUpdate = new JButton("수정");
		pBtns.add(btnUpdate);
		
		JButton btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
	}
	public updateCusItemPanel getpItems() {
		return pItems;
	}

}
