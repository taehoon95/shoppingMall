package shoppingMall.ui.cusframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProductDetailUI extends JFrame {

	private JPanel contentPane;

	public ProductDetailUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pPic = new JPanel();
		contentPane.add(pPic);
		
		JPanel pInfo = new JPanel();
		contentPane.add(pInfo);
		pInfo.setLayout(new GridLayout(0, 2, 0, 0));
	}

}
