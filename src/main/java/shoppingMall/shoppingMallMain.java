package shoppingMall;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;

import shoppingMall.dto.Customer;
import shoppingMall.service.customerService;
import shoppingMall.ui.frame.JoinMembershipManager;
import shoppingMall.ui.frame.MainManager;
import shoppingMall.ui.panel.loginPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class shoppingMallMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnNewCus;
	private JPanel pBottom;
	private customerService service;
	private loginPanel pMid;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					shoppingMallMain frame = new shoppingMallMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public shoppingMallMain() {
		initialize();
	}
	private void initialize() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		pTop.setBackground(Color.WHITE);
		panel.add(pTop, BorderLayout.NORTH);
		
		pMid = new loginPanel();
		panel.add(pMid, BorderLayout.CENTER);
		
		pBottom = new JPanel();
		pBottom.setBackground(Color.WHITE);
		panel.add(pBottom, BorderLayout.SOUTH);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(this);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setForeground(Color.BLACK);
		pBottom.add(btnLogin);
		
		btnNewCus = new JButton("회원가입");
		btnNewCus.addActionListener(this);
		btnNewCus.setBackground(Color.GREEN);
		pBottom.add(btnNewCus);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewCus) {
			actionPerformedBtnJoinMem(e);
		}
		if (e.getSource() == btnLogin) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		service = new customerService();
		Customer login = pMid.loginItem();
		Customer loginCus = service.loginCustomer(login);
		if(loginCus != null) {
			MainManager frame = new MainManager();
			frame.setVisible(true);	
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "아이디,비밀번호를 다시 확인해주세요", "로그인 오류", JOptionPane.WARNING_MESSAGE);
		}
	}
	protected void actionPerformedBtnJoinMem(ActionEvent e) {
		JoinMembershipManager frame = new JoinMembershipManager();
		frame.setVisible(true);
	}
}
