package shoppingMall.ui.panel.product;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.SwingConstants;

import shoppingMall.dto.Sale;
import shoppingMall.service.saleService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public class ProductMidPanel extends AbstractCustomTablePanel<Sale> {

	private saleService service;
	private DecimalFormat df = new DecimalFormat("#,###");
	
	@Override
	public List<Sale> initList() {
		service = new saleService();
		list = service.showProduct();
		return list;
	}

	@Override
	protected void setAlignAndWidth() {

		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		setTableCellAlign(SwingConstants.RIGHT, 3, 4, 5, 6);

		// 컬럼별 너비 조정
		setTableCellWidth(120, 100, 100, 100, 100, 100, 100);

	}

	@Override
	public Object[] toArray(Sale t) {
		return new Object[] { 
				t.getDate(),
				t.getProcode().getProcode(),
				t.getProcode().getProname(),
				df.format(t.getSaleamount()),
				df.format(t.getProcode().getProprice()),
				df.format(t.getSales()),
				df.format(t.getProfit())
				};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "날짜", "제품코드", "제품명", "주문수량", "단가", "판매액", "이익금액" };
	}



}
