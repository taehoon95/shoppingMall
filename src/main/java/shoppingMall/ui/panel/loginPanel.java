package shoppingMall.ui.panel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
	
	public loginPanel() {

		initialize();
	}
	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(new EmptyBorder(70, 100, 70, 100), "\uD68C\uC6D0 \uB85C\uADF8\uC778", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCusno = new JLabel("\uD68C\uC6D0\uBC88\uD638");
		add(lblCusno);
		
		tfCusno = new JTextField();
		add(tfCusno);
		tfCusno.setColumns(10);
		
		JLabel lblPass = new JLabel("\uBE44\uBC00\uBC88\uD638");
		add(lblPass);
		
		tfPass = new JTextField();
		tfPass.setColumns(10);
		add(tfPass);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		
		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setForeground(Color.BLACK);
		panel.add(btnLogin);
		
		JButton btnNewCus = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewCus.setBackground(Color.GREEN);
		panel.add(btnNewCus);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			actionPerformedBtnLogin(e);
		}
	}
	protected void actionPerformedBtnLogin(ActionEvent e) {
		MainManager frame = new MainManager();
		frame.setVisible(true);
	}
}
