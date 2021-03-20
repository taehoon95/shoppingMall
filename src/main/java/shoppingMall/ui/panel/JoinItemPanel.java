package shoppingMall.ui.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.ButtonGroup;

public class JoinItemPanel extends JPanel {
	private JTextField tfCusno;
	private JTextField tfCusname;
	private JPasswordField pfPass1;
	private JTextField tfCall;
	private JPasswordField pfPass2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dcBirth;
	private JRadioButton rdbtnmale;
	private JRadioButton rdbtnFemale;

	public JoinItemPanel() {

		initialize();
	}
	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(new EmptyBorder(20, 0, 20, 0), "\uD68C\uC6D0 \uAC00\uC785", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 0, 20));
		
		JLabel lblCusno = new JLabel("\uC544\uC774\uB514(\uD68C\uC6D0 \uBC88\uD638)");
		lblCusno.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCusno);
		
		tfCusno = new JTextField();
		add(tfCusno);
		tfCusno.setColumns(10);
		
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
		add(pfPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPass2);
		
		pfPass2 = new JPasswordField();
		add(pfPass2);
		
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
		
		JLabel lblCall = new JLabel("휴대전화(010-0000-0000)");
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
		tfCusno.setText("");
		tfCusname.setText("");
		pfPass1.setText("");
		pfPass2.setText("");
		rdbtnFemale.setSelected(false);
		rdbtnmale.setSelected(false);
		tfCall.setText("");
		dcBirth.setDate(new Date());
	}
}
