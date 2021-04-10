package shoppingMall.ui.frame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.ui.panel.UpdateDetailManagerPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UpdateDetailManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private UpdateDetailManagerPanel pUpdateDetailItem;
	private JButton btnCancel;

	public UpdateDetailManager() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pUpdateDetailItem = new UpdateDetailManagerPanel();
		contentPane.add(pUpdateDetailItem, BorderLayout.CENTER);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns, BorderLayout.SOUTH);
		
		JButton btnUpdate = new JButton("수정");
		pBtns.add(btnUpdate);
		
		btnCancel = new JButton("취소");
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
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pUpdateDetailItem.tfClear();
	}
}
