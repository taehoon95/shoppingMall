package shoppingMall.ui.panel.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import shoppingMall.dto.Sale;
import shoppingMall.service.saleService;

@SuppressWarnings("serial")
public class MainBottomPanel extends JPanel {
	private JLabel tfTotalOrder;
	private JLabel tfTotalSales;
	private saleService service;

	private List<Sale> saleList;
	
	private DecimalFormat df = new DecimalFormat("#,###");

	public MainBottomPanel() {
		service = new saleService();
		initialize();
	}

	public JLabel getTfTotalOrder() {
		return tfTotalOrder;
	}

	public void setTfTotalOrder(JLabel tfTotalOrder) {
		this.tfTotalOrder = tfTotalOrder;
	}

	public JLabel getTfTotalSales() {
		return tfTotalSales;
	}

	public void setTfTotalSales(JLabel tfTotalSales) {
		this.tfTotalSales = tfTotalSales;
	}

	public void setDataTotalMain(List<Sale> list) {
		int totalSales = list.parallelStream().mapToInt(Sale::getSales).sum();
		tfTotalSales.setText(df.format(totalSales));
		
		int totalOrder = list.parallelStream().mapToInt(Sale::getSaleamount).sum();
		tfTotalOrder.setText(df.format(totalOrder));
	}

	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTotalOrder = new JLabel("총 주문수량");
		lblTotalOrder.setOpaque(true);
		lblTotalOrder.setBackground(Color.WHITE);
		lblTotalOrder.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalOrder);

		tfTotalOrder = new JLabel();
		tfTotalOrder.setOpaque(true);
		tfTotalOrder.setBackground(Color.WHITE);
		tfTotalOrder.setFont(new Font("굴림", Font.BOLD, 15));
		tfTotalOrder.setHorizontalAlignment(SwingConstants.TRAILING);
		add(tfTotalOrder);

		JLabel lblTotalSales = new JLabel("총 판매액");
		lblTotalSales.setOpaque(true);
		lblTotalSales.setBackground(Color.WHITE);
		lblTotalSales.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalSales);

		tfTotalSales = new JLabel();
		tfTotalSales.setOpaque(true);
		tfTotalSales.setBackground(Color.WHITE);
		tfTotalSales.setFont(new Font("굴림", Font.BOLD, 15));
		tfTotalSales.setHorizontalAlignment(SwingConstants.TRAILING);
		add(tfTotalSales);
		saleList = service.showMain();
		setDataTotalMain(saleList);
	}

	public void tfClear() {
		tfTotalOrder.setText(null);
		tfTotalSales.setText(null);
	}

}
