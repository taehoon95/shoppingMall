package shoppingMall.ui.panel.product;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shoppingMall.dto.Sale;
import shoppingMall.service.saleService;

public class ProductBottomPanel extends JPanel {
	private JTextField tfTotalOrder;
	private JTextField tfTotalProfit;
	private saleService service;
	
	private DecimalFormat df = new DecimalFormat("0,000");
	
	public ProductBottomPanel() {
		service = new saleService();
		initialize();
		setDataTotalOrder();
		setDataTotalProfit();
	}

	public JTextField getTfTotalProfit() {
		return tfTotalProfit;
	}

	public void setTfTotalProfit(JTextField tfTotalProfit) {
		this.tfTotalProfit = tfTotalProfit;
	}

	public JTextField getTfTotalOrder() {
		return tfTotalOrder;
	}

	public void setTfTotalOrder(JTextField tfTotalOrder) {
		this.tfTotalOrder = tfTotalOrder;
	}

	public void setDataTotalProfit() {
		List<Sale> saleList = service.showProduct();
		int totalProfit = saleList.parallelStream().mapToInt(Sale::getProfit).sum();
		tfTotalProfit.setText(df.format(totalProfit));
	}

	public void setDataTotalOrder() {
		List<Sale> saleList = service.showProduct();
		int totalOrder = saleList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		tfTotalOrder.setText(totalOrder + "");
	}

	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTotalOrder = new JLabel("총 주문수량");
		lblTotalOrder.setOpaque(true);
		lblTotalOrder.setBackground(Color.WHITE);
		lblTotalOrder.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalOrder);

		tfTotalOrder = new JTextField();
		tfTotalOrder.setFont(new Font("굴림", Font.BOLD, 15));
		tfTotalOrder.setHorizontalAlignment(SwingConstants.TRAILING);
		add(tfTotalOrder);
		tfTotalOrder.setColumns(10);

		JLabel lblTotalProfit = new JLabel("총 이익금액");
		lblTotalProfit.setOpaque(true);
		lblTotalProfit.setBackground(Color.WHITE);
		lblTotalProfit.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalProfit);

		tfTotalProfit = new JTextField();
		tfTotalProfit.setFont(new Font("굴림", Font.BOLD, 15));
		tfTotalProfit.setHorizontalAlignment(SwingConstants.TRAILING);
		tfTotalProfit.setColumns(10);
		add(tfTotalProfit);
	}

	public void tfClear() {
		tfTotalOrder.setText(null);
		tfTotalProfit.setText(null);
	}

}
