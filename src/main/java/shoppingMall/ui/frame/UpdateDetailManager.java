package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.exception.noCountException;
import shoppingMall.service.customerService;
import shoppingMall.service.productService;
import shoppingMall.service.saleService;
import shoppingMall.ui.panel.UpdateDetailManagerPanel;
import shoppingMall.ui.panel.detail.DetailBottomPanel;
import shoppingMall.ui.panel.detail.DetailMidPanel;

@SuppressWarnings("serial")
public class UpdateDetailManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private UpdateDetailManagerPanel pUpdateDetailItem;
	private JButton btnCancel;
	private JButton btnUpdate;
	
	private productService prodService = new productService();
	private customerService cusService = new customerService();
	private saleService saleService = new saleService();
	
	private DetailMidPanel table;
	private DetailBottomPanel pDetailTotal;
	
	public UpdateDetailManager() {
		initialize();
	}
	private void initialize() {
		setTitle("주문 정보 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 390);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pUpdateDetailItem = new UpdateDetailManagerPanel();
		pUpdateDetailItem.setService(prodService, cusService);
		contentPane.add(pUpdateDetailItem, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		pBtns.setBackground(Color.WHITE);
		contentPane.add(pBtns, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("수정");
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.addActionListener(this);
		pBtns.add(btnUpdate);
		
		btnCancel = new JButton("취소");
		btnCancel.setBackground(Color.GREEN);
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
	}
	public UpdateDetailManagerPanel getpUpdateDetailItem() {
		return pUpdateDetailItem;
	}
	public void setpUpdateDetailItem(UpdateDetailManagerPanel pUpdateDetailItem) {
		this.pUpdateDetailItem = pUpdateDetailItem;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		
		try{
			if (e.getSource() == btnUpdate) {
				actionPerformedBtnUpdate(e);
			}	
		}catch (InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, "공란 존재","수정 오류",JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null, "형식을 맞춰 입력해주세요","수정 오류",JOptionPane.ERROR_MESSAGE);
		}catch (noCountException e1) {
			JOptionPane.showMessageDialog(null, "주문수량을 1이상 입력해주세요","수정 오류",JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pUpdateDetailItem.tfClear();
	}

////////////// 수정
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Sale sale = pUpdateDetailItem.getUpdateTf();
		saleService.modiDetailInfo(sale);
		
		table.loadData();
		pDetailTotal.setTotalDataDetail(table.initList());
		dispose();
	}
	
	public void setTable(DetailMidPanel table) {
		this.table = table;
	}
	
	public void setTotal(DetailBottomPanel pDetailTotal) {
		this.pDetailTotal = pDetailTotal;
	}
	
}
