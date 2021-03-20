package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import shoppingMall.ui.panel.JoinItemPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JoinMembershipManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnCancel;
	private JoinItemPanel pMid;
	private JButton btnAdd;
	

	public JoinMembershipManager() {
		initialize();
	}
	private void initialize() {
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
		
		pMid = new JoinItemPanel();
		contentPane.add(pMid, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMid.tfClear();
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
	}
}
