package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import shoppingMall.graph.profitPanel;
import shoppingMall.ui.manager.CusInfoManager;
import shoppingMall.ui.manager.DetailManager;
import shoppingMall.ui.manager.MainManager;
import shoppingMall.ui.manager.ProductInfoManager;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class JTabbedShoppingmall extends JFrame {

	private JPanel contentPane;

	private JTabbedPane tabbedPane;
	private MainManager pMain;
	private DetailManager pDetail;

	private ProductInfoManager pProductInfo;
	private CusInfoManager pCustomerInfo;
	private profitPanel panel = new profitPanel();
	private ChartPanel donpanel;
	
	private JFreeChart chart;
	private JFreeChart chart2;
	
	public JTabbedShoppingmall() {
		initialize();
	}

	private void initialize() {
		setTitle("관리자 화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 681, 555);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		pMain = new MainManager();
		tabbedPane.addTab("메인화면", null, pMain, null);

		pDetail = new DetailManager();
		tabbedPane.addTab("상세 조회", null, pDetail, null);

		pProductInfo = new ProductInfoManager();
		tabbedPane.addTab("제품 관리", null, pProductInfo, null);

		pCustomerInfo = new CusInfoManager();
		tabbedPane.addTab("회원 관리", null, pCustomerInfo, null);
		
		chart = panel.getChart(true);
		
		chart2 = panel.getChart(false);
	}
}
