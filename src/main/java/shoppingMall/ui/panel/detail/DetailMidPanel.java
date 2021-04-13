package shoppingMall.ui.panel.detail;

import java.util.List;

import javax.swing.SwingConstants;

import shoppingMall.dto.Customer;
import shoppingMall.dto.Product;
import shoppingMall.dto.Sale;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public class DetailMidPanel extends AbstractCustomTablePanel<Sale> {
	public DetailMidPanel() {
	}
	private saleService saleService;
	private productService prodService;
	private customerService cusService;
	
	private List<Product> prodList;
	private List<Customer> cusList;
	
	@Override
	public void initList() {
		saleService = new saleService();
		list = saleService.showDetail();
		
		prodService = new productService();
		prodList = prodService.showProInfo();
		
		cusService = new customerService();
		cusList = cusService.showCustomer();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);
		setTableCellAlign(SwingConstants.RIGHT, 5, 6, 7, 8);

		// 컬럼별 너비 조정
		setTableCellWidth(60, 100, 100, 100, 140, 90, 100, 100, 100);
	}

	@Override
	public Object[] toArray(Sale t) {
		return new Object[] {
				t.getOrderno(),
				t.getDate(),
				t.getProcode().getProcode(),
				t.getProcode().getProname(),
				String.format("%s(%s)", t.getCusno().getCusname(),t.getCusno().getCusno()),
				t.getSaleamount(),
				t.getProcode().getProprice(),
				t.getSales(),
				t.getProfit()
				
		};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"번호","날짜","제품코드","제품명","회원명","주문수량","단가","판매액","이익금액"};
	}

	public Customer getCusItem() {
		int row = table.getSelectedRow();
		String serCus = (String) table.getValueAt(row, 4);
		
		int startCus = serCus.indexOf("(");
		int lastCus = serCus.indexOf(")");
		
		int cusCode = Integer.parseInt(serCus.substring(startCus+1, lastCus));
		
		if (row == -1) {
			throw new NotSelectedExeption();
		}
		return cusList.get(cusList.indexOf(new Customer(cusCode)));
	}

	public Product getProdItem() {
		int row = table.getSelectedRow();
		String proCode = (String) table.getValueAt(row, 2);
		if (row == -1) {
			throw new NotSelectedExeption();
		}
		
		return prodList.get(prodList.indexOf(new Product(proCode)));
	}
	
	
}
