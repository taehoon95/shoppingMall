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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblProcode = new JLabel("New label");
		panel.add(lblProcode);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("New label");
		panel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JLabel label_1 = new JLabel("New label");
		panel.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel.add(textField_2);
	}

}
