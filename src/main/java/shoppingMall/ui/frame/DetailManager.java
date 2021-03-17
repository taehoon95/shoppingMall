package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.panel.detail.DetailMidPanel;
import shoppingMall.ui.panel.detail.DtailBottomPanel;
import shoppingMall.ui.panel.detail.DtailTopPanel;

public class DetailManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DetailMidPanel pMid;
	private DtailBottomPanel pBottom;
	private DtailTopPanel pTop;
	private saleService sService;
	private customerService cService;
	private productService pService;
	
	private List<Sale> searchListByCus;
	private List<Sale> searchListByProd;
	private DecimalFormat df = new DecimalFormat("0,000");
	
	
	public DetailManager() {
		sService = new saleService();
		initialize();
	}
	private void initialize() {
		setTitle("상세정보");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pTop = new DtailTopPanel();
		pTop.getBtnlblCusSearch().addActionListener(this);
		pTop.getBtnProductSearch().addActionListener(this);
		pTop.getBtnAllSerach().addActionListener(this);
		pTop.setProdService(pService);
		pTop.setCusService(cService);
		
		contentPane.add(pTop, BorderLayout.NORTH);
		
		pMid = new DetailMidPanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		pBottom = new DtailBottomPanel();
		contentPane.add(pBottom, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == pTop.getBtnlblCusSearch()) {
				actionPerformedPTopBtnlblCusSearch(e);
			}
			if (e.getSource() == pTop.getBtnProductSearch()) {
				actionPerformedPTopBtnProductSearch(e);
			}	
		}catch(InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}catch(NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.");
		}
		
		if (e.getSource() == pTop.getBtnAllSerach()) {
			actionPerformedPTopBtnAllSerach(e);
		}
	}
	protected void actionPerformedPTopBtnAllSerach(ActionEvent e) {
		pMid.loadData();
		pTop.btnWarning();
		pTop.tfClear();
		pBottom.setDataCntOrder();
		pBottom.setDataTotalOrder();
		pBottom.setDataTotalProfit();
		pBottom.setDataTotalSales();
	}
	protected void actionPerformedPTopBtnProductSearch(ActionEvent e) {
		
		Product search = (Product) pTop.getCmbProductSearch().getSelectedItem();
		searchListByProd = sService.selectProductByProInfo(search);
		pMid.selectList(searchListByProd);
		
		// 총 주문 건수, 총 주문 수량, 총 판매액, 총 이익금액
		
		
		setDataCntOrderByProd();
		setDataTotalOrderByProd();
		setDataTotalSalesByProd();
		setDataTotalProfitByProd();
	}
	
	private void setDataTotalProfitByProd() {
		int totalProfit = searchListByProd.parallelStream().mapToInt(Sale::getProfit).sum();
		pBottom.getTfTotalProfit().setText(df.format(totalProfit));
	}
	private void setDataTotalSalesByProd() {
		int totalSales = searchListByProd.parallelStream().mapToInt(Sale::getSales).sum();
		pBottom.getTfTotalSales().setText(df.format(totalSales));
	}
	private void setDataTotalOrderByProd() {
		int totalOrder = searchListByProd.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pBottom.getTfTotalOrder().setText(totalOrder+"");
	}
	private void setDataCntOrderByProd() {
		pBottom.getTflCntOrder().setText(searchListByProd.size()+"");
	}
	
	protected void actionPerformedPTopBtnlblCusSearch(ActionEvent e) {
		
		Customer search = (Customer) pTop.getCmbCusSearch().getSelectedItem();
		searchListByCus = sService.selectDetailByCutomer(search);
		pMid.selectList(searchListByCus);
		
		setDataCntOrderByCus();
		setDataTotalOrderByCus();
		setDataTotalSalesByCus();
		setDataTotalProfitByCus();
	}
	
	private void setDataTotalProfitByCus() {
		int totalProfit = searchListByCus.parallelStream().mapToInt(Sale::getProfit).sum();
		pBottom.getTfTotalProfit().setText(df.format(totalProfit));
	}
	
	private void setDataTotalSalesByCus() {
		int totalSales = searchListByCus.parallelStream().mapToInt(Sale::getSales).sum();
		pBottom.getTfTotalSales().setText(df.format(totalSales));
	}
	
	private void setDataTotalOrderByCus() {
		int totalOrder = searchListByCus.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pBottom.getTfTotalOrder().setText(totalOrder+"");
	}
	
	private void setDataCntOrderByCus() {
		pBottom.getTflCntOrder().setText(searchListByCus.size()+"");
	}
}
