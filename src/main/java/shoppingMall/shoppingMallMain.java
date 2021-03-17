package shoppingMall;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import shoppingMall.ui.panel.loginPanel;

public class shoppingMallMain extends JFrame {

	private JPanel contentPane;

	
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
		
		loginPanel pMid = new loginPanel();
		panel.add(pMid, BorderLayout.CENTER);
		
		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.WHITE);
		panel.add(pBottom, BorderLayout.SOUTH);
	}

}
