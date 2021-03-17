package shoppingMall.ui.panel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import shoppingMall.ui.frame.JoinMembershipManager;
import shoppingMall.ui.frame.MainManager;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginPanel extends JPanel implements ActionListener {
	private JTextField tfCusno;
	private JTextField tfPass;
	private JButton btnLogin;
	private JButton btnNewCus;
	
	public loginPanel() {

		initialize();
	}
	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(new EmptyBorder(70, 100, 70, 100), "회원 로그인", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCusno = new JLabel("회원번호");
		add(lblCusno);
		
		tfCusno = new JTextField();
		add(tfCusno);
		tfCusno.setColumns(10);
		
		JLabel lblPass = new JLabel("비밀번호");
		add(lblPass);
		
		tfPass = new JTextField();
		tfPass.setColumns(10);
		add(tfPass);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setForeground(Color.BLACK);
		panel.add(btnLogin);
		
		btnNewCus = new JButton("회원가입");
		btnNewCus.addActionListener(this);
		btnNewCus.setBackground(Color.GREEN);
		panel.add(btnNewCus);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewCus) {
			actionPerformedBtnNewCus(e);
		}
		if (e.getSource() == btnLogin) {
			actionPerformedBtnLogin(e);
		}
	}
	protected void actionPerformedBtnLogin(ActionEvent e) {
		MainManager frame = new MainManager();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnNewCus(ActionEvent e) {
		JoinMembershipManager frame = new JoinMembershipManager();
		frame.setVisible(true);
	}
}
