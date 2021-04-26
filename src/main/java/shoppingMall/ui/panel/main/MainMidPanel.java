package shoppingMall.ui.panel.main;

import java.awt.Color;
import java.util.List;

import javax.swing.SwingConstants;

import shoppingMall.dto.Sale;
import shoppingMall.service.saleService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

public class MainMidPanel extends AbstractCustomTablePanel<Sale> {
	public MainMidPanel() {
		initialize();
	}
	private void initialize() {
		setBackground(Color.WHITE);
	}

	private saleService service;

	@Override
	public List<Sale> initList() {
		service = new saleService();
		list = service.showMain();
		return list;
	}

	@Override
	protected void setAlignAndWidth() {

		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);
		setTableCellAlign(SwingConstants.RIGHT, 5, 6);

		// 컬럼별 너비 조정
		setTableCellWidth(150, 100, 100, 130, 80, 80, 130);

	}

	@Override
	public Object[] toArray(Sale t) {
		return new Object[] { 
				t.getDate(),
				t.getCusno().getCusno(),
				t.getCusno().getCusname(),
				t.getCusno().getCallno(),
				t.getProcode().getProcode(),
				t.getSaleamount(), t.getSales()
				};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "날짜", "회원번호", "회원명", "휴대전화", "제품코드", "주문수량", "판매액" };
	}


}
