package shoppingMall.ui.panel.detail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;

public class DtailTopPanel extends JPanel implements ActionListener, ItemListener {
	private JComboBox cmbProductSearch;
	private JButton btnProductSearch;
	private JComboBox cmbCusSearch;
	private JButton btnlblCusSearch;

	private productService pService;
	private customerService cService;
	private JButton btnCancel;
	private JButton btnAllSerach;

	public DtailTopPanel() {

		initialize();
		
	}

	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(null, "상세 조회", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pLeft = new JPanel();
		pLeft.setBackground(Color.WHITE);
		add(pLeft);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setBackground(Color.GREEN);
		pLeft.add(btnCancel);
		
		btnAllSerach = new JButton("전체 조회");
		btnAllSerach.addActionListener(this);
		btnAllSerach.setBackground(Color.GREEN);
		pLeft.add(btnAllSerach);

		JPanel pRight = new JPanel();
		pRight.setBorder(new EmptyBorder(0, 0, 0, 20));
		pRight.setBackground(Color.WHITE);
		add(pRight);
		pRight.setLayout(new GridLayout(0, 3, 5, 20));

		JLabel lblProductSearch = new JLabel("제품별 검색");
		pRight.add(lblProductSearch);

		cmbProductSearch = new JComboBox();
		cmbProductSearch.addItemListener(this);
		
		pRight.add(cmbProductSearch);

		btnProductSearch = new JButton("검색");
		btnProductSearch.setEnabled(false);
		btnProductSearch.setFont(new Font("굴림", Font.BOLD, 12));
		btnProductSearch.setBackground(Color.GREEN);
		btnProductSearch.setPreferredSize(new Dimension(40, 23));
		pRight.add(btnProductSearch);

		JLabel lblCusSearch = new JLabel("회원별 검색");
		pRight.add(lblCusSearch);

		cmbCusSearch = new JComboBox();
		cmbCusSearch.addItemListener(this);
		
		pRight.add(cmbCusSearch);

		btnlblCusSearch = new JButton("검색");
		btnlblCusSearch.setEnabled(false);
		
		btnlblCusSearch.setFont(new Font("굴림", Font.BOLD, 12));
		btnlblCusSearch.setBackground(Color.GREEN);
		pRight.add(btnlblCusSearch);
		
		
	}

	
	
	public void setCmbProductSearch(JComboBox cmbProductSearch) {
		this.cmbProductSearch = cmbProductSearch;
	}

	public void setCmbCusSearch(JComboBox cmbCusSearch) {
		this.cmbCusSearch = cmbCusSearch;
	}

	public void setProdService(productService service) {
		this.pService = service;

		service = new productService();

		List<Product> prodList = service.showProd();
		DefaultComboBoxModel prodModel = new DefaultComboBoxModel<Product>(new Vector<>(prodList));
		cmbProductSearch.setModel(prodModel);
		cmbProductSearch.setSelectedIndex(-1);
	}

	public void setCusService(customerService service) {
		this.cService = service;

		service = new customerService();

		List<Customer> cusList = service.showCustomer();
		DefaultComboBoxModel cusModel = new DefaultComboBoxModel<Customer>(new Vector<>(cusList));
		cmbCusSearch.setModel(cusModel);
		cmbCusSearch.setSelectedIndex(-1);
	}

	public void tfClear() {
		cmbCusSearch.setSelectedItem(null);
		cmbProductSearch.setSelectedItem(null);
	}

	public JButton getBtnAllSerach() {
		return btnAllSerach;
	}

	public void setBtnAllSerach(JButton btnAllSerach) {
		this.btnAllSerach = btnAllSerach;
	}
	
	public JButton getBtnProductSearch() {	
		return btnProductSearch;
	}

	public JButton getBtnlblCusSearch() {	
		return btnlblCusSearch;
	}
	
	private void vaildCheck() {
		if(cmbCusSearch.getSelectedItem() == null && cmbProductSearch.getSelectedItem() == null) {  
			throw new InvaildCheckException();
		}
	}

	public JComboBox getCmbProductSearch() {
		vaildCheck();
		return cmbProductSearch;
	}

	public JComboBox getCmbCusSearch() {
		vaildCheck();
		return cmbCusSearch;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnCancel) {
			actionPerformedBtnNewButton(e);
		}
	}

	protected void actionPerformedBtnNewButton(ActionEvent e) {
		tfClear();
		btnWarning();
	}


////////////////////////////// 검색창을 둘다 못쓰게 함	
	public void btnWarning() {
		btnProductSearch.setEnabled(false);
		btnlblCusSearch.setEnabled(false);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbCusSearch) {
			itemStateChangedCmbCusSearch(e);
		}
		if (e.getSource() == cmbProductSearch) {
			itemStateChangedCmbProductSearch(e);
		}
	}

	///////////// 콤보박스에 값이 선택 되면 검색창이 풀리도록
	protected void itemStateChangedCmbProductSearch(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			btnProductSearch.setEnabled(true);
		}
	}
	
	protected void itemStateChangedCmbCusSearch(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			btnlblCusSearch.setEnabled(true);
		}
	}
}
