package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import shoppingMall.graph.profitPanel;

public class chartFrame extends JFrame {

	private JPanel contentPane;
	private profitPanel panel = new profitPanel();
	
	private JFreeChart chart;
	private ChartPanel donpanel;

	private boolean a;
	
	public chartFrame(boolean a) {
		initialize(a);
	}
	private void initialize(boolean a) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		chart = panel.getChart(a);
		donpanel = new ChartPanel(chart);
		contentPane.add(donpanel, BorderLayout.CENTER);
	}

}
