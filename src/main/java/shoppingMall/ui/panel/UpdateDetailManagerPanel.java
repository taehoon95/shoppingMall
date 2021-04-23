package shoppingMall.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;

@SuppressWarnings("serial")
public class UpdateDetailManagerPanel extends JPanel {
	private JComboBox cmbCus;
	private JComboBox cmbProd;
	private JTextField tftfSaleAmount;
	private JDateChooser dCOrderdate;
	
	private productService service;
	private customerService cService;
	
	private SimpleDateFormat tdf = new SimpleDateFormat("yyyy.MM.dd");
	private JLabel lblOrderNoText;
	private JLabel lblOrderNo;
	
	public UpdateDetailManagerPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lblOrderNo = new JLabel("주문번호");
		lblOrderNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderNo.setOpaque(true);
		lblOrderNo.setBackground(Color.WHITE);
		add(lblOrderNo);
		
		lblOrderNoText = new JLabel();
		lblOrderNoText.setOpaque(true);
		lblOrderNoText.setBackground(Color.WHITE);
		lblOrderNoText.setFont(new Font("굴림", Font.BOLD, 12));
		lblOrderNoText.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOrderNoText);
		
		JLabel lblCusname = new JLabel("회원");
		lblCusname.setOpaque(true);
		lblCusname.setBackground(Color.WHITE);
		lblCusname.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCusname);
		
		cmbCus = new JComboBox();
		cmbCus.setBackground(Color.WHITE);
		add(cmbCus);
		
		JLabel lblProCode = new JLabel("제품");
		lblProCode.setOpaque(true);
		lblProCode.setBackground(Color.WHITE);
		lblProCode.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProCode);
		
		cmbProd = new JComboBox();
		cmbProd.setBackground(Color.WHITE);
		add(cmbProd);
		
		JLabel lblSaleAmount = new JLabel("주문수량");
		lblSaleAmount.setOpaque(true);
		lblSaleAmount.setBackground(Color.WHITE);
		lblSaleAmount.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSaleAmount);
		
		tftfSaleAmount = new JTextField();
		tftfSaleAmount.setHorizontalAlignment(SwingConstants.CENTER);
		tftfSaleAmount.setColumns(10);
		add(tftfSaleAmount);
		
		JLabel lblOrderdate = new JLabel("\uAD6C\uB9E4\uB0A0\uC9DC");
		lblOrderdate.setOpaque(true);
		lblOrderdate.setBackground(Color.WHITE);
		lblOrderdate.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOrderdate);
		
		dCOrderdate = new JDateChooser();
		add(dCOrderdate);
	}
	
	public void setUpdateTf(Customer cus,Product prod,Sale sale) throws ParseException{
		Date date = tdf.parse(sale.getDate());
		
		lblOrderNoText.setText(sale.getOrderno()+"");
		dCOrderdate.setDate(date);
		cmbCus.setSelectedItem(cus);
		cmbProd.setSelectedItem(prod);
		tftfSaleAmount.setText(sale.getSaleamount()+"");
	}


	public void tfClear() {
		dCOrderdate.setDate(null);
		cmbCus.setSelectedIndex(-1);
		cmbProd.setSelectedIndex(-1);
		tftfSaleAmount.setText(null);
	}

	public void setService(productService service,customerService cService) {
		this.service = service;
		this.cService = cService;
		
		List<Product> prodList = service.showProd();
		DefaultComboBoxModel prodModel = new DefaultComboBoxModel<Product>(new Vector<>(prodList));
		cmbProd.setModel(prodModel);
		cmbProd.setSelectedItem(-1);
		
		List<Customer> cusList = cService.showCustomer();
		DefaultComboBoxModel cosModel = new DefaultComboBoxModel<Customer>(new Vector<>(cusList));
		cmbCus.setModel(cosModel);
		cmbCus.setSelectedItem(-1);
	}
	
	public Sale getUpdateTf() {
		vaildCheck();
		int orderno = Integer.parseInt(lblOrderNoText.getText());
		String date = tdf.format(dCOrderdate.getDate());
		int saleamount = Integer.parseInt(tftfSaleAmount.getText());
		
		String procode = cmbProd.getSelectedItem()+"";
		
		String seachCusno = cmbCus.getSelectedItem()+"";
		int cusno = Integer.parseInt(seachCusno.substring(0, 5));
		return new Sale(date, orderno,new Customer(cusno),new Product(procode.substring(0, 2)), saleamount);
	}
	private void vaildCheck() {
		if(dCOrderdate.getDate().equals(null) ||
				tftfSaleAmount.getText().equals(null) ||
				cmbProd.getSelectedItem().equals(null) ||
				cmbCus.getSelectedItem().equals(null)){
					throw new InvaildCheckException();
				}
	}
}
