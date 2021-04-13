package shoppingMall.ui.cuspanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.OptionalInt;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import shoppingMall.dto.Customer;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;

@SuppressWarnings("serial")
public class updateCusItemPanel extends JPanel {
	private JLabel lblCusId;
	private JTextField tfCusname;
	private JTextField tfCall;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dcBirth;
	private JRadioButton rdbtnmale;
	private JRadioButton rdbtnFemale;
	private customerService service = new customerService();
	
	public updateCusItemPanel() {
		initialize();
	}
	
	private void cusId() {
		OptionalInt cusno = service.showCustomer().parallelStream().mapToInt(Customer::getCusno).max();
		int newCusno = cusno.getAsInt()+1;
		lblCusId.setText(newCusno+"");
	}	
	
	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(new EmptyBorder(20, 0, 20, 0), "회원가입", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 0, 20));
		
		JLabel lblCusno = new JLabel("아이디(회원번호)");
		lblCusno.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCusno);
		
		lblCusId = new JLabel();
		lblCusId.setHorizontalAlignment(SwingConstants.LEFT);
		cusId();
		add(lblCusId);
		
		JLabel lbCusname = new JLabel("회원 명");
		lbCusname.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbCusname);
		
		tfCusname = new JTextField();
		tfCusname.setColumns(10);
		add(tfCusname);
		
		JLabel lblSex = new JLabel("성별");
		lblSex.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSex);
		
		JPanel pSex = new JPanel();
		add(pSex);
		pSex.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnFemale = new JRadioButton("여");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBackground(Color.WHITE);
		pSex.add(rdbtnFemale);
		
		rdbtnmale = new JRadioButton("남");
		buttonGroup.add(rdbtnmale);
		rdbtnmale.setBackground(Color.WHITE);
		pSex.add(rdbtnmale);
		
		JLabel lblCall = new JLabel("휴대전화(숫자만입력(-없이))");
		lblCall.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCall);
		
		tfCall = new JTextField();
		add(tfCall);
		
		JLabel lblBirth = new JLabel("생년월일");
		lblBirth.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblBirth);
		
		dcBirth = new JDateChooser();
		dcBirth.setDate(new Date());
		add(dcBirth);
	}

	
	public void tfClear() {
		tfCusname.setText("");
		rdbtnFemale.setSelected(false);
		rdbtnmale.setSelected(false);
		tfCall.setText("");
		dcBirth.setDate(new Date());
	}

	public Customer getJoinItem() {
		validCheck();
		int cusno = Integer.parseInt(lblCusId.getText().trim());
		String cusname = tfCusname.getText().trim();
		
		SimpleDateFormat birthDate = new SimpleDateFormat("yyyy.MM.dd");
		String birth = birthDate.format(dcBirth.getDate());
		
		String callno = tfCall.getText().trim();
		int sex = rdbtnFemale.isSelected()?2:1;
		return new Customer(cusno, cusname, birth, callno, sex);
	}
	
	private void validCheck() {
		if(lblCusId.getText().equals("") || tfCusname.getText().equals("")||
				dcBirth.getDate() == null || tfCall.getText().equals("") || tfCall.getText().length() != 11) {
			throw new InvaildCheckException();
		}
	}

	public void setCusItem(Customer customer) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(customer.getBirth());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lblCusId.setText(customer.getCusno()+"");
		tfCusname.setText(customer.getCusname());
		tfCall.setText(customer.getCallno());
		if(customer.getSex() == 1) {
			rdbtnmale.setSelected(true);
		}else {
			rdbtnFemale.setSelected(true);
		}
		
		dcBirth.setDate(d);
		
	}
}
