package shoppingMall.ui.panel.detail;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import shoppingMall.dto.Sale;
import shoppingMall.service.saleService;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DtailBottomPanel extends JPanel {
	private JTextField tflCntOrder;
	private JTextField tfTotalOrder;
	private JTextField tfTotalSales;
	private JTextField tfTotalProfit;

	private saleService service;
	
	private DecimalFormat df = new DecimalFormat("0,000");
	
	public DtailBottomPanel() {
		service = new saleService();
		initialize();
		setDataCntOrder();
		setDataTotalOrder();
		setDataTotalSales();
		setDataTotalProfit();
	}
	public void setDataTotalProfit() {
		List<Sale> saleList = service.showDetail();
		int totalProfit = saleList.parallelStream().mapToInt(Sale::getProfit).sum();
		tfTotalProfit.setText(df.format(totalProfit));
	}
	public void setDataTotalSales() {
		List<Sale> saleList = service.showDetail();
		int totalSales = saleList.parallelStream().mapToInt(Sale::getSales).sum();
		tfTotalSales.setText(df.format(totalSales));
	}
	public void setDataTotalOrder() {
		List<Sale> saleList = service.showDetail();
		int totalOrder = saleList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		tfTotalOrder.setText(totalOrder + "");
	}
	public void setDataCntOrder() {
		List<Sale> saleList = service.showDetail();
		tflCntOrder.setText(saleList.size()+"");
	}
	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblCntOrder = new JLabel("총 주문 건수");
		lblCntOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblCntOrder.setOpaque(true);
		lblCntOrder.setBackground(Color.WHITE);
		add(lblCntOrder);
		
		tflCntOrder = new JTextField();
		tflCntOrder.setFont(new Font("굴림", Font.BOLD, 12));
		tflCntOrder.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tflCntOrder);
		tflCntOrder.setColumns(10);
		
		JLabel lblTotalOrder = new JLabel("총 주문 수량");
		lblTotalOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalOrder.setOpaque(true);
		lblTotalOrder.setBackground(Color.WHITE);
		add(lblTotalOrder);
		
		tfTotalOrder = new JTextField();
		tfTotalOrder.setFont(new Font("굴림", Font.BOLD, 12));
		tfTotalOrder.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalOrder.setColumns(10);
		add(tfTotalOrder);
		
		JLabel lblTotalSales = new JLabel("총 판매액");
		lblTotalSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalSales.setBackground(Color.WHITE);
		lblTotalSales.setOpaque(true);
		add(lblTotalSales);
		
		tfTotalSales = new JTextField();
		tfTotalSales.setFont(new Font("굴림", Font.BOLD, 12));
		tfTotalSales.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalSales.setColumns(10);
		add(tfTotalSales);
		
		JLabel lblTotalProfit = new JLabel("총 이익금액");
		lblTotalProfit.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalProfit.setBackground(Color.WHITE);
		lblTotalProfit.setOpaque(true);
		add(lblTotalProfit);
		
		tfTotalProfit = new JTextField();
		tfTotalProfit.setFont(new Font("굴림", Font.BOLD, 12));
		tfTotalProfit.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalProfit.setColumns(10);
		add(tfTotalProfit);
	}
	public JTextField getTflCntOrder() {
		return tflCntOrder;
	}
	public void setTflCntOrder(JTextField tflCntOrder) {
		this.tflCntOrder = tflCntOrder;
	}
	public JTextField getTfTotalOrder() {
		return tfTotalOrder;
	}
	public void setTfTotalOrder(JTextField tfTotalOrder) {
		this.tfTotalOrder = tfTotalOrder;
	}
	public JTextField getTfTotalSales() {
		return tfTotalSales;
	}
	public void setTfTotalSales(JTextField tfTotalSales) {
		this.tfTotalSales = tfTotalSales;
	}
	public JTextField getTfTotalProfit() {
		return tfTotalProfit;
	}
	public void setTfTotalProfit(JTextField tfTotalProfit) {
		this.tfTotalProfit = tfTotalProfit;
	}

	
}
