package shoppingMall.ui.panel.product.category;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class productCategoryInfo extends JPanel {
	private JTextField textField;

	public productCategoryInfo() {
		
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		JLabel 카테고리 = new JLabel("New label");
		카테고리.setHorizontalAlignment(SwingConstants.CENTER);
		add(카테고리);
		
		JComboBox comboBox = new JComboBox();
		add(comboBox);
		
		JLabel label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
	}

}
