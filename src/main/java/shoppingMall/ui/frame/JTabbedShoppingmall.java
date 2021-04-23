package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.ui.manager.CusInfoManager;
import shoppingMall.ui.manager.DetailManager;
import shoppingMall.ui.manager.MainManager;
import shoppingMall.ui.manager.ProductInfoManager;
import shoppingMall.ui.manager.ProductManager;

@SuppressWarnings("serial")
public class JTabbedShoppingmall extends JFrame {

	private JPanel contentPane;

	private productService productService;
	private customerService customerService;

	private JTabbedPane tabbedPane;
	private MainManager pMain;
	private ProductManager pProduct;
	private DetailManager pDetail;

	private ProductInfoManager pProductInfo;
	private CusInfoManager pCustomerInfo;

	public JTabbedShoppingmall() {
		productService = new productService();
		customerService = new customerService();
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

		pProduct = new ProductManager();
		tabbedPane.addTab("제품별 조회", null, pProduct, null);

		pDetail = new DetailManager();
		tabbedPane.addTab("상세 조회", null, pDetail, null);

		pProductInfo = new ProductInfoManager();
		tabbedPane.addTab("제품 관리", null, pProductInfo, null);

		pCustomerInfo = new CusInfoManager();
		tabbedPane.addTab("회원 관리", null, pCustomerInfo, null);
	}
	
}
