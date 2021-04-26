package shoppingMall.ui.cuspanel;

import java.util.List;

import javax.swing.SwingConstants;

import shoppingMall.dto.Product;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.service.productService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public class productInfoTablePanel extends AbstractCustomTablePanel<Product> {

	public productInfoTablePanel() {
		initialize();
	}

	private void initialize() {

	}

	private productService service;

	@Override
	public List<Product> initList() {
		service = new productService();
		list = service.showProInfo();
		return list;
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		setTableCellAlign(SwingConstants.RIGHT, 2, 3);

		// 컬럼별 너비 조정
		setTableCellWidth(80, 80, 100, 80);
		
	}

	

	@Override
	public Object[] toArray(Product t) {
		return new Object[] { t.getProcode(), t.getProname(), t.getProprice(), t.getStock() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "제품코드", "제품명", "가격", "남은수량" };
	}

	public Product getItem() {
		int row = table.getSelectedRow();
		if (row == -1) {
			throw new NotSelectedExeption();
		}
		
		String proCode = (String) table.getValueAt(row, 0);

		return list.get(list.indexOf(new Product(proCode)));
	}

}
