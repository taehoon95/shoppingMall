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
import shoppingMall.ui.frame.DetailManager;
import shoppingMall.ui.frame.MainManager;

public class ProductTopPanel extends JPanel implements ActionListener {
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
		setBorder(new TitledBorder(null, "제품별 조회", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));

		pTop = new JPanel();
		add(pTop);
		pTop.setLayout(new GridLayout(1, 0, 0, 0));

		pTopLeft = new JPanel();
		pTopLeft.setBackground(Color.WHITE);
		pTopLeft.setBorder(new EmptyBorder(15, 0, 0, 0));
		pTop.add(pTopLeft);

		btnProduct = new JButton("메인화면");
		btnProduct.addActionListener(this);
		btnProduct.setBackground(Color.GREEN);
		btnProduct.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnProduct);

		btnDetail = new JButton("상세 조회");
		btnDetail.addActionListener(this);
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
		btnCancel.addActionListener(this);
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
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnDetail) {
			actionPerformedBtnDetail(e);
		}
		if (e.getSource() == btnProduct) {
			actionPerformedBtnProduct(e);
		}
	}
	protected void actionPerformedBtnProduct(ActionEvent e) {
		MainManager frame = new MainManager();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnDetail(ActionEvent e) {
		DetailManager frame = new DetailManager();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		tfClear();
	}
}
