package shoppingMall.ui.panel.product_category;

import java.util.List;

import javax.swing.SwingConstants;

import shoppingMall.dto.Category;
import shoppingMall.service.categoryService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

public class productCategoryTable extends AbstractCustomTablePanel<Category> {

	private categoryService service; 
	
	@Override
	public List<Category> initList() {
		service = new categoryService();
		list = service.showCategory();
		return list;
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		setTableCellWidth(100, 100);
	}

	@Override
	public Object[] toArray(Category t) {
		return new Object[] {
				t.getCategoryCode(),
				t.getCategoryName()
		};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"코드","이름"};
	}

}
