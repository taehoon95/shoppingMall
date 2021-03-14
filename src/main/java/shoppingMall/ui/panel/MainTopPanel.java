package shoppingMall.ui.panel;

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

public class MainTopPanel extends JPanel implements ActionListener {
	private JButton btnSearch;
	private JPanel pBottomRight;
	private JPanel pBottom;
	private JDateChooser cmbDate;
	private JPanel pBottomLeft;

	public MainTopPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop);
		pTop.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pTopLeft = new JPanel();
		pTopLeft.setBackground(Color.WHITE);
		pTopLeft.setBorder(new EmptyBorder(15, 0, 0, 0));
		pTop.add(pTopLeft);
		
		JButton btnProduct = new JButton("제품별 조회");
		btnProduct.setBackground(Color.GREEN);
		btnProduct.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnProduct);
		
		JButton btnDetail = new JButton("상세 조회");
		btnDetail.setBackground(Color.GREEN);
		btnDetail.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnDetail);
		
		JPanel pTopRight = new JPanel();
		pTopRight.setBackground(Color.WHITE);
		pTop.add(pTopRight);
		
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
		
		cmbDate = new JDateChooser();
		cmbDate.setBackground(Color.LIGHT_GRAY);
		pBottomLeft.add(cmbDate);
		
		pBottomRight = new JPanel();
		pBottomRight.setBackground(Color.WHITE);
		pBottomRight.setBorder(new EmptyBorder(15, 0, 0, 100));
		pBottom.add(pBottomRight);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setFont(new Font("굴림", Font.BOLD, 12));
		pBottomRight.add(btnSearch);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
