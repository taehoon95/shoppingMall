package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.service.saleMainService;
import shoppingMall.ui.panel.MainBottomPanel;
import shoppingMall.ui.panel.MainMidPanel;
import shoppingMall.ui.panel.MainTopPanel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private saleMainService service;
	private MainTopPanel pTop;
	private MainMidPanel pMid;
	private MainBottomPanel pBottom;
	
	public MainFrame() {
		service = new saleMainService();
		initialize();
	}
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pTop = new MainTopPanel();
		contentPane.add(pTop, BorderLayout.NORTH);
		
		pMid = new MainMidPanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		pBottom = new MainBottomPanel();
		contentPane.add(pBottom, BorderLayout.SOUTH);
	}

}
