package shoppingMall.ui.cuspanel;

import java.util.List;

import javax.swing.SwingConstants;

import shoppingMall.dto.Customer;
import shoppingMall.service.customerService;
import shoppingMall.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public class CustomerInfoTablePanel extends AbstractCustomTablePanel<Customer> {
	public CustomerInfoTablePanel() {
		initialize();
	}

	private void initialize() {}
	
	private customerService service;

	@Override
	public List<Customer> initList() {
		service = new customerService();
		list = service.showCustomers();
		return list;
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);

		// 컬럼별 너비 조정
		setTableCellWidth(80, 80, 100, 120,70);
	}

	@Override
	public Object[] toArray(Customer t) {
		return new Object[] { t.getCusno(),
				t.getCusname(),
				t.getBirth(),
				t.getCallno(),
				t.getSex() == 1 ? "남자"+"("+t.getSex()+")" : "여자"+"("+t.getSex()+")" };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "회원 번호", "회원 명", "생년월일", "휴대전화", "성별" };
	}

}
