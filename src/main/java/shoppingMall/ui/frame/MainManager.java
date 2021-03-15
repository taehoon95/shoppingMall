package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.saleService;
import shoppingMall.ui.panel.MainBottomPanel;
import shoppingMall.ui.panel.MainMidPanel;
import shoppingMall.ui.panel.MainTopPanel;
import javax.swing.border.TitledBorder;

public class MainManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private saleService service;
	private MainTopPanel pTop;
	private MainMidPanel pMid;
	private MainBottomPanel pBottom;
	private List<Sale> list;
	
	private DecimalFormat df = new DecimalFormat("0,000");
	
	public MainManager() {
		service = new saleService();
		initialize();
	}
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(new EmptyBorder(5, 5, 5, 5), "\uBA54\uC778\uD654\uBA74", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pTop = new MainTopPanel();
		pTop.getBtnDetail().addActionListener(this);
		pTop.getBtnProduct().addActionListener(this);
		pTop.getBtnAllsearch().addActionListener(this);
		pTop.getBtnSearch().addActionListener(this);
		contentPane.add(pTop, BorderLayout.NORTH);
		
		pMid = new MainMidPanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		pBottom = new MainBottomPanel();
		contentPane.add(pBottom, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pTop.getBtnDetail()) {
			actionPerformedPTopBtnDetail(e);
		}
		if (e.getSource() == pTop.getBtnProduct()) {
			actionPerformedPTopBtnProduct(e);
		}
		if (e.getSource() == pTop.getBtnAllsearch()) {
			actionPerformedPTopBtnAllsearch(e);
		}
		try {
			if (e.getSource() == pTop.getBtnSearch()) {
				pTopBtnSelectActionPerformed(e);
			}	
		}catch(InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}catch(NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "해당 날짜에 부합하는 검색결과가 없습니다.");
			pTop.tfClear();
		}
		
	}
	protected void pTopBtnSelectActionPerformed(ActionEvent e) {
		Sale searchByDate = searchDate();
		list = service.selectMainByDate(searchByDate);
		pMid.selectList(list);
		setDataTotalSales();
		setDataTotalOrder();
		pTop.tfClear();
		
	}
	public void setDataTotalSales() {
		int totalSales = list.parallelStream().mapToInt(Sale::getSales).sum();
		pBottom.getTfTotalSales().setText(df.format(totalSales));
	}
	public void setDataTotalOrder() {
		int totalOrder = list.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pBottom.getTfTotalOrder().setText(totalOrder+"");
	}
	private Sale searchDate() {
		SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date searchDate = pTop.getJDate().getDate();
		String date = searchDateFormat.format(searchDate);
		Sale searchByDate = new Sale(date);
		return searchByDate;
	}
	protected void actionPerformedPTopBtnAllsearch(ActionEvent e) {
		pMid.loadData();
		pTop.tfClear();
		pBottom.setDataTotalOrder();
		pBottom.setDataTotalSales();
		
	}
	protected void actionPerformedPTopBtnProduct(ActionEvent e) {
		ProductManager frame = new ProductManager();
		frame.setVisible(true);
	}
	protected void actionPerformedPTopBtnDetail(ActionEvent e) {
		DetailManager frame = new DetailManager();
		frame.setVisible(true);
	}
}
