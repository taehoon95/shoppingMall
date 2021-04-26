package shoppingMall.ui.panel.product;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import shoppingMall.dto.Product;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;

public class ProductTopPanel extends JPanel implements ActionListener {
	private JButton btnSearch;
	private JPanel pBottomRight;
	private JPanel pBottom;
	private JComboBox<Product> cmbProduct;
	private JPanel pBottomLeft;
	private productService service;
	private JButton btnAll;
	private JButton button_1;
	
	
	public ProductTopPanel() {

		initialize();
	}

	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(null, "제품별 조회", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));

		pBottom = new JPanel();
		add(pBottom);
		pBottom.setLayout(new GridLayout(0, 2, 0, 0));

		pBottomLeft = new JPanel();
		pBottomLeft.setBackground(Color.WHITE);
		pBottomLeft.setBorder(new EmptyBorder(22, 0, 22, 10));
		pBottom.add(pBottomLeft);
		pBottomLeft.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblProduct = new JLabel("검색조건");
		lblProduct.setFont(new Font("굴림", Font.BOLD, 15));
		lblProduct.setHorizontalAlignment(SwingConstants.CENTER);
		pBottomLeft.add(lblProduct);

		cmbProduct = new JComboBox<>();
		cmbProduct.setBackground(Color.WHITE);
		pBottomLeft.add(cmbProduct);

		pBottomRight = new JPanel();
		pBottomRight.setBorder(new EmptyBorder(20, 40, 20, 160));
		pBottomRight.setBackground(Color.WHITE);
		pBottom.add(pBottomRight);
		pBottomRight.setLayout(null);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(23, 10, 63, 28);
		pBottomRight.add(btnSearch);
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setFont(new Font("굴림", Font.PLAIN, 12));
		
		btnAll = new JButton("↺");
		btnAll.addActionListener(this);
		btnAll.setFont(new Font("굴림", Font.PLAIN, 12));
		btnAll.setBackground(Color.GREEN);
		btnAll.setBounds(122, 12, 63, 25);
		pBottomRight.add(btnAll);
		
		button_1 = new JButton("취소");
		button_1.addActionListener(this);
		button_1.setFont(new Font("굴림", Font.PLAIN, 12));
		button_1.setBackground(Color.GREEN);
		button_1.setBounds(206, 13, 70, 23);
		pBottomRight.add(button_1);
	}

	public JButton getBtnAll() {
		return btnAll;
	}


	public JComboBox<Product> getCmbProduct() {
		vaildCheck();
		return cmbProduct;
	}

	private void vaildCheck() {
		if(cmbProduct.getSelectedItem() == null) {
			throw new InvaildCheckException();
		}
	}

	public void setService(productService service) {
		this.service = service;
	
		List<Product> prodList = service.showProd();
		DefaultComboBoxModel prodModel = new DefaultComboBoxModel<Product>(new Vector<>(prodList));
		cmbProduct.setModel(prodModel);
		cmbProduct.setSelectedIndex(-1);
	}
	


	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}


	public void tfClear() {
		cmbProduct.setSelectedItem(null);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_1) {
			actionPerformedButton_1(e);
		}

	}

	protected void actionPerformedButton_1(ActionEvent e) {
		tfClear();
	}
}
