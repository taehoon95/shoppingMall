package shoppingMall.ui.manager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shoppingMall.ui.panel.main.MainTopPanel;
import shoppingMall.ui.panel.main.MainMidPanel;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.saleService;
import shoppingMall.ui.panel.main.MainBottomPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainManager extends JPanel implements ActionListener {
	private MainTopPanel pMainBtns;
	private MainMidPanel pMainTable;
	private MainBottomPanel pMainTotal;
	
	private DecimalFormat df = new DecimalFormat("0,000");
	
	private saleService saleService;
	
	private List<Sale> nullList = new ArrayList<>();
	private List<Sale> saleList;
	
	public MainManager() {
		saleService = new saleService();
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		pMainBtns = new MainTopPanel();
		pMainBtns.getBtnAllsearch().addActionListener(this);
		pMainBtns.getBtnSearch().addActionListener(this);
		add(pMainBtns, BorderLayout.NORTH);
		
		pMainTable = new MainMidPanel();
		pMainTable.loadData();
		add(pMainTable, BorderLayout.CENTER);
		
		pMainTotal = new MainBottomPanel();
		add(pMainTotal, BorderLayout.SOUTH);
	}

	private Sale searchDate() {
		SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date searchDate = pMainBtns.getJDate().getDate();
		String date = searchDateFormat.format(searchDate);
		Sale searchByDate = new Sale(date);
		return searchByDate;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pMainBtns.getBtnAllsearch()) {
			actionPerformedPMainBtnsBtnAllsearch(e);
		}
		try {
			if (e.getSource() == pMainBtns.getBtnSearch()) {
				actionPerformedPMainBtnsBtnSearch(e);
			}
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	protected void actionPerformedPMainBtnsBtnSearch(ActionEvent e) {
		Sale searchByDate = searchDate();
		saleList = saleService.selectMainByDate(searchByDate);
		if(saleList == null) {
			saleList = nullList;
		}
		pMainTable.selectList(saleList);
		setDataTotalSalesByDate();
		setDataTotalOrderByDate();
	}
	protected void actionPerformedPMainBtnsBtnAllsearch(ActionEvent e) {
		pMainTable.loadData();
		pMainBtns.tfClear();
		pMainTotal.setDataTotalOrder();
		pMainTotal.setDataTotalSales();
	}
	public void setDataTotalSalesByDate() {
		int totalSales = saleList.parallelStream().mapToInt(Sale::getSales).sum();
		pMainTotal.getTfTotalSales().setText(df.format(totalSales));
	}

	public void setDataTotalOrderByDate() {
		int totalOrder = saleList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pMainTotal.getTfTotalOrder().setText(totalOrder + "");
	}
}
