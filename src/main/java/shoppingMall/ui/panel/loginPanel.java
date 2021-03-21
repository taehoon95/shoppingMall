package shoppingMall.ui.panel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import shoppingMall.shoppingMallMain;
import shoppingMall.dto.Customer;
import shoppingMall.service.customerService;
import shoppingMall.ui.frame.JoinMembershipManager;
import shoppingMall.ui.frame.MainManager;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class loginPanel extends JPanel implements ActionListener {
	private JTextField tfCusno;
	private JPasswordField tfPass;
	private customerService service;
	
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
		
		tfPass = new JPasswordField();
		tfPass.setColumns(10);
		add(tfPass);
	}

	public void actionPerformed(ActionEvent e) {
	}
	
	public Customer loginItem() {
		String cusno = tfCusno.getText();
		String passno = String.valueOf(tfPass.getPassword());
		return new Customer(cusno, passno);
	}
}
