package shoppingMall.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Manager;
import shoppingMall.exception.InvaildCheckException;

public class loginPanel extends JPanel implements ActionListener {
	private JTextField tfCusno;
	private JPasswordField tfPass;
	private String loginId;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
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
		validCheck();
		int cusno = Integer.parseInt(tfCusno.getText());
		String passno = String.valueOf(tfPass.getPassword());
		return new Customer(cusno, passno);
	}
	
	public Manager loginManager() {
		String managerId = tfCusno.getText();
		String managerPass = String.valueOf(tfPass.getPassword());
		return new Manager(managerId, managerPass);
	}
	
	private void validCheck() {
		if(tfCusno.getText().equals("") || tfPass.getPassword().equals("")) {
			throw new InvaildCheckException();
		}
	}
	public JTextField getTfCusno() {
		return tfCusno;
	}
	
	
}
