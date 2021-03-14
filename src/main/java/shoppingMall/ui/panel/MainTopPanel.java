package shoppingMall.ui.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class MainTopPanel extends JPanel {

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
		
		JButton btnProduct = new JButton("\uC81C\uD488\uBCC4 \uC870\uD68C");
		btnProduct.setBackground(Color.GREEN);
		btnProduct.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnProduct);
		
		JButton btnDetail = new JButton("\uC0C1\uC138 \uC870\uD68C");
		btnDetail.setBackground(Color.GREEN);
		btnDetail.setFont(new Font("굴림", Font.BOLD, 15));
		pTopLeft.add(btnDetail);
		
		JPanel pTopRight = new JPanel();
		pTopRight.setBackground(Color.WHITE);
		pTop.add(pTopRight);
		
		JPanel pBottom = new JPanel();
		add(pBottom);
		pBottom.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(15, 0, 15, 10));
		pBottom.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDate = new JLabel("\uB0A0\uC9DC\uBCC4 \uC870\uD68C");
		lblDate.setFont(new Font("굴림", Font.BOLD, 15));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDate);
		
		JDateChooser cmbDate = new JDateChooser();
		cmbDate.setBackground(Color.LIGHT_GRAY);
		panel.add(cmbDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EmptyBorder(15, 0, 0, 100));
		pBottom.add(panel_1);
		
		JButton btnSearch = new JButton("\uAC80\uC0C9");
		btnSearch.setBackground(Color.GREEN);
		btnSearch.setFont(new Font("굴림", Font.BOLD, 12));
		panel_1.add(btnSearch);
	}

}
