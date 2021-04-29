package shoppingMall.ui.cuspanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.productService;

@SuppressWarnings("serial")
public class productBuyCusInfoPanel extends JPanel implements ChangeListener {
	private JLabel tfCusno;

	private SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy.MM.dd");
	private JLabel lblProcode;
	private JSpinner spinsaleamount;
	private JLabel lblProname;

	private DecimalFormat df = new DecimalFormat("#,###");
	private JLabel lblPrice;

	private Product prod;

	public productBuyCusInfoPanel() {

		initialize();
	}

	private void initialize() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		lblProcode = new JLabel();
		lblProcode.setOpaque(true);
		lblProcode.setBackground(Color.WHITE);
		lblProcode.setFont(new Font("굴림", Font.BOLD, 25));
		lblProcode.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblProcode);

		lblProname = new JLabel();
		lblProname.setBackground(Color.WHITE);
		lblProname.setOpaque(true);
		lblProname.setFont(new Font("굴림", Font.BOLD, 25));
		lblProname.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblProname);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel pCount = new JPanel();
		panel_1.add(pCount, BorderLayout.NORTH);
		pCount.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblsaleamount = new JLabel("수량");
		lblsaleamount.setOpaque(true);
		lblsaleamount.setBackground(Color.WHITE);
		lblsaleamount.setHorizontalAlignment(SwingConstants.CENTER);
		lblsaleamount.setFont(new Font("굴림", Font.BOLD, 15));
		pCount.add(lblsaleamount);

		spinsaleamount = new JSpinner();
		spinsaleamount.addChangeListener(this);
		spinsaleamount.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		pCount.add(spinsaleamount);

		lblPrice = new JLabel("");
		lblPrice.setFont(new Font("굴림", Font.BOLD, 14));
		lblPrice.setOpaque(true);
		lblPrice.setBackground(Color.WHITE);
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		pCount.add(lblPrice);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(new EmptyBorder(20, 0, 20, 0), "개인정보", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(0, 2, 0, 70));

		JLabel lblCusno = new JLabel("회원 번호");
		lblCusno.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblCusno);

		tfCusno = new JLabel();
		tfCusno.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(tfCusno);
	}

	public Sale getBuyProd() {
		vaildCheck();
		String date = searchDateFormat.format(new Date());
		Customer cusno = new Customer(Integer.parseInt(tfCusno.getText().trim()));
		Product procode = new Product(lblProcode.getText().trim());
		int saleamount = (int) spinsaleamount.getValue();
		return new Sale(date, cusno, procode, saleamount);
	}

	private void vaildCheck() {
		if(spinsaleamount.getValue().equals(0) || tfCusno.getText().equals("")) {
			throw new InvaildCheckException();
		}
	}


	public JLabel getTfCusno() {
		return tfCusno;
	}

	// 제품 선택하고 구입하기 버튼 누르면 자동으로 제품코드와 이름 설정
	public void setBuyProd(Product prod,String id) {
		this.prod = prod;
		tfCusno.setText(id);
		lblProcode.setText(prod.getProcode());
		lblProname.setText(prod.getProname());
	}

	public JSpinner getSpinsaleamount() {
		return spinsaleamount;
	}

	public void setSpinsaleamount(JSpinner spinsaleamount) {
		this.spinsaleamount = spinsaleamount;
	}

	public JLabel getLblPrice() {
		return lblPrice;
	}

	public void setLblPrice(JLabel lblPrice) {
		this.lblPrice = lblPrice;
	}

	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == spinsaleamount) {
			stateChangedSpinsaleamount(e);
		}
	}

	protected void stateChangedSpinsaleamount(ChangeEvent e) {
		lblPrice.setText(df.format((int) (prod.getProprice() * (int) spinsaleamount.getValue() * 1.1)) + " 원");
	}

	public void clearTf() {
		lblPrice.setText(null);
		tfCusno.setText("");
		spinsaleamount.setValue(0);
	}
}
