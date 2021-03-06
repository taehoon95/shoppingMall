package shoppingMall.ui.manager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.exception.noCountException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.frame.UpdateDetailManager;
import shoppingMall.ui.frame.chartFrame;
import shoppingMall.ui.panel.detail.DetailBottomPanel;
import shoppingMall.ui.panel.detail.DetailMidPanel;
import shoppingMall.ui.panel.detail.DetailTopPanel;

@SuppressWarnings("serial")
public class DetailManager extends JPanel implements ActionListener {
	private DetailTopPanel pDetailBtns;
	private DetailMidPanel pDetailTable;
	private DetailBottomPanel pDetailTotal;

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
			JOptionPane.showMessageDialog(null, e1.getMessage(), "??????", JOptionPane.WARNING_MESSAGE);
		}

		if (e.getSource() == pDetailBtns.getBtnAllSerach()) {
			actionPerformedPDetailBtnsBtnAllSerach(e);
		}
	}

	protected void actionPerformedPDetailBtnsBtnAllSerach(ActionEvent e) {
		pDetailTable.loadData();
		pDetailTotal.setTotalDataDetail(saleService.showDetail());
		pDetailBtns.tfClear();
		pDetailBtns.btnWarning();
	}

	protected void actionPerformedPDetailBtnsBtnProductSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		loadTableProd();

		// ??? ?????? ??????, ??? ?????? ??????, ??? ?????????, ??? ????????????
		pDetailTotal.setTotalDataDetail(searchListByProd);
	}

	public void loadTableProd() {
		searchListByProd = saleService.selectProductByProInfo(prodSearch);
		if (searchListByProd == null) {
			searchListByProd = nullList;
		}
		pDetailTable.selectList(searchListByProd);
	}

	protected void actionPerformedPDetailBtnsBtnlblCusSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		loadTableCustomer();

		pDetailTotal.setTotalDataDetail(searchListByCus);
	}

	public void loadTableCustomer() {
		searchListByCus = saleService.selectDetailByCutomer(customerSearch);

		if (searchListByCus == null) {
			List<Sale> nullList = new ArrayList<>();
			searchListByCus = nullList;
		}
		pDetailTable.selectList(searchListByCus);
	}

////?????? ??????
	private void actionPerformedPTopBtnlblCusAndProdSearch(ActionEvent e) {
		prodSearch = (Product) pDetailBtns.getCmbProductSearch().getSelectedItem();
		customerSearch = (Customer) pDetailBtns.getCmbCusSearch().getSelectedItem();
		selectListByCusAndProd();
	}

/////////  ????????? ?????? ?????? ??????
	private void selectListByCusAndProd() {
		searchListByCusAndProd = saleService.selectDetailByProdAndCus(customerSearch, prodSearch);
		if (searchListByCusAndProd == null) {
			searchListByCusAndProd = nullList;
		}
		pDetailTable.selectList(searchListByCusAndProd);

		pDetailTotal.setTotalDataDetail(searchListByCusAndProd);
	}

/////////////////// ???????????? ??????,??????,??????
	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand() == "??????") {
					Sale sale = pDetailTable.getItem();
					saleService.delSale(sale);
					pDetailTable.loadData();
					pDetailTotal.setTotalDataDetail(pDetailTable.initList());
				} else if (e.getActionCommand() == "??????") {
					UpdateDetailManager frame = new UpdateDetailManager();

					Product prod = null;
					Customer cus = null;
					Sale sale = null;

					try {
						cus = pDetailTable.getCusItem();
						prod = pDetailTable.getProdItem();
						sale = pDetailTable.getItem();
						frame.getpUpdateDetailItem().setUpdateTf(cus, prod, sale);
						frame.setVisible(true);
					} catch (IndexOutOfBoundsException | ParseException e1) {
						JOptionPane.showMessageDialog(null, "????????? ??????????????????", "??????", JOptionPane.WARNING_MESSAGE);
					} catch (noCountException e1) {
						JOptionPane.showMessageDialog(null, "??????????????? 1?????? ??????????????????", "??????", JOptionPane.WARNING_MESSAGE);
					}
					frame.setTotal(pDetailTotal);
					frame.setTable(pDetailTable);
				}
			} catch (NotSelectedExeption e1) {
				JOptionPane.showMessageDialog(null, "????????? ???????????????.", "??????", JOptionPane.ERROR_MESSAGE);
			}
			if(e.getActionCommand() == "??????????????????") {
				chartFrame frame = new chartFrame(false);
				frame.setTitle("??????????????????");
				frame.setVisible(true);
			}
			if(e.getActionCommand() == "???????????????") {
				chartFrame frame = new chartFrame(true);
				frame.setTitle("???????????????");
				frame.setVisible(true);
			}
		}
	};

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem delSale = new JMenuItem("??????");
		JMenuItem upSale = new JMenuItem("??????");
		JMenuItem profitChart = new JMenuItem("??????????????????");
		JMenuItem amountChart = new JMenuItem("???????????????");
		
		delSale.addActionListener(popupMenuListner);
		upSale.addActionListener(popupMenuListner);
		profitChart.addActionListener(popupMenuListner);
		amountChart.addActionListener(popupMenuListner);

		popMenu.add(delSale);
		popMenu.add(upSale);
		popMenu.add(profitChart);
		popMenu.add(amountChart);
		return popMenu;
	}
}
