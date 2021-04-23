package shoppingMall.ui.manager;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import shoppingMall.ui.panel.detail.DetailTopPanel;
import shoppingMall.ui.panel.detail.DetailMidPanel;
import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.frame.UpdateDetailManager;
import shoppingMall.ui.panel.detail.DetailBottomPanel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DetailManager extends JPanel implements ActionListener {
	private DetailTopPanel pDetailBtns;
	private DetailMidPanel pDetailTable;
	private DetailBottomPanel pDetailTotal;

	private DecimalFormat df = new DecimalFormat("#,###");

	private saleService saleService;
	private productService productService;
	private customerService customerService;

	List<Sale> nullList = new ArrayList<>();

	private Product prodSearch;
	private Customer customerSearch;
	private List<Sale> searchListByCus;
	private List<Sale> searchListByProd;
	private List<Sale> searchListByCusAndProd;

	public DetailManager() {
		saleService = new saleService();
		productService = new productService();
		customerService = new customerService();
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		pDetailBtns = new DetailTopPanel();
		pDetailBtns.getBtnProductSearch().addActionListener(this);
		pDetailBtns.getBtnAllSerach().addActionListener(this);
		pDetailBtns.setProdService(productService);
		pDetailBtns.setCusService(customerService);
		add(pDetailBtns, BorderLayout.NORTH);

		pDetailTable = new DetailMidPanel();
		pDetailTable.loadData();
		add(pDetailTable, BorderLayout.CENTER);

		pDetailTotal = new DetailBottomPanel();
		add(pDetailTotal, BorderLayout.SOUTH);

		JPopupMenu popupMenu = createPopupMenu();
		pDetailTable.setPopupMenu(popupMenu);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == pDetailBtns.getBtnProductSearch()) {
				if (pDetailBtns.getCmbProductSearch().getSelectedIndex() != -1
						&& pDetailBtns.getCmbCusSearch().getSelectedIndex() != -1) {
					actionPerformedPTopBtnlblCusAndProdSearch(e);
				} else if (pDetailBtns.getCmbProductSearch().getSelectedIndex() != -1) {
					actionPerformedPDetailBtnsBtnProductSearch(e);
				} else if (pDetailBtns.getCmbCusSearch().getSelectedIndex() != -1) {
					actionPerformedPDetailBtnsBtnlblCusSearch(e);
				}
			}
		} catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "오류", JOptionPane.WARNING_MESSAGE);
		}

		if (e.getSource() == pDetailBtns.getBtnAllSerach()) {
			actionPerformedPDetailBtnsBtnAllSerach(e);
		}
	}

	protected void actionPerformedPDetailBtnsBtnAllSerach(ActionEvent e) {
		pDetailTable.loadData();
		pDetailTotal.setDataCntOrder();
		pDetailTotal.setDataTotalOrder();
		pDetailTotal.setDataTotalProfit();
		pDetailTotal.setDataTotalSales();
		pDetailBtns.tfClear();
	}

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
		if(searchListByProd == null) {
			searchListByProd = nullList;
		}
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

	protected void actionPerformedPDetailBtnsBtnlblCusSearch(ActionEvent e) {
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
		if (searchListByCus == null) {
			List<Sale> nullList = new ArrayList<>();
			searchListByCus = nullList;
		}
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

/////////  디테일 두개 같이 검색
	private void selectListByCusAndProd() {
		searchListByCusAndProd = saleService.selectDetailByProdAndCus(customerSearch, prodSearch);
		if(searchListByCusAndProd == null) {
			searchListByCusAndProd = nullList;
		}
		pDetailTable.selectList(searchListByCusAndProd);

		setDataCntOrderByCusAndProd();
		setDataTotalOrderByCusAndProd();
		setDataTotalProfitByCusAndProd();
		setDataTotalSalesByCusAndProd();
	}

////두개 검색
	private void actionPerformedPTopBtnlblCusAndProdSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		selectListByCusAndProd();
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

/////////////////// 팝업메뉴 추가,삭제,수정
	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand() == "삭제") {
					Sale sale = pDetailTable.getItem();
					saleService.delSale(sale);
					pDetailTable.loadData();
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
			} catch (NotSelectedExeption e1) {
				JOptionPane.showMessageDialog(null, "목록을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		}
	};

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
}
