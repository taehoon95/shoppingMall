package shoppingMall.ui.cuspanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import shoppingMall.dto.Product;
import shoppingMall.exception.NotSelectedExeption;
import shoppingMall.service.productService;
import shoppingMall.ui.cusframe.ProductDetailUI;
import shoppingMall.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public class procutBuyTablePanel extends AbstractCustomTablePanel<Product> implements MouseListener {
	
	public procutBuyTablePanel() {
		initialize();
	}
	private void initialize() {
		table.addMouseListener(this);
	}

	private productService service;
	
	@Override
	public void initList() {
		service = new productService();
		list = service.showProInfo();
	}

	
	
	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		setTableCellAlign(SwingConstants.RIGHT,2,3);

		// 컬럼별 너비 조정
		setTableCellWidth(80, 80, 100,80);
	}

	@Override
	public Object[] toArray(Product t) {
		return new Object[] {
				t.getProcode(),
				t.getProname(),
				t.getSalePrice(),
				t.getStock()
		};
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"제품코드", "제품명", "가격", "남은수량"};
	}

	public Product getItem() {
		int row = table.getSelectedRow();
		String proCode = (String) table.getValueAt(row, 0);
		
		if(row == -1) {
			throw new NotSelectedExeption();
		}
		
		return list.get(list.indexOf(new Product(proCode)));
	}
	
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedThisTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedThisTable(MouseEvent e) {
		ProductDetailUI frame = new ProductDetailUI();
		frame.setVisible(true);
	}
}
