package shoppingMall.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.OptionalInt;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import shoppingMall.dto.Customer;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;

@SuppressWarnings("serial")
public class JoinItemPanel extends JPanel {
	private JLabel lblCusId;
	private JTextField tfCusname;
	private JPasswordField pfPass1;
	private JTextField tfCall;
	private JPasswordField pfPass2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dcBirth;
	private JRadioButton rdbtnmale;
	private JRadioButton rdbtnFemale;
	private JLabel lblConfirm;
	private customerService service = new customerService();
	
	public JoinItemPanel() {

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
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPass1);
		
		pfPass1 = new JPasswordField();
		pfPass1.getDocument().addDocumentListener(listener);
		add(pfPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPass2);
		
		pfPass2 = new JPasswordField();
		pfPass2.getDocument().addDocumentListener(listener);
		add(pfPass2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		
		lblConfirm = new JLabel();
		lblConfirm.setFont(new Font("굴림", Font.BOLD, 12));
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblConfirm);
		
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
		pfPass1.setText("");
		pfPass2.setText("");
		lblConfirm.setText("");
		rdbtnFemale.setSelected(false);
		rdbtnmale.setSelected(false);
		tfCall.setText("");
		dcBirth.setDate(new Date());
	}
	

	
	public Customer getJoinItem() {
		validCheck();
		int cusno = Integer.parseInt(lblCusId.getText().trim());
		String cusname = tfCusname.getText().trim();
		
		String passno = String.valueOf(pfPass1.getPassword());
		
		SimpleDateFormat birthDate = new SimpleDateFormat("yyyy.MM.dd");
		String birth = birthDate.format(dcBirth.getDate());
		
		String callno = tfCall.getText().trim();
		int sex = rdbtnFemale.isSelected()?2:1;
		return new Customer(cusno, passno,cusname, birth, callno, sex);
	}
	private void validCheck() {
		if(lblCusId.getText().equals("") || tfCusname.getText().equals("")||
				dcBirth.getDate() == null || tfCall.getText().equals("") || tfCall.getText().length() != 11 ||
				lblConfirm.getText().equals("불일치")) {
			throw new InvaildCheckException();
		}
	}
	
	DocumentListener listener = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		private void getMessage() {
			String pw1 = new String(pfPass1.getPassword());
			String pw2 = new String(pfPass2.getPassword());
			if(pw1.equals(pw2)) {
				lblConfirm.setText("일치");
				lblConfirm.setForeground(Color.GREEN);
			}else {
				lblConfirm.setForeground(Color.RED);
				lblConfirm.setText("불일치");
			}
		}
	};
}
