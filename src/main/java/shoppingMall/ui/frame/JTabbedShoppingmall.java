package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.cusframe.CustomerManagerUI;
import shoppingMall.ui.cusframe.ProductInfoUI;
import shoppingMall.ui.cuspanel.CustomerInfoTablePanel;
import shoppingMall.ui.cuspanel.procutBuyTablePanel;
import shoppingMall.ui.panel.detail.DetailMidPanel;
import shoppingMall.ui.panel.detail.DtailBottomPanel;
import shoppingMall.ui.panel.detail.DtailTopPanel;
import shoppingMall.ui.panel.main.MainBottomPanel;
import shoppingMall.ui.panel.main.MainMidPanel;
import shoppingMall.ui.panel.main.MainTopPanel;
import shoppingMall.ui.panel.product.ProductBottomPanel;
import shoppingMall.ui.panel.product.ProductMidPanel;
import shoppingMall.ui.panel.product.ProductTopPanel;

@SuppressWarnings("serial")
public class JTabbedShoppingmall extends JFrame implements ActionListener {

	private JPanel contentPane;

	private DecimalFormat df = new DecimalFormat("0,000");

///////// 빈 리스트
	List<Sale> nullList = new ArrayList<>();

///////// 메인화면
	private MainTopPanel pMainBtns;
	private MainMidPanel pMainTable;
	private MainBottomPanel pMainTotal;
	private List<Sale> saleList;

	private saleService saleService;
	private productService productService;
	private customerService customerService;

	private ProductTopPanel pProdBtns;
	private ProductBottomPanel pProdTotal;
	private ProductMidPanel pProdTable;
	private DtailTopPanel pDetailBtns;
	private DtailBottomPanel pDetailTotal;
	private DetailMidPanel pDetailTable;

///////////디테일 검색
	private Product prodSearch;
	private Customer customerSearch;
	private List<Sale> searchListByCus;
	private List<Sale> searchListByProd;
	private List<Sale> searchListByCusAndProd;

	public JTabbedShoppingmall() {
		saleService = new saleService();
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

		JPanel pMain = new JPanel();
		tabbedPane.addTab("메인 화면", null, pMain, null);
		pMain.setLayout(new BorderLayout(0, 0));

		pMainBtns = new MainTopPanel();
		pMainBtns.getBtnAllsearch().addActionListener(this);
		pMainBtns.getBtnSearch().addActionListener(this);
		pMain.add(pMainBtns, BorderLayout.NORTH);

		pMainTotal = new MainBottomPanel();
		pMain.add(pMainTotal, BorderLayout.SOUTH);

		pMainTable = new MainMidPanel();
		pMainTable.loadData();
		pMain.add(pMainTable, BorderLayout.CENTER);

		JPanel pProduct = new JPanel();
		tabbedPane.addTab("제품별 조회", null, pProduct, null);
		pProduct.setLayout(new BorderLayout(0, 0));

		pProdBtns = new ProductTopPanel();
		pProdBtns.getBtnAll().addActionListener(this);
		pProdBtns.getBtnSearch().addActionListener(this);
		pProdBtns.setService(productService);
		pProdBtns.setForeground(Color.WHITE);
		pProduct.add(pProdBtns, BorderLayout.NORTH);

		pProdTotal = new ProductBottomPanel();
		pProduct.add(pProdTotal, BorderLayout.SOUTH);

		pProdTable = new ProductMidPanel();
		pProdTable.loadData();
		pProdTable.setBackground(Color.WHITE);
		pProduct.add(pProdTable);

		pDetail = new JPanel();
		pDetail.setForeground(Color.WHITE);
		tabbedPane.addTab("상세 조회", null, pDetail, null);
		pDetail.setLayout(new BorderLayout(0, 0));

		pDetailBtns = new DtailTopPanel();
		pDetailBtns.getBtnAllSerach().addActionListener(this);
		pDetailBtns.getBtnlblCusSearch().addActionListener(this);
		pDetailBtns.getBtnProductSearch().addActionListener(this);
		pDetailBtns.setProdService(productService);
		pDetailBtns.setCusService(customerService);
		pDetail.add(pDetailBtns, BorderLayout.NORTH);

		pDetailTotal = new DtailBottomPanel();
		pDetail.add(pDetailTotal, BorderLayout.SOUTH);

		pDetailTable = new DetailMidPanel();
		detailLodeData();
		pDetail.add(pDetailTable, BorderLayout.CENTER);

////////////////// 팝업메뉴생성
		JPopupMenu popupMenu = createPopupMenu();
		pDetailTable.setPopupMenu(popupMenu);

		pCus = new JPanel();
		tabbedPane.addTab("회원 관리", null, pCus, null);
		pCus.setLayout(new BoxLayout(pCus, BoxLayout.Y_AXIS));

		pCusTable = new CustomerInfoTablePanel();
		pCusTable.setBackground(Color.WHITE);
		pCusTable.loadData();
		JPopupMenu CusPopupMenu = createCusPopupMenu();
		pCusTable.setPopupMenu(CusPopupMenu);
		pCus.add(pCusTable);

		pProd = new JPanel();
		tabbedPane.addTab("제품관리", null, pProd, null);
		pProd.setLayout(new BoxLayout(pProd, BoxLayout.Y_AXIS));

		pProdInfoTable = new procutBuyTablePanel();
		pProdInfoTable.loadData();

		JPopupMenu popupProdMenu = createProdPopupMenu();
		pProdInfoTable.setPopupMenu(popupProdMenu);

		pProd.add(pProdInfoTable);
	}
///////////////// 제품 수정, 삭제
	private JPopupMenu createProdPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem modiItem = new JMenuItem("수정");
		JMenuItem delItem = new JMenuItem("삭제");
		modiItem.addActionListener(popupProdMenuListner);
		delItem.addActionListener(popupProdMenuListner);
		popMenu.add(modiItem);
		popMenu.add(delItem);
		return popMenu;
	}
///////////////////////////////////////////// 제품 수정, 삭제
	ActionListener popupProdMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "삭제") {
				Product product = pProdInfoTable.getItem();
				
				productService.delProd(product.getProcode());
				System.out.println(product.getProcode());
				pProdInfoTable.loadData();
			} else if (e.getActionCommand() == "수정") {
				Product product = pProdInfoTable.getItem();
				Product prod = productService.selectProductByProcode(product);
				ProductInfoUI frame = new ProductInfoUI();
				frame.setDetailItem(prod);
				frame.setVisible(true);
				frame.setTable(pProdInfoTable);

			}
		}
	};
	/////////// 고객 수정, 삭제 팝업메뉴
	private JPopupMenu createCusPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem delSale = new JMenuItem("삭제");
		JMenuItem upSale = new JMenuItem("수정");

		delSale.addActionListener(popupCusMenuListner);
		upSale.addActionListener(popupCusMenuListner);

		popMenu.add(delSale);
		popMenu.add(upSale);
		return popMenu;
	}

//////////////////////////////////////고객 수정,삭제
	ActionListener popupCusMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "삭제") {
				Customer customer = pCusTable.getItem();
				customerService.delCustomer(customer.getCusno());
				pCusTable.loadData();
			} else if (e.getActionCommand() == "수정") {

				Customer customer = pCusTable.getItem();

				CustomerManagerUI frame = new CustomerManagerUI();
				frame.getpItems().setCusItem(customer);

				frame.setVisible(true);
				frame.setTable(pCusTable);

			}
		}
	};

////////////////////////////////////////////////////////
	public void tabSelected() {
		tabbedPane.setSelectedComponent(pDetail);
	}

	public void detailLodeData() {
		pDetailTable.loadData();
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem delSale = new JMenuItem("삭제");
		JMenuItem upSale = new JMenuItem("수정");

		delSale.addActionListener(popupMenuListner);
		upSale.addActionListener(popupMenuListner);

		popMenu.add(delSale);
		popMenu.add(upSale);
		return popMenu;
	}

/////////////////// 팝업메뉴 추가,삭제,수정
	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand() == "삭제") {
				Sale sale = pDetailTable.getItem();
				saleService.delSale(sale);
				detailLodeData();
			} else if (e.getActionCommand() == "수정") {

				UpdateDetailManager frame = new UpdateDetailManager();
				Customer cus = pDetailTable.getCusItem();
				Product prod = pDetailTable.getProdItem();
				Sale sale = pDetailTable.getItem();
				try {
					frame.getpUpdateDetailItem().setUpdateTf(cus, prod, sale);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(true);
				frame.setTable(pDetailTable);
			}
		}
	};
	private JPanel pDetail;
	private JTabbedPane tabbedPane;
	private JPanel pCus;
	private CustomerInfoTablePanel pCusTable;
	private JPanel pProd;
	private procutBuyTablePanel pProdInfoTable;

//////////////////
////////////////// 검색할 날짜 받아오기
	private Sale searchDate() {
		SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date searchDate = pMainBtns.getJDate().getDate();
		String date = searchDateFormat.format(searchDate);
		Sale searchByDate = new Sale(date);
		return searchByDate;
	}

///////////////// 액션 이벤트
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pDetailBtns.getBtnAllSerach()) {
			actionPerformedPDetailBtnsBtnAllSerach(e);
		}

		try {
			if (e.getSource() == pProdBtns.getBtnAll()) {
				actionPerformedPProdBtnsBtnAll(e);
			}
			if (e.getSource() == pProdBtns.getBtnSearch()) {
				actionPerformedPProdBtnsBtnSearch(e);
			}
			if (e.getSource() == pMainBtns.getBtnAllsearch()) {
				actionPerformedPMainBtnsBtnAllsearch(e);
			}
			if (e.getSource() == pDetailBtns.getBtnlblCusSearch()) {
				if (pDetailBtns.getCmbProductSearch().getSelectedIndex() == -1) {
					actionPerformedPDetailBtnsBtnlblCusSearch(e);
				} else {
					actionPerformedPTopBtnlblCusAndProdSearch(e);
				}
			}
			if (e.getSource() == pDetailBtns.getBtnProductSearch()) {
				if (pDetailBtns.getCmbCusSearch().getSelectedIndex() == -1) {
					actionPerformedPDetailBtnsBtnProductSearch(e);
				} else {
					actionPerformedPTopBtnlblCusAndProdSearch(e);
				}
			}
			if (e.getSource() == pMainBtns.getBtnSearch()) {
				actionPerformedPMainBtnsBtnSearch(e);
			}
		} catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.WARNING_MESSAGE);
		} catch (NullPointerException e1) {
///////////////// 아무값도 검색 되지 않을때
			pMainTable.selectList(nullList);
			pProdTable.selectList(nullList);
			pDetailTable.selectList(nullList);
			pMainTotal.tfClear();
			pDetailTotal.tfClear();
			pProdTotal.tfClear();
		}
	}

//// 두개 검색
	private void actionPerformedPTopBtnlblCusAndProdSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		selectListByCusAndProd();
	}

////////메인	
	protected void actionPerformedPMainBtnsBtnSearch(ActionEvent e) {
		Sale searchByDate = searchDate();
		saleList = saleService.selectMainByDate(searchByDate);
		pMainTable.selectList(saleList);
		setDataTotalSalesByDate();
		setDataTotalOrderByDate();
	}

////////메인 전체검색
	protected void actionPerformedPMainBtnsBtnAllsearch(ActionEvent e) {
		pMainTable.loadData();
		pMainBtns.tfClear();
		pMainTotal.setDataTotalOrder();
		pMainTotal.setDataTotalSales();
	}

/////// 메인 날짜 검색(토탈)
	public void setDataTotalSalesByDate() {
		int totalSales = saleList.parallelStream().mapToInt(Sale::getSales).sum();
		pMainTotal.getTfTotalSales().setText(df.format(totalSales));
	}

	public void setDataTotalOrderByDate() {
		int totalOrder = saleList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pMainTotal.getTfTotalOrder().setText(totalOrder + "");
	}

////////제품 검색
	protected void actionPerformedPProdBtnsBtnSearch(ActionEvent e) {
		Product prodInfo = (Product) pProdBtns.getCmbProduct().getSelectedItem();
		List<Sale> productByproInfoList = productService.selectProductByProInfo(prodInfo);
		pProdTable.selectList(productByproInfoList);

		int totalProfit = productByproInfoList.parallelStream().mapToInt(Sale::getProfit).sum();
		pProdTotal.getTfTotalProfit().setText(df.format(totalProfit));

		int totalOrder = productByproInfoList.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pProdTotal.getTfTotalOrder().setText(totalOrder + "");
	}

////////제품 전체 검색
	protected void actionPerformedPProdBtnsBtnAll(ActionEvent e) {
		pProdTable.loadData();
		pProdBtns.tfClear();
		pProdTotal.setDataTotalOrder();
		pProdTotal.setDataTotalProfit();
	}

/////////상세 검색
	protected void actionPerformedPDetailBtnsBtnProductSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		searchProd();

		// 총 주문 건수, 총 주문 수량, 총 판매액, 총 이익금액

		setDataCntOrderByProd();
		setDataTotalOrderByProd();
		setDataTotalSalesByProd();
		setDataTotalProfitByProd();
	}

	public void searchProd() {
		searchListByProd = saleService.selectProductByProInfo(prodSearch);
		pDetailTable.selectList(searchListByProd);
	}

	// 제품코드로 검색할때 총주문량 ,판매량 , 주문건수, 이익금액
	private void setDataTotalProfitByProd() {
		int totalProfit = searchListByProd.parallelStream().mapToInt(Sale::getProfit).sum();
		pDetailTotal.getTfTotalProfit().setText(df.format(totalProfit));
	}

	private void setDataTotalSalesByProd() {
		int totalSales = searchListByProd.parallelStream().mapToInt(Sale::getSales).sum();
		pDetailTotal.getTfTotalSales().setText(df.format(totalSales));
	}

	private void setDataTotalOrderByProd() {
		int totalOrder = searchListByProd.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pDetailTotal.getTfTotalOrder().setText(totalOrder + "");
	}

	private void setDataCntOrderByProd() {
		pDetailTotal.getTflCntOrder().setText(searchListByProd.size() + "");
	}

	// 회원코드로 검색
	protected void actionPerformedPTopBtnlblCusSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		searchCustomer();

		setDataCntOrderByCus();
		setDataTotalOrderByCus();
		setDataTotalSalesByCus();
		setDataTotalProfitByCus();
	}

	public void searchCustomer() {
		searchListByCus = saleService.selectDetailByCutomer(customerSearch);
		pDetailTable.selectList(searchListByCus);
	}

	// 회원코드로 검색할때 총주문량 ,판매량 , 주문건수, 이익금액
	private void setDataTotalProfitByCus() {
		int totalProfit = searchListByCus.parallelStream().mapToInt(Sale::getProfit).sum();
		pDetailTotal.getTfTotalProfit().setText(df.format(totalProfit));
	}

	private void setDataTotalSalesByCus() {
		int totalSales = searchListByCus.parallelStream().mapToInt(Sale::getSales).sum();
		pDetailTotal.getTfTotalSales().setText(df.format(totalSales));
	}

	private void setDataTotalOrderByCus() {
		int totalOrder = searchListByCus.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pDetailTotal.getTfTotalOrder().setText(totalOrder + "");
	}

	private void setDataCntOrderByCus() {
		pDetailTotal.getTflCntOrder().setText(searchListByCus.size() + "");
	}

/////////// 디테일 고객명으로 검색
	protected void actionPerformedPDetailBtnsBtnlblCusSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		searchCustomer();

		setDataCntOrderByCus();
		setDataTotalOrderByCus();
		setDataTotalSalesByCus();
		setDataTotalProfitByCus();

	}

/////////  디테일 두개 같이 검색
	private void selectListByCusAndProd() {
		searchListByCusAndProd = saleService.selectDetailByProdAndCus(customerSearch, prodSearch);
		pDetailTable.selectList(searchListByCusAndProd);

		setDataCntOrderByCusAndProd();
		setDataTotalOrderByCusAndProd();
		setDataTotalProfitByCusAndProd();
		setDataTotalSalesByCusAndProd();
	}

///////   디테일 두개 같이 검색 될때 총판매량 ,총이익량,총주문량,총 주문건수
	private void setDataTotalSalesByCusAndProd() {
		int totalSalesByCusAndProd = searchListByCusAndProd.parallelStream().mapToInt(Sale::getSales).sum();
		pDetailTotal.getTfTotalSales().setText(totalSalesByCusAndProd + "");

	}

	private void setDataTotalProfitByCusAndProd() {
		int TotalProfitByCusAndProd = searchListByCusAndProd.parallelStream().mapToInt(Sale::getProfit).sum();
		pDetailTotal.getTfTotalProfit().setText(TotalProfitByCusAndProd + "");

	}

	private void setDataTotalOrderByCusAndProd() {
		int TotalOrderByCusAndProd = searchListByCusAndProd.parallelStream().mapToInt(Sale::getSaleamount).sum();
		pDetailTotal.getTfTotalOrder().setText(TotalOrderByCusAndProd + "");

	}

	private void setDataCntOrderByCusAndProd() {
		pDetailTotal.getTflCntOrder().setText(searchListByCusAndProd.size() + "");

	}

////////////////// 디테일 전체 검색
	protected void actionPerformedPDetailBtnsBtnAllSerach(ActionEvent e) {
		detailLodeData();
		pDetailTotal.setDataCntOrder();
		pDetailTotal.setDataTotalOrder();
		pDetailTotal.setDataTotalProfit();
		pDetailTotal.setDataTotalSales();
		pDetailBtns.tfClear();

	}

}
