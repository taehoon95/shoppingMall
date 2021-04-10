package shoppingMall.ui.panel;

import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;

@SuppressWarnings("serial")
public class UpdateDetailManagerPanel extends JPanel {
	private JTextField tfCusno;
	private JTextField tfCusname;
	private JTextField tfProCode;
	private JTextField tfProName;
	private JTextField tftfSaleAmount;
	private JDateChooser dCOrderdate;

	public UpdateDetailManagerPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblOrderdate = new JLabel("\uAD6C\uB9E4\uB0A0\uC9DC");
		lblOrderdate.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblOrderdate);
		
		dCOrderdate = new JDateChooser();
		add(dCOrderdate);
		
		JLabel lblCusno = new JLabel("회원번호");
		lblCusno.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCusno);
		
		tfCusno = new JTextField();
		tfCusno.setColumns(10);
		add(tfCusno);
		
		JLabel lblCusname = new JLabel("회원명");
		lblCusname.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCusname);
		
		tfCusname = new JTextField();
		tfCusname.setColumns(10);
		add(tfCusname);
		
		JLabel lblProCode = new JLabel("제품코드");
		lblProCode.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProCode);
		
		tfProCode = new JTextField();
		tfProCode.setColumns(10);
		add(tfProCode);
		
		JLabel lblProName = new JLabel("제품명");
		lblProName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProName);
		
		tfProName = new JTextField();
		tfProName.setColumns(10);
		add(tfProName);
		
		JLabel lblSaleAmount = new JLabel("주문수량");
		lblSaleAmount.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSaleAmount);
		
		tftfSaleAmount = new JTextField();
		tftfSaleAmount.setColumns(10);
		add(tftfSaleAmount);
	}
	
	public void setUpdateTf(Customer cus,Product prod,Sale sale) throws ParseException{
//		validCheck(); 수정 버튼에 사용해야될듯
		SimpleDateFormat tdf = new SimpleDateFormat("yyyy.MM.dd");
		Date date = tdf.parse(sale.getDate());
		
		dCOrderdate.setDate(date);
		tfCusno.setText(cus.getCusno()+"");
		tfCusname.setText(cus.getCusname());
		tfProCode.setText(prod.getProcode());
		tfProName.setText(prod.getProname());
		tftfSaleAmount.setText(sale.getSaleamount()+"");
	}

//	private void validCheck() {
//		if(dCOrderdate.getDate().equals(null) ||
//		tfCusno.getText().equals(0) ||
//		tfCusname.getText().equals(null) ||
//		tfProCode.getText().equals(null) ||
//		tfProName.getText().equals(null) ||
//		tftfSaleAmount.getText().equals(null)) {
//			
//			throw new InvaildCheckException();
//		}
//		
//	}
	public void tfClear() {
		dCOrderdate.setDate(null);
		tfCusno.setText(null);
		tfCusname.setText(null);
		tfProCode.setText(null);
		tfProName.setText(null);
		tftfSaleAmount.setText(null);
	}

}
