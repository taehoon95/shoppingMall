package shoppingMall.ui.panel.product_category;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shoppingMall.dto.Category;
import shoppingMall.exception.InvaildCheckException;

@SuppressWarnings("serial")
public class productCategoryInfo extends JPanel {
	private JTextField tfCategoryName;
	private JTextField tfCategoryCode;

	public productCategoryInfo() {
		
		initialize();
		
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCategoryCode = new JLabel("카테고리코드");
		lblCategoryCode.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCategoryCode);
		
		tfCategoryCode = new JTextField();
		add(tfCategoryCode);
		
		JLabel lblCategoryName = new JLabel("카테고리이름");
		lblCategoryName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCategoryName);
		
		tfCategoryName = new JTextField();
		add(tfCategoryName);
		tfCategoryName.setColumns(10);
	}

	
	
	public JTextField getTfCategoryCode() {
		return tfCategoryCode;
	}
	public Category getCategory() {
		vaildCheck();
		String categoryCode = tfCategoryCode.getText();
		String categoryName = tfCategoryName.getText();

		return new Category(categoryCode, categoryName);
	}
	
	public void SetCategory(Category category) {
		tfCategoryCode.setText(category.getCategoryCode());
		tfCategoryName.setText(category.getCategoryName());
	}
	
	private void vaildCheck() {
		if(tfCategoryCode.getText().equals("") | tfCategoryName.getText().equals("")) {
			throw new InvaildCheckException();
		}
	}
	
	public void clearTf() {
		tfCategoryCode.setText("");
		tfCategoryName.setText("");
	}
	
	
}
