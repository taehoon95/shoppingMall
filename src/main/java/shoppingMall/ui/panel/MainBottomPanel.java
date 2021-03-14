package shoppingMall.ui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainBottomPanel extends JPanel {
	private JTextField tfTotalOrder;
	private JTextField tfTotalSale;

	/**
	 * Create the panel.
	 */
	public MainBottomPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblTotalOrder = new JLabel("\uCD1D \uC8FC\uBB38\uC218\uB7C9");
		lblTotalOrder.setOpaque(true);
		lblTotalOrder.setBackground(Color.WHITE);
		lblTotalOrder.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalOrder);
		
		tfTotalOrder = new JTextField();
		add(tfTotalOrder);
		tfTotalOrder.setColumns(10);
		
		JLabel lblTotalSale = new JLabel("\uCD1D \uD310\uB9E4\uC561");
		lblTotalSale.setOpaque(true);
		lblTotalSale.setBackground(Color.WHITE);
		lblTotalSale.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalSale);
		
		tfTotalSale = new JTextField();
		tfTotalSale.setColumns(10);
		add(tfTotalSale);
	}

}
