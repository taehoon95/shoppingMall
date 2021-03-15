package shoppingMall.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ItemEvent;

public class ProductTopPanel extends JPanel {
	private JButton btnSearch;
	private JPanel pBottomRight;
	private JPanel pBottom;
	private JComboBox<Product> cmbProduct;
	private JPanel pBottomLeft;
	private JPanel pTop;
	private JButton btnAllsearch;
	private JButton btnCancel;
	private JPanel pTopRight;
	private JPanel pTopLeft;
	private JButton btnProduct;
	private JButton btnDetail;
	private productService service;
	
	
	public ProductTopPanel() {

		initialize();
	}

	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(null, "\uC81C\uD488\uBCC4 \uC870\uD68C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));

		pTop = new JPanel();
		add(pTop);
		pTop.setLayout(new GridLayout(1, 0, 0, 0));

		pTopLeft = new JPanel();
		pTopLeft.setBackground(Color.WHITE);
		pTopLeft.setBorder(new EmptyBorder(15, 0, 0, 0));
		pTop.add(pTopLeft);

		btnProduct = new JButton("제품별 조회");
		btnProduct.setBackground(Color.GREEN);
		btnProduct.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnProduct);

		btnDetail = new JButton("상세 조회");
		btnDetail.setBackground(Color.GREEN);
		btnDetail.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnDetail);

		pTopRight = new JPanel();
		pTopRight.setBackground(Color.WHITE);
		pTop.add(pTopRight);
		pTopRight.setLayout(null);
		
		btnAllsearch = new JButton("전체조회");
		btnAllsearch.setBackground(Color.GREEN);
		btnAllsearch.setFont(new Font("굴림", Font.BOLD, 12));
		btnAllsearch.setBounds(12, 30, 105, 23);
		pTopRight.add(btnAllsearch);
		
		btnCancel = new JButton("취소");
		btnCancel.setBackground(Color.GREEN);
		btnCancel.setFont(new Font("굴림", Font.BOLD, 12));
		btnCancel.setBounds(129, 30, 70, 23);
		pTopRight.add(btnCancel);

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
		pBottomRight.setLayout(new GridLayout(0, 2, 10, 0));
		
		btnSearch = new JButton("검색");
		pBottomRight.add(btnSearch);
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setFont(new Font("굴림", Font.BOLD, 12));
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

	public void setCmbProduct(JComboBox<Product> cmbProduct) {
		this.cmbProduct = cmbProduct;
	}

	public void setService(productService service) {
		this.service = service;
	
		List<Product> prodList = service.showProInfo();
		DefaultComboBoxModel prodModel = new DefaultComboBoxModel<Product>(new Vector<>(prodList));
		cmbProduct.setModel(prodModel);
		cmbProduct.setSelectedIndex(-1);
	}
	
	public JButton getBtnProduct() {
		return btnProduct;
	}

	public void setBtnProduct(JButton btnProduct) {
		this.btnProduct = btnProduct;
	}

	public JButton getBtnDetail() {
		return btnDetail;
	}

	public void setBtnDetail(JButton btnDetail) {
		this.btnDetail = btnDetail;
	}

	public JButton getBtnAllsearch() {
		return btnAllsearch;
	}

	public void setBtnAllsearch(JButton btnAllsearch) {
		this.btnAllsearch = btnAllsearch;
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
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		tfClear();
	}
	

}
