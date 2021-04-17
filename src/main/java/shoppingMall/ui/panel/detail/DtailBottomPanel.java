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

@SuppressWarnings("serial")
public class DtailBottomPanel extends JPanel {
	private JLabel tflCntOrder;
	private JLabel tfTotalOrder;
	private JLabel tfTotalSales;
	private JLabel tfTotalProfit;

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
		
		tflCntOrder = new JLabel();
		tflCntOrder.setBackground(Color.WHITE);
		tflCntOrder.setOpaque(true);
		tflCntOrder.setFont(new Font("굴림", Font.BOLD, 12));
		tflCntOrder.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tflCntOrder);
		
		JLabel lblTotalOrder = new JLabel("총 주문 수량");
		lblTotalOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalOrder.setOpaque(true);
		lblTotalOrder.setBackground(Color.WHITE);
		add(lblTotalOrder);
		
		tfTotalOrder = new JLabel();
		tfTotalOrder.setBackground(Color.WHITE);
		tfTotalOrder.setOpaque(true);
		tfTotalOrder.setFont(new Font("굴림", Font.BOLD, 12));
		tfTotalOrder.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tfTotalOrder);
		
		JLabel lblTotalSales = new JLabel("총 판매액");
		lblTotalSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalSales.setBackground(Color.WHITE);
		lblTotalSales.setOpaque(true);
		add(lblTotalSales);
		
		tfTotalSales = new JLabel();
		tfTotalSales.setBackground(Color.WHITE);
		tfTotalSales.setOpaque(true);
		tfTotalSales.setFont(new Font("굴림", Font.BOLD, 12));
		tfTotalSales.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tfTotalSales);
		
		JLabel lblTotalProfit = new JLabel("총 이익금액");
		lblTotalProfit.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalProfit.setBackground(Color.WHITE);
		lblTotalProfit.setOpaque(true);
		add(lblTotalProfit);
		
		tfTotalProfit = new JLabel();
		tfTotalProfit.setBackground(Color.WHITE);
		tfTotalProfit.setOpaque(true);
		tfTotalProfit.setFont(new Font("굴림", Font.BOLD, 12));
		tfTotalProfit.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tfTotalProfit);
	}

	public JLabel getTflCntOrder() {
		return tflCntOrder;
	}
	public void setTflCntOrder(JLabel tflCntOrder) {
		this.tflCntOrder = tflCntOrder;
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
	public JLabel getTfTotalProfit() {
		return tfTotalProfit;
	}
	public void setTfTotalProfit(JLabel tfTotalProfit) {
		this.tfTotalProfit = tfTotalProfit;
	}
	public void tfClear() {
		tflCntOrder.setText(null);
		tfTotalOrder.setText(null);
		tfTotalProfit.setText(null);
		tfTotalSales.setText(null);
	}
	
}
