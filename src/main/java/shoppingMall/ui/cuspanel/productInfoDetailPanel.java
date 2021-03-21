package shoppingMall.ui.cuspanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridLayout;

public class productInfoDetailPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	public productInfoDetailPanel() {

		initialize();
	}
	private void initialize() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("New label");
		add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		add(textField_1);
		
		JLabel label_1 = new JLabel("New label");
		add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		add(textField_2);
	}

}
