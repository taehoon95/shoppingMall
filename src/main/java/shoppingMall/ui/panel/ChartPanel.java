package shoppingMall.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import shoppingMall.graph.profitPanel;

@SuppressWarnings("serial")
public class ChartPanel extends JPanel {

	public ChartPanel(JFreeChart chart) {

		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pChartBtns = new JPanel();
		add(pChartBtns, BorderLayout.NORTH);
		
		JComboBox cmbChart = new JComboBox();
		pChartBtns.add(cmbChart);
		
		JButton btnChart = new JButton("New button");
		pChartBtns.add(btnChart);
		
		profitPanel pChart = new profitPanel();
		add(pChart);
		
		JFreeChart chart = pChart.getChart();
		ChartPanel donpanel = new ChartPanel(chart);
	}

}
