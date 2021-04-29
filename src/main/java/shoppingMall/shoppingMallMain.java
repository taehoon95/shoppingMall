package shoppingMall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Manager;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.service.managerService;
import shoppingMall.ui.cusframe.ProductManager;
import shoppingMall.ui.frame.JTabbedShoppingmall;
import shoppingMall.ui.frame.JoinMembershipManager;
import shoppingMall.ui.panel.loginPanel;

@SuppressWarnings("serial")
public class shoppingMallMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnNewCus;
	private JPanel pBottom;

	private customerService service;
	private managerService mService;

	private loginPanel pMid;
	
	private Customer login;
	private Manager manager;

	private JTabbedShoppingmall frame;

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
		service = new customerService();
		mService = new managerService();
		
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
		try {
			if (e.getSource() == btnLogin) {
				actionPerformedBtnAdd(e);
			}
		} catch (InvaildCheckException | NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "아이디 나 비밀번호를 입력해주세요", "로그인 오류", JOptionPane.CANCEL_OPTION);
		}
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		
		login = pMid.loginItem();
		
		manager = pMid.loginManager();
		System.out.println(manager);
		Manager loginManager = mService.loginManager(manager);
		Customer loginCus = service.loginCustomer(login);
		
		if (loginManager != null) {
			
			ProductManager frame2 = new ProductManager();
			frame2.setVisible(true);
			
			frame = new JTabbedShoppingmall();
			frame.setVisible(true);
			
			dispose();
		} else if (loginCus != null) {
			JOptionPane.showMessageDialog(null, "환영합니다.");
			ProductManager frame = new ProductManager();
			frame.setId(pMid.getTfCusno().getText());

			frame.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "아이디,비밀번호를 다시 확인해주세요", "로그인 오류", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void loginCus(Customer login) {
		this.login = login;
	}

	protected void actionPerformedBtnJoinMem(ActionEvent e) {
		JoinMembershipManager frame = new JoinMembershipManager();
		frame.setVisible(true);
	}
}
