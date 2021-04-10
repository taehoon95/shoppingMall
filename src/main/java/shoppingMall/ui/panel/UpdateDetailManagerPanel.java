package shoppingMall.ui.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;

@SuppressWarnings("serial")
public class UpdateDetailManagerPanel extends JPanel {
	private JTextField tfCusno;
	private JTextField tfCusname;
	private JTextField tfProCode;
	private JTextField tfProName;
	private JTextField tftfSaleAmount;
	private JTextField tfProPrice;

	public UpdateDetailManagerPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
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
		
		JLabel lblProPrice = new JLabel("단가");
		lblProPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProPrice);
		
		tfProPrice = new JTextField();
		tfProPrice.setColumns(10);
		add(tfProPrice);
	}
	
	public void setUpdateTf(Customer cus,Product prod,Sale sale) {
		tfCusno.setText(cus.getCusno()+"");
		tfCusname.setText(cus.getCusname());
		tfProCode.setText(prod.getProcode());
		tfProName.setText(prod.getProname());
		tftfSaleAmount.setText(sale.getSaleamount()+"");
		tfProPrice.setText(prod.getProprice()+"");
	}


}
