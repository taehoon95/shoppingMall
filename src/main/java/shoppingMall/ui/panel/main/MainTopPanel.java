package shoppingMall.ui.panel.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import shoppingMall.exception.InvaildCheckException;
import javax.swing.border.TitledBorder;

public class MainTopPanel extends JPanel implements ActionListener  {
	private JButton btnSearch;
	private JPanel pBottomRight;
	private JPanel pBottom;
	private JDateChooser JDate;
	private JPanel pBottomLeft;
	private JPanel pTop;
	private JButton btnAllsearch;
	private JButton btnCancel;
	private JPanel pTopRight;
	private JPanel pTopLeft;

	public MainTopPanel() {

		initialize();
	}

	private void initialize() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(null, "\uBA54\uC778\uD654\uBA74", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));

		pTop = new JPanel();
		add(pTop);
		pTop.setLayout(new GridLayout(1, 0, 0, 0));

		pTopLeft = new JPanel();
		pTopLeft.setBackground(Color.WHITE);
		pTopLeft.setBorder(new EmptyBorder(15, 0, 0, 0));
		pTop.add(pTopLeft);

		pTopRight = new JPanel();
		pTopRight.setBackground(Color.WHITE);
		pTop.add(pTopRight);
		pTopRight.setLayout(null);
		
		btnAllsearch = new JButton("초기화");
		btnAllsearch.setBackground(Color.GREEN);
		btnAllsearch.setFont(new Font("굴림", Font.PLAIN, 12));
		btnAllsearch.setBounds(12, 30, 105, 23);
		pTopRight.add(btnAllsearch);
		
		btnCancel = new JButton("\uCDE8\uC18C");
		btnCancel.addActionListener(this);
		btnCancel.setBackground(Color.GREEN);
		btnCancel.setFont(new Font("굴림", Font.PLAIN, 12));
		btnCancel.setBounds(129, 30, 70, 23);
		pTopRight.add(btnCancel);

		pBottom = new JPanel();
		add(pBottom);
		pBottom.setLayout(new GridLayout(0, 2, 0, 0));

		pBottomLeft = new JPanel();
		pBottomLeft.setBackground(Color.WHITE);
		pBottomLeft.setBorder(new EmptyBorder(15, 0, 15, 10));
		pBottom.add(pBottomLeft);
		pBottomLeft.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblDate = new JLabel("날짜별 조회");
		lblDate.setFont(new Font("굴림", Font.BOLD, 15));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		pBottomLeft.add(lblDate);

		JDate = new JDateChooser();
		JDate.setBackground(Color.LIGHT_GRAY);
		pBottomLeft.add(JDate);

		pBottomRight = new JPanel();
		pBottomRight.setBackground(Color.WHITE);
		pBottomRight.setBorder(new EmptyBorder(11, 0, 10, 150));
		pBottom.add(pBottomRight);

		btnSearch = new JButton("검색");
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setFont(new Font("굴림", Font.PLAIN, 12));
		pBottomRight.add(btnSearch);
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

	public JDateChooser getJDate() {
		vaildCheck();
		return JDate;
	}

	private void vaildCheck() {
		if(JDate.getDate() == null) {
			throw new InvaildCheckException();
		}
	}

	public void setJDate(JDateChooser JDate) {
		this.JDate = JDate;
	}

	public void tfClear() {
		JDate.setDate(null);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		tfClear();
	}
}
