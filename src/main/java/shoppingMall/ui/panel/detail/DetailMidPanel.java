package shoppingMall.ui.panel.detail;

import javax.swing.SwingConstants;

import shoppingMall.dto.Sale;
import shoppingMall.service.saleService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

public class DetailMidPanel extends AbstractCustomTablePanel<Sale> {
	int i = 1;
	private saleService service;
	
	@Override
	public void initList() {
		service = new saleService();
		list = service.showDetail();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);
		setTableCellAlign(SwingConstants.RIGHT, 5, 6, 7, 8);

		// 컬럼별 너비 조정
		setTableCellWidth(60, 100, 100, 100, 100, 90, 100, 100, 100);
	}

	@Override
	public Object[] toArray(Sale t) {
		return new Object[] {
				t.getOrderno(),
				t.getDate(),
				t.getProcode().getProcode(),
				t.getProcode().getProname(),
				t.getCusno().getCusname(),
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

}
