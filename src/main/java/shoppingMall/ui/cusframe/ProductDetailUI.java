package shoppingMall.ui.cusframe;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Product;
import shoppingMall.ui.cuspanel.productInfoDetailPanel;

@SuppressWarnings("serial")
public class ProductDetailUI extends JFrame {

	private JPanel contentPane;
	private productInfoDetailPanel pInfo;

	public ProductDetailUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		pInfo = new productInfoDetailPanel();
		contentPane.add(pInfo);
		pInfo.setLayout(new GridLayout(0, 2, 0, 0));
	}

	
	public void setDetailItem(Product prod) {
		pInfo.setItem(prod);
	}
	
	public void prohibitionBtn() {
		pInfo.prohibitionBtn();
	}
}
